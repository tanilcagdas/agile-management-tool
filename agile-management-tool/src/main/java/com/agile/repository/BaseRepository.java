package com.agile.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agile.interfaces.DataIF;


public interface BaseRepository<T extends DataIF, ID extends Serializable> extends JpaRepository<T, ID> {

}
