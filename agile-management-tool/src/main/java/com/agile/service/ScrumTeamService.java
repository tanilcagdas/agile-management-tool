package com.agile.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.beans.ProductOwnerUserData;
import com.agile.beans.ScrumMasterUserData;
import com.agile.beans.ScrumTeamData;
import com.agile.beans.UserData;
import com.agile.beans.servicebean.ScrumTeamServiceBean;
import com.agile.beans.servicebean.UserServiceBean;
import com.agile.converter.ScrumTeamConverter;
import com.agile.converter.UserConverter;
import com.agile.interfaces.ScrumTeamServiceIF;
import com.agile.repository.ScrumTeamRepository;
import com.agile.repository.UserRepository;

@Service
public class ScrumTeamService extends BaseService<ScrumTeamServiceBean,ScrumTeamData> implements ScrumTeamServiceIF{

	@Autowired
	ScrumTeamRepository repository;
	
	@Autowired
	UserRepository<UserData, String> userRepository;
	
	@Autowired
	UserConverter userConverter;
	
	@Autowired
	ScrumTeamConverter converter;
	
	ScrumTeamData scrumTeamData;
	
	ScrumTeamServiceBean scrumTeamServiceBean;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostConstruct
	public void init() {
		super.repository = (JpaRepository)repository;
		super.converter = converter;
	}

	@Override
	@Transactional
	public ScrumTeamServiceBean save(ScrumTeamServiceBean item) {
		if (findOne(((ScrumTeamServiceBean) item).getName()) != null) {
			scrumTeamData = repository.findOne(((ScrumTeamServiceBean) item).getName());
		} else {
			scrumTeamData = new ScrumTeamData();
		}
		ProductOwnerUserData oldPO = null;
		ScrumMasterUserData oldSM = null;
		if(scrumTeamData.getProductOwner()!=null){
			 oldPO = scrumTeamData.getProductOwner();
		}
		if(scrumTeamData.getScrumMaster() != null){
			 oldSM = scrumTeamData.getScrumMaster();
		}
		scrumTeamData = converter.convert((ScrumTeamServiceBean)item,scrumTeamData);
		ProductOwnerUserData PO = scrumTeamData.getProductOwner();
		ScrumMasterUserData SM = scrumTeamData.getScrumMaster();
		if(oldPO!=null && !PO.getUsername().equals(oldPO.getUsername())){
			oldPO.setScrumTeam(null);
			userRepository.save(oldPO);
		}
		if(oldSM!=null && !SM.getUsername().equals(oldSM.getUsername())){
			oldSM.setScrumTeam(null);
			userRepository.save(oldSM);
		}
		repository.save(scrumTeamData);
		userRepository.save(PO);
		userRepository.save(SM);
		
		converter.convert(scrumTeamData, (ScrumTeamServiceBean) item);
		return item;
	}

	@Override
	public  ScrumTeamServiceBean findOne(String name) {
		ScrumTeamServiceBean scrumTeam = new ScrumTeamServiceBean();
		ScrumTeamData scrumTeamData = repository.findOne(name);
		if (scrumTeamData == null) {
			return null;
		} else {
			converter.convert(scrumTeamData, scrumTeam);
		return scrumTeam;
		}
	}

	
	@Override
	public List<ScrumTeamServiceBean> findByOwner(UserServiceBean user) {
		
		UserData userData = null;
		userData = userConverter.convert(user, userData);
		List<ScrumTeamData> scrumTeamDataList = repository.findByProductOwner((ProductOwnerUserData) userData);
		List<ScrumTeamServiceBean> scrumTeamList = new ArrayList<>();
		for (ScrumTeamData scrumTeamData : scrumTeamDataList) {
			scrumTeamList.add(converter.convert(scrumTeamData, new ScrumTeamServiceBean()));
		}
		return scrumTeamList;
	}
}