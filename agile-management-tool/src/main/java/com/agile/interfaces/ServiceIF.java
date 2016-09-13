package com.agile.interfaces;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.agile.beans.servicebean.UserServiceBean;

public interface ServiceIF<T extends ServiceBeanIF> {

	@Transactional
	public T save(T item);

	public void delete(T item);

	public T findOne(String name);

	public List<T> findAll();

	public List<T> findByOwner(UserServiceBean user);

}
