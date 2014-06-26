package com.agile.beans.servicebean;

import com.agile.interfaces.ServiceBeanIF;

public class EpicServiceBean implements ServiceBeanIF {
	
	public EpicServiceBean(){
	}
	
	public EpicServiceBean(String name){
		this();
		setName(name);
	}

	private String name;

	private UserServiceBean owner;

	private String detail;

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

}
