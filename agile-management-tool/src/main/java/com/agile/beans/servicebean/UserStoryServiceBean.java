package com.agile.beans.servicebean;

import com.agile.interfaces.ServiceBeanIF;

public class UserStoryServiceBean implements ServiceBeanIF {
	
	public UserStoryServiceBean(){
	}
	
	public UserStoryServiceBean(String name){
		this();
		this.setName(name);
	}

	private String name;

	private String detail;

	private IterationServiceBean iteration;
	
	private UserServiceBean owner;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public IterationServiceBean getIteration() {
		return iteration;
	}

	public void setIteration(IterationServiceBean iteration) {
		this.iteration = iteration;
	}

}
