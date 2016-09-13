package com.agile.ui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.agile.beans.servicebean.IterationServiceBean;
import com.agile.beans.servicebean.UserServiceBean;
import com.agile.interfaces.IterationServiceIF;
import com.agile.interfaces.ServiceBeanIF;
import com.agile.interfaces.UserServiceIF;
import com.agile.ui.util.AgileConstants;

@Scope("session")
@Controller
public class IterationController {

	private List<IterationServiceBean> iterationList;

	@Autowired
	IterationServiceIF service;

	@Autowired
	UserServiceIF userService;

	FacesMessage fm = null;

	@PostConstruct
	public void init() {
		findAll();
	}

	public String save() {
		for (IterationServiceBean epic : iterationList) {
			service.save(epic);
		}
		findAll();
		FacesMessage fm = new FacesMessage("save Succesfull", null);
		FacesContext.getCurrentInstance().addMessage(null, fm);
		return "";
	}

	public String delete(Integer key) {
		String name = iterationList.get(key).getName();
		service.delete(iterationList.get(key));
		findAll();
		fm = new FacesMessage(name + " deleted succesfully", null);
		FacesContext.getCurrentInstance().addMessage(null, fm);
		return "";
	}

	public String add() {
		IterationServiceBean iterationData = new IterationServiceBean();
		iterationData.setOwner(new UserServiceBean());
		iterationList.add(iterationData);
		return "";
	}

	public void handleUserSelect(Integer key) {
		String userName = iterationList.get(key).getOwner().getUsername();
		UserServiceBean user = (UserServiceBean) userService.findOne(userName);
		iterationList.get(key).setOwner(user);
	}

	public void handlePeriodChange(Integer key) {
		IterationServiceBean iteration = iterationList.get(key);
		int days = iteration.getDays();
		long time;
		long delta;
		if (days < 24) {

			delta = days * AgileConstants.DAY;
			time = iteration.getStartDate().getTime() + delta;
		} else {
			int division = days / 20;
			int remaining = days % 20;
			time = iteration.getStartDate().getTime();
			for(int i=0;i<division;i++){
				delta = 20 * AgileConstants.DAY;
				time = time + delta;
			}
			time = time+remaining*AgileConstants.DAY;
		}
		iteration.getEndDate().setTime(time);
		iterationList.set(key, iteration);
	}

	public void handleTimeChange(Integer key) {
		IterationServiceBean iteration = iterationList.get(key);
		iteration.setDays((int) ((iteration.getEndDate().getTime() - iteration.getStartDate().getTime()) / AgileConstants.DAY));
	}

	public List<String> getIterationArray() {
		List<String> iterationNameList = new ArrayList<>();
		iterationNameList.add("select one");
		for (ServiceBeanIF iteration : service.findAll()) {
			iterationNameList.add(((IterationServiceBean) iteration).getName());
		}
		return iterationNameList;
	}

	public void findAll() {
		iterationList = service.findAll();
	}

	public List<IterationServiceBean> getAssignedIterations(UserServiceBean user) {
		return service.findByOwner(user);
	}

	public List<IterationServiceBean> getIterationList() {
		return iterationList;
	}

	public void setIterationList(List<IterationServiceBean> iterationList) {
		this.iterationList = iterationList;
	}

	public IterationServiceIF getIterationService() {
		return service;
	}

	public void setIterationService(IterationServiceIF iterationService) {
		this.service = iterationService;
	}

}
