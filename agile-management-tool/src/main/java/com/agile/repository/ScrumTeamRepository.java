package com.agile.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agile.beans.ScrumTeamData;
import com.agile.beans.UserData;


public interface ScrumTeamRepository extends JpaRepository<ScrumTeamData, String> {
	
	List<ScrumTeamData> findByProductOwner(UserData user); 
}

