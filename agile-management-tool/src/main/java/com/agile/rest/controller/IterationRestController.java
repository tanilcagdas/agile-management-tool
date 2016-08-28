package com.agile.rest.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.agile.beans.servicebean.IterationServiceBean;
import com.agile.interfaces.IterationServiceIF;
import com.agile.service.UserService;


@Component
@Path("iteration")
//@RestController
//@RequestMapping(value = "/iteration", produces = "application/json")
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
	
	

//	@RequestMapping(value = "/addMembersToGroup", method = RequestMethod.POST)
	@GET
    @Path("/list")
//	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Object list(
//			@DefaultValue("0") @QueryParam("start") int start,
//            @DefaultValue("10") @QueryParam("size") int size,
//            @QueryParam("name") String deviceName,
//            @Context HttpServletRequest req
//            ,@HeaderParam(value = "username") String username
            ) {
			List<IterationServiceBean> result = iterationList = service.findAll();
		
		return result ;
	}



}
