package com.agile.repository;


import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agile.beans.UserData;

public interface UserRepository <T extends UserData, ID extends Serializable> extends JpaRepository<T, String> {
	
	List<UserData> findByUsername(String username);

}

