package com.agile.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agile.beans.DeveloperUserData;
import com.agile.beans.UserData;
import com.agile.beans.servicebean.DeveloperUserServiceBean;
import com.agile.beans.servicebean.UserServiceBean;
import com.agile.interfaces.UserServiceIF;

@Service
public class DeveloperConverter {
	
	@Autowired
	UserServiceIF userService;

	public DeveloperUserServiceBean convert(UserServiceBean user) {
		DeveloperUserServiceBean productOwner = new DeveloperUserServiceBean();
		productOwner.setUsername(user.getUsername());
		productOwner.setAuthority(user.getAuthority());
		productOwner.setPassword(user.getPassword());
		return productOwner;
	}
	
	
	public DeveloperUserData convert(UserData user) {
		DeveloperUserData productOwner = new DeveloperUserData();
		if(user instanceof DeveloperUserData){
			productOwner.setUsername(user.getUsername());
		}else{
			productOwner.setUsername(user.getUsername()+"_");
		}
		productOwner.setAuthority(user.getAuthority());
		productOwner.setPassword(user.getPassword());
		return productOwner;
	}
	
	

}
