package com.agile.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.agile.interfaces.DataIF;

@Entity(name = "ScrumTeam")
public class ScrumTeamData implements DataIF{
	
	public ScrumTeamData(){
	}
	
	public ScrumTeamData(String name){
		this();
		setName(name);
	}

	@Id
	@Column(name = "NAME", nullable = false, unique = true, length = 50)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@OneToOne(mappedBy="scrumTeam")
	private ProductOwnerUserData productOwner;
	
	@OneToOne(mappedBy="scrumTeam")
	private ScrumMasterUserData scrumMaster;
	
	public ProductOwnerUserData getProductOwner() {
		return productOwner;
	}

	public void setProductOwner(ProductOwnerUserData productOwner) {
		this.productOwner = productOwner;
	}

	public ScrumMasterUserData getScrumMaster() {
		return scrumMaster;
	}

	public void setScrumMaster(ScrumMasterUserData scrumMaster) {
		this.scrumMaster = scrumMaster;
	}

}