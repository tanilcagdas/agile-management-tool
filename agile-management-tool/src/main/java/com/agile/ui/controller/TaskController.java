package com.agile.ui.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.agile.beans.servicebean.TaskServiceBean;
import com.agile.beans.servicebean.UserServiceBean;
import com.agile.beans.servicebean.UserStoryServiceBean;
import com.agile.interfaces.TaskServiceIF;
import com.agile.interfaces.UserServiceIF;
import com.agile.interfaces.UserStoryServiceIF;

@Scope("session")
@Controller
public class TaskController {

	private List<TaskServiceBean> taskList;
	private List<TaskServiceBean> assignedTasks;

	@Autowired
	TaskServiceIF taskService;

	@Autowired
	UserServiceIF userService;

	@Autowired
	UserStoryServiceIF userStoryService;

	FacesMessage fm = null;

	@PostConstruct
	public void init() {
		findAll();
		FacesContext ctx = FacesContext.getCurrentInstance();
		UserServiceBean user = userService.findOne(ctx.getExternalContext().getRemoteUser());
		findAssignedTasks(user);
	}

	public String save() {
		for (TaskServiceBean task : taskList) {
			taskService.save(task);
		}
		init();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage fm = new FacesMessage("save Succesfull", null);
		facesContext.addMessage(null, fm);
		return "";
	}

	public String saveAssignments() {
		for (TaskServiceBean task : assignedTasks) {
			taskService.save(task);
		}
		init();
		FacesMessage fm = new FacesMessage("assignments save succesfully", null);
		FacesContext.getCurrentInstance().addMessage(null, fm);
		return "";
	}

	public String delete(Integer key) {
		String name = taskList.get(key).getName(); 
		taskService.delete(taskList.get(key));
		findAll();
		fm = new FacesMessage(name + " deleted succesfully", null);
		FacesContext.getCurrentInstance().addMessage(null, fm);
		return "";
	}

	public String add() {
		TaskServiceBean taskData = new TaskServiceBean();
		taskData.setUserStory(new UserStoryServiceBean());
		taskData.setOwner(new UserServiceBean());
		taskList.add(taskData);
		return "";
	}

	public void findAll() {
		taskList = taskService.findAll();
	}

	public void findAssignedTasks(UserServiceBean user) {
		setAssignedTasks(taskService.findByOwner(user));
	}

	public void handleUserSelect(Integer key) {
		String userName = taskList.get(key).getOwner().getUsername();
		UserServiceBean user = (UserServiceBean) userService.findOne(userName);
		taskList.get(key).setOwner(user);
	}

	public void handleUserStorySelect(Integer key) {
		String userStoryName = taskList.get(key).getUserStory().getName();
		UserStoryServiceBean userStory = (UserStoryServiceBean) userStoryService.findOne(userStoryName);
		taskList.get(key).setUserStory(userStory);
	}

	public List<TaskServiceBean> getUserStoryList() {
		return taskList;
	}

	public void setUserStoryList(List<TaskServiceBean> taskList) {
		this.taskList = taskList;
	}

	public TaskServiceIF getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskServiceIF taskService) {
		this.taskService = taskService;
	}

	public List<TaskServiceBean> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<TaskServiceBean> taskList) {
		this.taskList = taskList;
	}

	public List<TaskServiceBean> getAssignedTasks() {
		return assignedTasks;
	}

	public void setAssignedTasks(List<TaskServiceBean> assignedTasks) {
		this.assignedTasks = assignedTasks;
	}
}
