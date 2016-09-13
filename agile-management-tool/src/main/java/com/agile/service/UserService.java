package com.agile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agile.beans.AdminUserData;
import com.agile.beans.DeveloperUserData;
import com.agile.beans.ProductOwnerUserData;
import com.agile.beans.ScrumMasterUserData;
import com.agile.beans.UserData;
import com.agile.beans.servicebean.AdminUserServiceBean;
import com.agile.beans.servicebean.DeveloperUserServiceBean;
import com.agile.beans.servicebean.ProductOwnerUserServiceBean;
import com.agile.beans.servicebean.ScrumMasterUserServiceBean;
import com.agile.beans.servicebean.UserServiceBean;
import com.agile.converter.UserConverter;
import com.agile.interfaces.ServiceBeanIF;
import com.agile.interfaces.UserServiceIF;
import com.agile.repository.UserRepository;

@Service
public class UserService implements UserServiceIF{

	@Autowired
	UserRepository<UserData, ?> userRepository;

	@Autowired
	UserConverter userConverter;

	/* (non-Javadoc)
	 * @see com.agile.service.UserServiceIF#save(com.agile.beans.servicebean.UserServiceBean)
	 */
	@Override
	public UserServiceBean save(UserServiceBean user) {
		UserData userData = null;
		if (findOne(user.getUsername()) != null) {
			userData = userRepository.findOne(user.getUsername());
		} else {
			if (user instanceof ScrumMasterUserServiceBean) {
				userData = new ScrumMasterUserData();
			} else if (user instanceof ProductOwnerUserServiceBean) {
				userData = new ProductOwnerUserData();
			}else if (user instanceof DeveloperUserServiceBean) {
				userData = new DeveloperUserData();
			}else if (user instanceof AdminUserServiceBean) {
				userData = new AdminUserData();
			} else {
				userData = new UserData();
			}

		}
		userData = userConverter.convert(user, userData);
		userData = (UserData) userRepository.save(userData);
		userConverter.convert(userData, user);
		return user;
	}

	/* (non-Javadoc)
	 * @see com.agile.service.UserServiceIF#delete(com.agile.beans.servicebean.UserServiceBean)
	 */
	@Override
	public void delete(UserServiceBean user) {
		UserData userData = null;
		userData = userRepository.findOne(user.getUsername());
		userRepository.delete(userData);
	}

	/* (non-Javadoc)
	 * @see com.agile.service.UserServiceIF#findOne(java.lang.String)
	 */
	@Override
	public UserServiceBean findOne(String userName) {
		UserData userData = (UserData) userRepository.findOne(userName);
		if (userData == null) {
			return null;
		} else {
			return userConverter.convert(userData, null);
		}
	}

	/* (non-Javadoc)
	 * @see com.agile.service.UserServiceIF#findAll()
	 */
	@Override
	public List<UserServiceBean> findAll() {
		List<UserData> userList = userRepository.findAll();
		List<UserServiceBean> userSBList = new ArrayList<>();
		for (UserData userData : userList) {
			userSBList.add(userConverter.convert(userData, null));
		}
		return userSBList;
	}

	/* (non-Javadoc)
	 * @see com.agile.service.UserServiceIF#count()
	 */
	@Override
	public long count() {
		return userRepository.count();
	}

	@Override
	public List<UserServiceBean> findByOwner(UserServiceBean user) {
		// TODO Auto-generated method stub
		return null;
	}
}