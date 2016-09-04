package com.agile.rest.controller;

import javax.annotation.PostConstruct;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.agile.beans.servicebean.UserStoryServiceBean;
import com.agile.interfaces.UserStoryServiceIF;


@Component
@Path("userstory")
public class UserStoryRestController extends BaseRestController<UserStoryServiceBean>{
	
	@Autowired
	private UserStoryServiceIF service;
	
	@PostConstruct
	public void init(){
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		super.service = this.service;
	}
}
