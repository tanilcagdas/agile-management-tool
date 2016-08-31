package com.agile.rest.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.agile.beans.servicebean.IterationServiceBean;
import com.agile.interfaces.ServiceBeanIF;
import com.agile.interfaces.ServiceIF;
import com.google.gson.Gson;

public class BaseRestController {

	ServiceIF service;
	
	@GET
    @Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response list() {
		List<IterationServiceBean> result;
		try {
			result = service.findAll();
			return Response.ok(new Gson().toJson(result ), MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
//	@POST
//    @Path("/save")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response save(ServiceBeanIF item){
//		try {
//			service.save(item);
//			return Response.status(Status.CREATED).build();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return Response.notModified().build();
//		}
//	}
	
	@DELETE
	@Path("/delete")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response delete(ServiceBeanIF item){
		try {
			service.delete(item);
			return Response.status(Status.GONE).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.notModified().build();
		}
	}

}
