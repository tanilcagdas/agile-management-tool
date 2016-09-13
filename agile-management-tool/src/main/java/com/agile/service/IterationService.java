package com.agile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.beans.IterationData;
import com.agile.beans.UserData;
import com.agile.beans.servicebean.IterationServiceBean;
import com.agile.beans.servicebean.UserServiceBean;
import com.agile.converter.IterationConverter;
import com.agile.converter.UserConverter;
import com.agile.interfaces.IterationServiceIF;
import com.agile.repository.IterationRepository;

@Service
public class IterationService implements IterationServiceIF {

	@Autowired
	IterationRepository repository;
	
	@Autowired
	IterationConverter converter;
	
	IterationData iterationData;
	
	@Autowired
	UserConverter userConverter;
	
	IterationServiceBean iterationServiceBean;

	@Override
	@Transactional
	public IterationServiceBean save(IterationServiceBean item) {
		if (findOne(((IterationServiceBean) item).getName()) != null) {
			iterationData = repository.findOne(((IterationServiceBean) item).getName());
		} else {
			iterationData = new IterationData();
		}
		
		iterationData = converter.convert((IterationServiceBean) item, iterationData);
		iterationData = (IterationData) repository.save(iterationData);
		converter.convert(iterationData, (IterationServiceBean) item);
		return item;
	}

	@Override
	public void delete(IterationServiceBean item) {
		repository.delete(converter.convert((IterationServiceBean)item,iterationData));
	}

	@Override
	public IterationServiceBean findOne(String name) {
		IterationServiceBean iteration = new IterationServiceBean();
		IterationData iterationData = (IterationData) repository.findOne(name);
		if (iterationData == null) {
			return null;
		} else {
			converter.convert(iterationData, iteration);
			return iteration;
		}
	}

	@Override
	public  List<IterationServiceBean> findAll() {
		List<IterationData> iterationDataList = repository.findAll();
		List<IterationServiceBean> iterationList = new ArrayList<>();
		for (IterationData epic : iterationDataList) {
			iterationList.add((IterationServiceBean) converter.convert(epic,new IterationServiceBean()));
		}
		return iterationList ;
	}
	
	@Override
	public List<IterationServiceBean> findByOwner(UserServiceBean user) {
		
		UserData userData = null;
		userData = userConverter.convert(user, userData);
		List<IterationData> iterationDataList = repository.findByOwner(userData);
		List<IterationServiceBean> iterationList = new ArrayList<>();
		for (IterationData userStoryData : iterationDataList) {
			iterationList.add(converter.convert(userStoryData, new IterationServiceBean()));
		}
		return iterationList;
	}

}