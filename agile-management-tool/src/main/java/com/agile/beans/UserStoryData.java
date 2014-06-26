package com.agile.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;

import com.agile.interfaces.DataIF;

@Entity(name = "UserStory")
public class UserStoryData implements DataIF {
	
	public UserStoryData(){
	}
	
	public UserStoryData(String name){
		this();
		this.setName(name);
	}

	@Id
	@Column(name = "NAME", nullable = false, unique = true, length = 50)
	private String name;

	private String detail;

	
	@ManyToOne
	@ForeignKey(name="FK_ITERATIONNAME")
	private IterationData iteration;

	@ManyToOne
	@ForeignKey(name="FK_OWNERUSERNAME")
	UserData owner;

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

	public IterationData getIteration() {
		return iteration;
	}

	public void setIteration(IterationData iteration) {
		this.iteration = iteration;
	}

}
