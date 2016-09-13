package com.agile.interfaces;

import java.util.List;

import com.agile.beans.servicebean.UserServiceBean;

public interface UserServiceIF extends ServiceIF<UserServiceBean> {
	UserServiceBean save(UserServiceBean user);

	void delete(UserServiceBean user);

	UserServiceBean findOne(String userName);

	List<UserServiceBean> findAll();

	long count();

}
