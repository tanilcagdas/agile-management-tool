package com.agile.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agile.beans.EpicData;
import com.agile.beans.servicebean.EpicServiceBean;
import com.agile.interfaces.ConverterIF;


@Service
public class EpicConverter implements ConverterIF<EpicServiceBean, EpicData>{

	@Autowired
	UserConverter userConverter;
	
	@Override
	public EpicData convert(EpicServiceBean epic, EpicData epicData) {
		if (epicData == null) {
			epicData = new EpicData();
		}
		epicData.setName(epic.getName());
		epicData.setDetail(epic.getDetail());
		epicData.setOwner(userConverter.convert(epic.getOwner(),epicData.getOwner()));
		
	

		return epicData;
	}

	@Override
	public EpicServiceBean convert(EpicData epicData, EpicServiceBean epic) {
		if (epic == null) {
			epic = new EpicServiceBean();
		}
		epic.setName(epicData.getName());
		epic.setDetail(epicData.getDetail());
		epic.setOwner(userConverter.convert(epicData.getOwner(),epic.getOwner()));

		return epic;
	}

}
