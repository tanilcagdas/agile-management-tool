package com.agile.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;

import com.agile.interfaces.DataIF;

@Entity(name = "TaskChart")
public class TaskChartData implements DataIF {
	
	@Id 
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@ForeignKey(name="FK_TASKCHART")
	private TaskData task;
	
	private Date date;
	
	private int delta;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TaskData getTask() {
		return task;
	}

	public void setTask(TaskData task) {
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
