package com.agile.repository;


import java.util.List;

import com.agile.beans.TaskData;
import com.agile.beans.UserData;


public interface TaskRepository extends BaseRepository<TaskData, String> {
	
	List<TaskData> findByOwner(UserData user); 
	
}

