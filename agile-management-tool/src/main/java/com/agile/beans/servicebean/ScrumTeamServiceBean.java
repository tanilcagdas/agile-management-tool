package com.agile.beans.servicebean;

import com.agile.interfaces.ServiceBeanIF;


public class ScrumTeamServiceBean implements ServiceBeanIF{
	
	public ScrumTeamServiceBean(){
	}
	
	public ScrumTeamServiceBean(String name){
		this();
		setName(name);
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	private ProductOwnerUserServiceBean productOwner;
	
	private ScrumMasterUserServiceBean scrumMaster;
	
	public ProductOwnerUserServiceBean getProductOwner() {
		return productOwner;
	}

	public void setProductOwner(ProductOwnerUserServiceBean productOwner) {
		this.productOwner = productOwner;
	}

	public ScrumMasterUserServiceBean getScrumMaster() {
		return scrumMaster;
	}

	public void setScrumMaster(ScrumMasterUserServiceBean scrumMaster) {
		this.scrumMaster = scrumMaster;
	}

}