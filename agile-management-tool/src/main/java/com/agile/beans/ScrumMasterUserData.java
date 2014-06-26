package com.agile.beans;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ForeignKey;




@Entity(name = "ScrumMasterUser")
public class ScrumMasterUserData extends UserData{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3114489932452602513L;

	public ScrumMasterUserData(){
	}
	
	public ScrumMasterUserData(String name){
		setUsername(name);
	}
	
	@OneToOne
	@JoinColumn(name="TEAM_ID")
	@ForeignKey(name="FK_SCRUMTEAMNAME")
	private ScrumTeamData scrumTeam;

	public ScrumTeamData getScrumTeam() {
		return scrumTeam;
	}

	public void setScrumTeam(ScrumTeamData scrumTeam) {
		this.scrumTeam = scrumTeam;
	}
	

}