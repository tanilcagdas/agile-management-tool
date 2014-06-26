package com.agile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.beans.TaskData;
import com.agile.beans.UserData;
import com.agile.beans.UserStoryData;
import com.agile.beans.servicebean.TaskServiceBean;
import com.agile.beans.servicebean.UserServiceBean;
import com.agile.beans.servicebean.UserStoryServiceBean;
import com.agile.converter.UserConverter;
import com.agile.converter.UserStoryConverter;
import com.agile.interfaces.ServiceBeanIF;
import com.agile.interfaces.UserStoryServiceIF;
import com.agile.repository.UserStoryRepository;

@Service
public class UserStoryService implements UserStoryServiceIF {

	@Autowired
	UserStoryRepository repository;
	
	@Autowired
	UserStoryConverter converter;
	
	@Autowired
	UserConverter userConverter;

	UserStoryServiceBean userStory = null;
	UserStoryData userStoryData = null;

	@Override
	@Transactional
	public <T extends ServiceBeanIF> T save(T item) {
		//UserStoryData userData = null;
		if (findOne(((UserStoryServiceBean) item).getName()) != null) {
			userStoryData = repository.findOne(((UserStoryServiceBean) item).getName());
		} else {
			userStoryData = new UserStoryData();
		}
		
		userStoryData = converter.convert((UserStoryServiceBean) item, userStoryData);
		userStoryData = (UserStoryData) repository.save(userStoryData);
		converter.convert(userStoryData, (UserStoryServiceBean) item);
		return item;


	}
	
	@Override
	public <T extends ServiceBeanIF> void delete(T item) {
		repository.delete(converter.convert((UserStoryServiceBean)item,userStoryData));
	}
	
	@Override
	public <T extends ServiceBeanIF> T findOne(String name) {
		UserStoryData userStroyData = null;
		try {
			userStroyData = repository.findOne(name);
		} catch (Exception e) {
			return null;
		}
		if (userStroyData == null) {
			return null;
		} else {
			userStory = converter.convert(userStroyData,userStory);
			return (T) userStory;
		}
		
	}
	
	@Override
	public <T extends ServiceBeanIF> List<T> findAll() {
		List<UserStoryData> userStoryDataList = repository.findAll();
		List<T> userStoryList = new ArrayList<>();
		for (UserStoryData userStory : userStoryDataList) {
			userStoryList.add((T) converter.convert(userStory,new UserStoryServiceBean()));
		}
		return (List<T>) userStoryList;
	}
	
	@Override
	public List<UserStoryServiceBean> findByOwner(UserServiceBean user) {
		
		UserData userData = null;
		userData = userConverter.convert(user, userData);
		List<UserStoryData> userStoryDataList = repository.findByOwner(userData);
		List<UserStoryServiceBean> userStoryList = new ArrayList<>();
		for (UserStoryData userStoryData : userStoryDataList) {
			userStoryList.add(converter.convert(userStoryData, new UserStoryServiceBean()));
		}
		return userStoryList;
	}

	

}