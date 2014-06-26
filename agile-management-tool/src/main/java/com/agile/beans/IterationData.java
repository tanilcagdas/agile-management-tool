package com.agile.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;

import com.agile.interfaces.DataIF;
import com.agile.ui.util.AgileConstants;

@Entity(name = "Iteration")
public class IterationData implements DataIF {
	
	public IterationData(){
	}
	
	public IterationData(String name) {
		this();
		setName(name);
	}

	@Id
	@Column(name = "NAME", nullable = false, unique = true, length = 50)
	private String name;

	@ManyToOne
	@ForeignKey(name="FK_OWNERUSERNAME")
	UserData owner;

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

	public UserData getOwner() {
		return owner;
	}

	public void setOwner(UserData owner) {
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
