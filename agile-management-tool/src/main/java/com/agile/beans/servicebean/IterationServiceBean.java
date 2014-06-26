package com.agile.beans.servicebean;

import java.util.Date;

import com.agile.interfaces.ServiceBeanIF;
import com.agile.ui.util.AgileConstants;

public class IterationServiceBean implements ServiceBeanIF {
	
	public IterationServiceBean(){
	}
	
	public IterationServiceBean(String name) {
		this();
		setName(name);
	}

	private String name;

	private UserServiceBean owner;

	private String detail;
	
	private Date startDate = new Date((new Date().getTime()/AgileConstants.DAY)*AgileConstants.DAY);
	
	private int days = 30 ;
	
	private Date endDate = new Date(startDate.getTime()+days*AgileConstants.DAY);
	

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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

}
