package com.agile.rest.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.agile.beans.servicebean.IterationServiceBean;
import com.agile.interfaces.IterationServiceIF;
import com.agile.service.UserService;


@Component
@Path("iteration")
public class IterationRestController extends BaseRestController{
	
	private List<IterationServiceBean> iterationList;

	@Autowired
	IterationServiceIF service;

	@Autowired
	UserService userService;
	
	@PostConstruct
	public void init(){
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		super.service = this.service;
	}
	
	

	@POST
    @Path("/save")
	@Consumes( MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(IterationServiceBean item){
		try {
			service.save(item);
			return Response.status(Status.CREATED).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.notModified().build();
		}
	}
	
	


}
