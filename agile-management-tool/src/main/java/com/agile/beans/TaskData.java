package com.agile.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;

import com.agile.interfaces.DataIF;

@Entity(name = "Task")
public class TaskData implements DataIF {
	
	public TaskData(){
	}
	
	public TaskData(String name){
		this();
		setName(name);
	}

	@Id
	@Column(name = "NAME", nullable = false, unique = true, length = 50)
	private String name;

	private String detail;
	
	private boolean finished;
	
	private int estimate;
	
	private int todo;

	@ManyToOne
	@ForeignKey(name="FK_USERSTORYNAME")
	private UserStoryData UserStory;

	@ManyToOne
	@ForeignKey(name="FK_OWNERUSERNAME")
	UserData owner;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserStoryData getUserStory() {
		return UserStory;
	}

	public void setUserStory(UserStoryData userStory) {
		UserStory = userStory;
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
