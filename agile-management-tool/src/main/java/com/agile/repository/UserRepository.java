package com.agile.repository;


import java.io.Serializable;
import java.util.List;

import com.agile.beans.UserData;

public interface UserRepository <T extends UserData, ID extends Serializable> extends BaseRepository<T, String> {
	
	List<UserData> findByUsername(String username);

}

