package com.agile.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agile.beans.UserData;
import com.agile.beans.UserStoryData;


public interface UserStoryRepository extends JpaRepository<UserStoryData, String> {
	
	List<UserStoryData> findByOwner(UserData user); 
}

