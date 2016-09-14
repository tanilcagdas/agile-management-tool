package com.agile.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agile.beans.AdminUserData;
import com.agile.beans.DeveloperUserData;
import com.agile.beans.ProductOwnerUserData;
import com.agile.beans.ScrumMasterUserData;
import com.agile.beans.UserData;
import com.agile.beans.security.AuthorityData;
import com.agile.beans.security.AuthorityServiceBean;
import com.agile.beans.servicebean.AdminUserServiceBean;
import com.agile.beans.servicebean.DeveloperUserServiceBean;
import com.agile.beans.servicebean.ProductOwnerUserServiceBean;
import com.agile.beans.servicebean.ScrumMasterUserServiceBean;
import com.agile.beans.servicebean.ScrumTeamServiceBean;
import com.agile.beans.servicebean.UserServiceBean;
import com.agile.interfaces.ConverterIF;
import com.agile.repository.UserRepository;

@Service
public class UserConverter implements ConverterIF<UserServiceBean, UserData> {

	@Autowired
	UserRepository<UserData, String> repository;
	
	@Autowired
	ScrumTeamConverter scrumTeamConverter;
	
	@Override
	public UserData convert(UserServiceBean user, UserData userData) {
		if (userData == null) {
			userData = new UserData();
		}if(repository.findOne(user.getUsername()) != null){
			userData = repository.findOne(user.getUsername());
		}else {
			userData.setUsername(user.getUsername());
		}
		userData.setPassword(user.getPassword());
		AuthorityData authority = new AuthorityData();
		authority.setName(user.getAuthority().getName());
		userData.setAuthority(authority);

		userData.setCredentialsNonExpired(true);
		userData.setEnabled(true);

		return userData;
	}

	@Override
	public UserServiceBean convert(UserData userData, UserServiceBean user) {
//		if (user == null) {
			if(userData instanceof DeveloperUserData){
				user = new DeveloperUserServiceBean();
				if(((DeveloperUserData) userData).getScrumTeam()!=null){
					((DeveloperUserServiceBean)user).setScrumTeam(new ScrumTeamServiceBean());
					((DeveloperUserServiceBean)user).getScrumTeam().setName(((DeveloperUserData) userData).getScrumTeam().getName());
				}
			}else if(userData instanceof ScrumMasterUserData){
				user = new ScrumMasterUserServiceBean();
				if(((ScrumMasterUserData) userData).getScrumTeam()!=null){
					((ScrumMasterUserServiceBean)user).setScrumTeam(new ScrumTeamServiceBean());
					((ScrumMasterUserServiceBean)user).getScrumTeam().setName(((ScrumMasterUserData) userData).getScrumTeam().getName());				
				}
			}else if(userData instanceof ProductOwnerUserData){
				user = new ProductOwnerUserServiceBean();
				if(((ProductOwnerUserData) userData).getScrumTeam()!=null){
					((ProductOwnerUserServiceBean)user).setScrumTeam(new ScrumTeamServiceBean());
					((ProductOwnerUserServiceBean)user).getScrumTeam().setName(((ProductOwnerUserData) userData).getScrumTeam().getName());				
				}
			}else if(userData instanceof AdminUserData){
				user = new AdminUserServiceBean();
			}else {
				user = new UserServiceBean();
			}
//		}
		user.setUsername(userData.getUsername());
		user.setPassword(userData.getPassword());
		AuthorityServiceBean authority = new AuthorityServiceBean();
		authority.setName(userData.getAuthority().getName());
		user.setAuthority(authority);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);

		return user;
	}

}
