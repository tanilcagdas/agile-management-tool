package com.agile.beans;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ForeignKey;




@Entity(name = "ProductOwnerUser")
public class ProductOwnerUserData extends UserData{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7852409936000801551L;

	public ProductOwnerUserData(){
	}
	
	public ProductOwnerUserData(String name){
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