package com.agile.beans.servicebean;

import java.util.Date;

import com.agile.interfaces.ServiceBeanIF;

public class TaskChartServiceBean implements ServiceBeanIF {

	private int id;
	
	private TaskServiceBean task;
	
	private Date date;
	
	private int delta;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TaskServiceBean getTask() {
		return task;
	}

	public void setTask(TaskServiceBean task) {
		this.task = task;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDelta() {
		return delta;
	}

	public void setDelta(int delta) {
		this.delta = delta;
	}


}
