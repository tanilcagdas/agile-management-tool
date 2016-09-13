package com.agile.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agile.beans.AdminUserData;
import com.agile.beans.UserData;
import com.agile.beans.servicebean.AdminUserServiceBean;
import com.agile.beans.servicebean.UserServiceBean;
import com.agile.interfaces.UserServiceIF;

@Service
public class AdminConverter {
	
	@Autowired
	UserServiceIF userService;

	public AdminUserServiceBean convert(UserServiceBean user) {
		AdminUserServiceBean productOwner = new AdminUserServiceBean();
		productOwner.setUsername(user.getUsername());
		productOwner.setAuthority(user.getAuthority());
		productOwner.setPassword(user.getPassword());
		return productOwner;
	}
	
	
	public AdminUserData convert(UserData user) {
		AdminUserData productOwner = new AdminUserData();
		if(user instanceof AdminUserData){
			productOwner.setUsername(user.getUsername());
		}else{
			productOwner.setUsername(user.getUsername()+"_");
		}
		productOwner.setAuthority(user.getAuthority());
		productOwner.setPassword(user.getPassword());
		return productOwner;
	}
	
	

}
