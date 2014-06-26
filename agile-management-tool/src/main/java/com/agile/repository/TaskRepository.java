package com.agile.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agile.beans.TaskData;
import com.agile.beans.UserData;


public interface TaskRepository extends JpaRepository<TaskData, String> {
	
	List<TaskData> findByOwner(UserData user); 
	
}

