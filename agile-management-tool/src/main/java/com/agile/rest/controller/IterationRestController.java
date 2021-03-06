package com.agile.rest.controller;

import javax.annotation.PostConstruct;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.agile.beans.servicebean.IterationServiceBean;
import com.agile.interfaces.IterationServiceIF;
import com.agile.service.UserService;


@Component
@Path("iteration")
public class IterationRestController extends BaseRestController<IterationServiceBean>{
	
	@Autowired
	IterationServiceIF service;

	@PostConstruct
	public void init(){
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		super.service = this.service;
	}
}
