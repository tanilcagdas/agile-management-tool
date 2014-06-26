package com.agile.beans.servicebean;





public class DeveloperUserServiceBean extends UserServiceBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7276348927645843780L;

	public DeveloperUserServiceBean(){
	}
	
	public DeveloperUserServiceBean(String name){
		setUsername(name);
	}

	private ScrumTeamServiceBean ScrumTeam;

	public ScrumTeamServiceBean getScrumTeam() {
		return ScrumTeam;
	}

	public void setScrumTeam(ScrumTeamServiceBean scrumTeam) {
		ScrumTeam = scrumTeam;
	}
	
}