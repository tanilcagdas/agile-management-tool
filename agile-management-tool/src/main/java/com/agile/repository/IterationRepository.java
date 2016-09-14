package com.agile.repository;


import java.util.List;

import com.agile.beans.IterationData;
import com.agile.beans.UserData;


public interface IterationRepository extends BaseRepository<IterationData, String> {
	
	List<IterationData> findByOwner(UserData user); 
}

