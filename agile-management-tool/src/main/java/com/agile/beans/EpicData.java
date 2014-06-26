package com.agile.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;

import com.agile.interfaces.DataIF;

@Entity(name = "Epic")
public class EpicData implements DataIF {
	
	public EpicData(){
	}
	
	public EpicData(String name){
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

}
