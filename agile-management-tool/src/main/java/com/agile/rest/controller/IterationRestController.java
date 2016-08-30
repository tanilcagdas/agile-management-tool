package com.agile.rest.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.agile.beans.servicebean.IterationServiceBean;
import com.agile.interfaces.IterationServiceIF;
import com.agile.service.UserService;
import com.google.gson.Gson;


@Component
@Path("iteration")
public class IterationRestController {
	
	private List<IterationServiceBean> iterationList;

	@Autowired
	IterationServiceIF service;

	@Autowired
	UserService userService;
	
	@PostConstruct
	public void init(){
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	

	@GET
    @Path("/list")
	public Response list(
//			@DefaultValue("0") @QueryParam("start") int start,
//            @DefaultValue("10") @QueryParam("size") int size,
//            @QueryParam("name") String deviceName,
//            @Context HttpServletRequest req
//            ,@HeaderParam(value = "username") String username
            ) {
			List<IterationServiceBean> result = iterationList = service.findAll();
		
		return Response.ok(new Gson().toJson(result ), MediaType.APPLICATION_JSON).build();
	}
	
	


}
