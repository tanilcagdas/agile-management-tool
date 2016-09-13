package com.agile.ui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.agile.beans.servicebean.DeveloperUserServiceBean;
import com.agile.beans.servicebean.ProductOwnerUserServiceBean;
import com.agile.beans.servicebean.ScrumMasterUserServiceBean;
import com.agile.beans.servicebean.ScrumTeamServiceBean;
import com.agile.beans.servicebean.UserServiceBean;
import com.agile.converter.ProductOwnerConverter;
import com.agile.converter.ScrumMasterConverter;
import com.agile.interfaces.ScrumTeamServiceIF;
import com.agile.interfaces.ServiceBeanIF;
import com.agile.interfaces.UserServiceIF;

@Scope("session")
@Controller
public class ScrumTeamController {

	private List<ScrumTeamServiceBean> scrumTeamList;
	
	private List<UserServiceBean> scrumTeamDeveloperList;
	
	private String selectedScrumTeam;
	
	private DualListModel<String> developers;

	
	@Autowired
	ScrumTeamServiceIF service;

	@Autowired
	UserServiceIF userService;
	
	@Autowired
	ProductOwnerConverter productOwnerConverter;
	
	
	@Autowired
	ScrumMasterConverter scrumMasterConverter;
	
	FacesMessage fm = null;

	@PostConstruct
	public void init() {
		findAll();
	}

	public String save() {
		for (ScrumTeamServiceBean scrumTeam : scrumTeamList) {
			service.save(scrumTeam);
		}
		findAll();
		FacesMessage fm = new FacesMessage("save Succesfull", null);
		FacesContext.getCurrentInstance().addMessage(null, fm);
		return "";
	}
	
	public String saveScrumTeamMembers() {
		for (String developer : getDevelopers().getTarget()) {
			DeveloperUserServiceBean dev = (DeveloperUserServiceBean) userService.findOne(developer);
//			if(dev.getScrumTeam() == null){
//				dev.setScrumTeam(new ScrumTeamServiceBean());
//			}
			if(dev.getScrumTeam() == null ||  !dev.getScrumTeam().getName().equals(getSelectedScrumTeam())){
				dev.setScrumTeam((ScrumTeamServiceBean) service.findOne(getSelectedScrumTeam()));
				userService.save(dev);
			}
		}
		handleScrumTeamSelect();
		FacesMessage fm = new FacesMessage("save succesfull", null);
		FacesContext.getCurrentInstance().addMessage(null, fm);
		return "";
	}
	
	public void onTransfer(TransferEvent event) {  
		System.out.println("");
	}

	public String delete(Integer key) {
		String name = scrumTeamList.get(key).getName();
		service.delete(scrumTeamList.get(key));
		findAll();
		fm = new FacesMessage(name +" deleted succesfully", null);
		FacesContext.getCurrentInstance().addMessage(null, fm);
		return "";
	}

	public String add() {
		ScrumTeamServiceBean scrumTeamData = new ScrumTeamServiceBean();
		scrumTeamData.setProductOwner(new ProductOwnerUserServiceBean());
		scrumTeamData.setScrumMaster(new ScrumMasterUserServiceBean());
		scrumTeamList.add(scrumTeamData);
		return "";
	}

	public void handleScrumMasterUserSelect(Integer key) {
		String userName = scrumTeamList.get(key).getScrumMaster().getUsername();
		ScrumMasterUserServiceBean user = scrumMasterConverter.convert(userService.findOne(userName));
		scrumTeamList.get(key).setScrumMaster(user);
	}
	
	public void handleProductOwnerUserSelect(Integer key) {
		String userName = scrumTeamList.get(key).getProductOwner().getUsername();
		ProductOwnerUserServiceBean user = productOwnerConverter.convert(userService.findOne(userName));
		scrumTeamList.get(key).setProductOwner(user);
	}
	
	public void handleScrumTeamSelect() {
		List<String> developersSource = new ArrayList<String>();  
		List<String> developersTarget = new ArrayList<String>();
		setScrumTeamDeveloperList(userService.findAll());
		for (int i = 0; i<getScrumTeamDeveloperList().size() ;) {
			UserServiceBean user =getScrumTeamDeveloperList().get(i);
			
			if(user instanceof DeveloperUserServiceBean){
				DeveloperUserServiceBean devUser = (DeveloperUserServiceBean)user;
				if(devUser.getScrumTeam()!=null && devUser.getScrumTeam().getName().equals(getSelectedScrumTeam())){
					developersTarget.add(user.getUsername());
					i++;
				}else {
					developersSource.add(user.getUsername());
					getScrumTeamDeveloperList().remove(i);
				}
			}else{
				getScrumTeamDeveloperList().remove(i);
			}
		}
		
        developers = new DualListModel<String>(developersSource, developersTarget);  
	}

	public List<String> getScrumTeamNameList() {
		List<String> scrumTeamNameList = new ArrayList<>();
		scrumTeamNameList.add("select one");
		for (ServiceBeanIF scrumTeam : service.findAll()) {
			scrumTeamNameList.add(((ScrumTeamServiceBean) scrumTeam).getName());
		}
		return scrumTeamNameList;
	}

	public void findAll() {
		scrumTeamList = service.findAll();
	}
	
	public List<ScrumTeamServiceBean> getScrumTeamList() {
		return scrumTeamList;
	}

	public void setScrumTeamList(List<ScrumTeamServiceBean> scrumTeamList) {
		this.scrumTeamList = scrumTeamList;
	}

	public ScrumTeamServiceIF getScrumTeamService() {
		return service;
	}

	public void setScrumTeamService(ScrumTeamServiceIF service) {
		this.service = service;
	}

	public List<UserServiceBean> getScrumTeamDeveloperList() {
		return scrumTeamDeveloperList;
	}

	public void setScrumTeamDeveloperList(List<UserServiceBean> scrumTeamDeveloperList) {
		this.scrumTeamDeveloperList = scrumTeamDeveloperList;
	}

	public String getSelectedScrumTeam() {
		return selectedScrumTeam;
	}

	public void setSelectedScrumTeam(String selectedScrumTeam) {
		this.selectedScrumTeam = selectedScrumTeam;
	}

	public DualListModel<String> getDevelopers() {
		return developers;
	}

	public void setDevelopers(DualListModel<String> developers) {
		this.developers = developers;
	}

}
