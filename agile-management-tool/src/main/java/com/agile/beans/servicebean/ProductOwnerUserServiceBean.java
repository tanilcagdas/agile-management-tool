package com.agile.beans.servicebean;





public class ProductOwnerUserServiceBean extends UserServiceBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4700179801239256051L;

	public ProductOwnerUserServiceBean(){
	}
	
	public ProductOwnerUserServiceBean(String name){
		setUsername(name);
	}
	
	private ScrumTeamServiceBean scrumTeam;

	public ScrumTeamServiceBean getScrumTeam() {
		return scrumTeam;
	}

	public void setScrumTeam(ScrumTeamServiceBean scrumTeam) {
		this.scrumTeam = scrumTeam;
	}

}