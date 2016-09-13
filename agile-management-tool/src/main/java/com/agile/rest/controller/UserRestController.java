package com.agile.rest.controller;

import javax.annotation.PostConstruct;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.agile.beans.servicebean.UserServiceBean;
import com.agile.interfaces.UserServiceIF;


@Component
@Path("user")
public class UserRestController extends BaseRestController<UserServiceBean>{
	
	@Autowired
	private UserServiceIF service;

	
	@PostConstruct
	public void init(){
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		super.service = this.service;
	}
}
