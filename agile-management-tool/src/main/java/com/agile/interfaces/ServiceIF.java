package com.agile.interfaces;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.agile.beans.servicebean.UserServiceBean;

public interface ServiceIF {
	
    @Transactional
	public <T extends ServiceBeanIF>T save(T item) ;
	
    public <T extends ServiceBeanIF>void delete(T item) ;
	
    public <T extends ServiceBeanIF>T findOne(String name) ;
	
    public <T extends ServiceBeanIF>List<T> findAll() ;
    
    public <T extends ServiceBeanIF> List<T> findByOwner(UserServiceBean user); 


}
