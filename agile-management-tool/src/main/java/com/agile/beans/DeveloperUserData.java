package com.agile.beans;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;




@Entity(name = "DeveloperUser")
public class DeveloperUserData extends UserData{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4223368796188849462L;

	public DeveloperUserData(){
	}
	
	public DeveloperUserData(String name){
		setUsername(name);
	}

	@ManyToOne
	@JoinColumn(name="TEAM_ID")
	@ForeignKey(name="FK_SCRUMTEAMNAME")
	private ScrumTeamData ScrumTeam;

	public ScrumTeamData getScrumTeam() {
		return ScrumTeam;
	}

	public void setScrumTeam(ScrumTeamData scrumTeam) {
		ScrumTeam = scrumTeam;
	}
	
}