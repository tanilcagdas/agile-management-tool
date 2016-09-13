package com.agile.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agile.beans.ProductOwnerUserData;
import com.agile.beans.UserData;
import com.agile.beans.servicebean.ProductOwnerUserServiceBean;
import com.agile.beans.servicebean.UserServiceBean;
import com.agile.interfaces.UserServiceIF;


@Service
public class ProductOwnerConverter {

	@Autowired
	UserServiceIF userService;
	
	public ProductOwnerUserServiceBean convert(UserServiceBean user) {
		ProductOwnerUserServiceBean productOwner = new ProductOwnerUserServiceBean();
		productOwner.setUsername(user.getUsername());
		productOwner.setAuthority(user.getAuthority());
		productOwner.setPassword(user.getPassword());
		return productOwner;
	}
	
	
	public ProductOwnerUserData convert(UserData user) {
		ProductOwnerUserData productOwner = new ProductOwnerUserData();
//		if(userService.findOne(user.getUsername())==null){
		if(user instanceof ProductOwnerUserData){
			productOwner.setUsername(user.getUsername());
		}else{
			productOwner.setUsername(user.getUsername()+"_");
			
		}
		productOwner.setAuthority(user.getAuthority());
		productOwner.setPassword(user.getPassword());
		return productOwner;
	}
	
	

}
