package com.agile.beans.servicebean;

import com.agile.interfaces.ServiceBeanIF;

public class TaskServiceBean implements ServiceBeanIF {

	public TaskServiceBean() {
	}

	public TaskServiceBean(String name) {
		this();
		setName(name);
	}

	private String name;

	private String detail;

	private UserStoryServiceBean UserStory;

	private UserServiceBean owner;

	private boolean finished;

	private int estimate;

	private int todo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserStoryServiceBean getUserStory() {
		return UserStory;
	}

	public void setUserStory(UserStoryServiceBean userStory) {
		UserStory = userStory;
	}

	public UserServiceBean getOwner() {
		return owner;
	}

	public void setOwner(UserServiceBean owner) {
		this.owner = owner;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public int getEstimate() {
		return estimate;
	}

	public void setEstimate(int estimate) {
		this.estimate = estimate;
	}

	public int getTodo() {
		return todo;
	}

	public void setTodo(int todo) {
		this.todo = todo;
	}

}
