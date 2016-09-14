package com.agile.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
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
public class IterationService extends BaseService<IterationServiceBean,IterationData> implements IterationServiceIF {

	@Autowired
	IterationRepository repository;
	
	@Autowired
	IterationConverter converter;
	
	IterationData iterationData;
	
	@Autowired
	UserConverter userConverter;
	
	IterationServiceBean iterationServiceBean;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostConstruct
	public void init() {
		super.repository = (JpaRepository)repository;
		super.converter = converter;
	}

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