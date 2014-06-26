package com.agile.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agile.beans.ScrumTeamData;
import com.agile.beans.servicebean.ScrumTeamServiceBean;
import com.agile.interfaces.ConverterIF;
import com.agile.repository.ScrumTeamRepository;


@Service
public class ScrumTeamConverter implements ConverterIF<ScrumTeamServiceBean, ScrumTeamData>{

	@Autowired
	UserConverter userConverter;
	
	@Autowired
	ScrumTeamRepository repository;
	
	@Autowired
	private ProductOwnerConverter productOwnerConverter;
	
	@Autowired
	private ScrumMasterConverter scrumMasterConverter;
	
	@Override
	public ScrumTeamData convert(ScrumTeamServiceBean scrumTeam, ScrumTeamData scrumTeamData) {
		if (scrumTeamData == null) {
			scrumTeamData = new ScrumTeamData();
		}
		if(repository.findOne(scrumTeam.getName()) != null){
			scrumTeamData = repository.findOne(scrumTeam.getName());
		}else {
			scrumTeamData.setName(scrumTeam.getName());
		}
		scrumTeamData.setProductOwner(productOwnerConverter.convert(userConverter.convert(scrumTeam.getProductOwner(),scrumTeamData.getProductOwner())));
		scrumTeamData.getProductOwner().setScrumTeam(scrumTeamData);
		scrumTeamData.setScrumMaster(scrumMasterConverter.convert( userConverter.convert(scrumTeam.getScrumMaster(),scrumTeamData.getScrumMaster())));
		scrumTeamData.getScrumMaster().setScrumTeam(scrumTeamData);

		
	

		return scrumTeamData;
	}

	@Override
	public ScrumTeamServiceBean convert(ScrumTeamData scrumTeamData, ScrumTeamServiceBean scrumTeam) {
		if (scrumTeam == null) {
			scrumTeam = new ScrumTeamServiceBean();
		}
		scrumTeam.setName(scrumTeamData.getName());
		scrumTeam.setProductOwner(productOwnerConverter.convert(userConverter.convert(scrumTeamData.getProductOwner(),scrumTeam.getProductOwner())));
		scrumTeam.setScrumMaster(scrumMasterConverter.convert(userConverter.convert(scrumTeamData.getScrumMaster(),scrumTeam.getScrumMaster())));

		return scrumTeam;
	}

}
