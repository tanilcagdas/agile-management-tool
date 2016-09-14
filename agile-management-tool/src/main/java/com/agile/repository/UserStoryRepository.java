package com.agile.repository;


import java.util.List;

import com.agile.beans.UserData;
import com.agile.beans.UserStoryData;


public interface UserStoryRepository extends BaseRepository<UserStoryData, String> {
	
	List<UserStoryData> findByOwner(UserData user); 
}

