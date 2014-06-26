package com.agile.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agile.beans.UserStoryData;
import com.agile.beans.servicebean.UserStoryServiceBean;
import com.agile.interfaces.ConverterIF;
import com.agile.repository.UserStoryRepository;


@Service
public class UserStoryConverter implements ConverterIF<UserStoryServiceBean, UserStoryData>{

	@Autowired
	UserConverter userConverter;
	
	@Autowired
	EpicConverter epicConverter;
	
	@Autowired
	IterationConverter iterationConverter;
	
	@Autowired
	UserStoryRepository repository;
	
	@Override
	public UserStoryData convert(UserStoryServiceBean userStory, UserStoryData userStoryData) {
		if (userStoryData == null) {
			userStoryData = new UserStoryData();
		}
		if(repository.findOne(userStory.getName()) != null){
			userStoryData = repository.findOne(userStory.getName());
		}else {
			userStoryData.setName(userStory.getName());
		}
		userStoryData.setIteration(iterationConverter.convert(userStory.getIteration(),userStoryData.getIteration()));
		userStoryData.setDetail(userStory.getDetail());
		userStoryData.setOwner(userConverter.convert(userStory.getOwner(),userStoryData.getOwner()));
		
	

		return userStoryData;
	}

	@Override
	public UserStoryServiceBean convert(UserStoryData userStoryData, UserStoryServiceBean userStory) {
		if (userStory == null) {
			userStory = new UserStoryServiceBean();
		}
		userStory.setName(userStoryData.getName());
		userStory.setIteration(iterationConverter.convert(userStoryData.getIteration(),userStory.getIteration()));
		userStory.setDetail(userStoryData.getDetail());
		userStory.setOwner(userConverter.convert(userStoryData.getOwner(),userStory.getOwner()));

		return userStory;
	}

}
