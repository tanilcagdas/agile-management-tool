package com.agile.beans.servicebean;





public class ScrumMasterUserServiceBean extends UserServiceBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7228360095599498779L;

	public ScrumMasterUserServiceBean(){
	}
	
	public ScrumMasterUserServiceBean(String name){
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