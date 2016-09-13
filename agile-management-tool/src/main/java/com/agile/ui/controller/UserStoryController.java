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
import com.agile.beans.servicebean.UserStoryServiceBean;
import com.agile.interfaces.IterationServiceIF;
import com.agile.interfaces.ServiceBeanIF;
import com.agile.interfaces.UserServiceIF;
import com.agile.interfaces.UserStoryServiceIF;

@Scope("session")
@Controller
public class UserStoryController {
	
	private List<UserStoryServiceBean> userStoryList;

	@Autowired
	UserStoryServiceIF userStoryService;
	
	@Autowired
	IterationServiceIF iterationService;

	@Autowired 
	UserServiceIF userService;
	
	FacesMessage fm = null;
	
	@PostConstruct
	   public void init() {
	      findAll();
	   }
	
	public String save(){
		for (UserStoryServiceBean userStory : userStoryList) {
			userStoryService.save(userStory );
		}
		findAll();
		fm = new FacesMessage("save succesfull", null);
		FacesContext.getCurrentInstance().addMessage(null, fm);
	
		return "";
	}
	
	public String delete(Integer key){
		String name = userStoryList.get(key).getName();
		userStoryService.delete(userStoryList.get(key));
		findAll();
		FacesMessage fm = new FacesMessage(name +" deleted succesfully", null);
		FacesContext.getCurrentInstance().addMessage(null, fm);
		return "";
	}
	
	public String add(){
		UserStoryServiceBean userStoryData = new UserStoryServiceBean();
		userStoryData.setOwner(new UserServiceBean());
		userStoryData.setIteration(new IterationServiceBean());
		userStoryList.add(userStoryData);
		return "";
	}
	
	public void findAll(){
		userStoryList = userStoryService.findAll();
	}
	
	public List<UserStoryServiceBean> getAssignedUserStories(UserServiceBean user) {
		return userStoryService.findByOwner(user);
	}
	
	public void handleIterationSelect(Integer key){
		String iterationName = userStoryList.get(key).getIteration().getName();
		IterationServiceBean iteration = (IterationServiceBean) iterationService.findOne(iterationName);
		userStoryList.get(key).setIteration(iteration);
	}
	
	public void handleUserSelect(Integer key){
		String userName = userStoryList.get(key).getOwner().getUsername();
		UserServiceBean user = (UserServiceBean) userService.findOne(userName);
		userStoryList.get(key).setOwner(user);
	}

	public List<UserStoryServiceBean> getUserStoryList() {
		return userStoryList;
	}

	public void setUserStoryList(List<UserStoryServiceBean> userStoryList) {
		this.userStoryList = userStoryList;
	}

	public UserStoryServiceIF getUserStoryService() {
		return userStoryService;
	}

	public void setUserStoryService(UserStoryServiceIF userStoryService) {
		this.userStoryService = userStoryService;
	}
	
	public List<String> getUserStoryNameList(){
		List<String> userStoryNameList = new ArrayList<>();
		userStoryNameList.add("select one");
		for (ServiceBeanIF userStory : userStoryService.findAll()) {
			userStoryNameList.add(((UserStoryServiceBean)userStory).getName());
		}
		return userStoryNameList;
	}
}
