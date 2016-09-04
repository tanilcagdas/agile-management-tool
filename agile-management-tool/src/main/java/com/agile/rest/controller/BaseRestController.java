package com.agile.rest.controller;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.Date;
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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class BaseRestController<T extends ServiceBeanIF> {

	protected ServiceIF service;
	
	private Gson gson;
	
	public BaseRestController() {
		
		JsonSerializer<Date> ser = new JsonSerializer<Date>() {
			  @Override
			  public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext 
			             context) {
			    return src == null ? null : new JsonPrimitive(src.getTime());
			  }
			};

			JsonDeserializer<Date> deser = new JsonDeserializer<Date>() {
			  @Override
			  public Date deserialize(JsonElement json, Type typeOfT,
			       JsonDeserializationContext context) throws JsonParseException {
			    return json == null ? null : new Date(json.getAsLong());
			  }
			};

		 gson = new GsonBuilder()
			   .registerTypeAdapter(Date.class, ser)
			   .registerTypeAdapter(Date.class, deser).create();
		
		
	}
	
	@GET
    @Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response list() {
		List<T> result;
		try {
			result = service.findAll();
			return Response.ok(gson.toJson(result ), MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@POST
    @Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(T item){
		try {
			service.save(item);
			return Response.status(Status.CREATED).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.notModified().build();
		}
	}
	
	@DELETE
	@Path("/delete")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response delete(T item){
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

class IterationServiceBeanDeserializer implements JsonDeserializer<IterationServiceBean>{
		
		@Override
		public IterationServiceBean deserialize(JsonElement json, Type arg1, JsonDeserializationContext arg2)
				throws JsonParseException {
			
			return new Gson().fromJson(json, IterationServiceBean.class);
		}
	
}



class DateDeserializer implements JsonDeserializer<Date>{
	@Override
	public Date deserialize(JsonElement json, Type arg1, JsonDeserializationContext arg2)
			throws JsonParseException {
		long time = Long.parseLong(json.getAsString());
	    return new Date(time);
	}
}

class DateDeserializer2 implements JsonDeserializer<java.sql.Date> {
	
	@Override
	public java.sql.Date deserialize(JsonElement json, Type arg1, JsonDeserializationContext arg2)
			throws JsonParseException {
		long time = Long.parseLong(json.getAsString());
		return new java.sql.Date(time);
	}
}
class TimestampDeserializer implements JsonDeserializer<Timestamp> {
	
	@Override
	public Timestamp deserialize(JsonElement json, Type arg1, JsonDeserializationContext arg2)
			throws JsonParseException {
		long time = Long.parseLong(json.getAsString());
		return new Timestamp(time);
	}
}
