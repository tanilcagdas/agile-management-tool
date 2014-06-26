package com.agile.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agile.beans.IterationData;
import com.agile.beans.UserData;


public interface IterationRepository extends JpaRepository<IterationData, String> {
	List<IterationData> findByOwner(UserData user); 
}

