package com.agile.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agile.beans.ScrumMasterUserData;
import com.agile.beans.UserData;
import com.agile.beans.servicebean.ScrumMasterUserServiceBean;
import com.agile.beans.servicebean.UserServiceBean;
import com.agile.interfaces.UserServiceIF;

@Service
public class ScrumMasterConverter {
	
	@Autowired
	UserServiceIF userService;

	public ScrumMasterUserServiceBean convert(UserServiceBean user) {
		ScrumMasterUserServiceBean productOwner = new ScrumMasterUserServiceBean();
		productOwner.setUsername(user.getUsername());
		productOwner.setAuthority(user.getAuthority());
		productOwner.setPassword(user.getPassword());
		return productOwner;
	}
	
	
	public ScrumMasterUserData convert(UserData user) {
		ScrumMasterUserData productOwner = new ScrumMasterUserData();
		if(user instanceof ScrumMasterUserData){
			productOwner.setUsername(user.getUsername());
		}else{
			productOwner.setUsername(user.getUsername()+"_");
		}
		productOwner.setAuthority(user.getAuthority());
		productOwner.setPassword(user.getPassword());
		return productOwner;
	}
	
	

}
