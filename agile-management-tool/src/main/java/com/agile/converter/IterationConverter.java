package com.agile.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agile.beans.IterationData;
import com.agile.beans.servicebean.IterationServiceBean;
import com.agile.interfaces.ConverterIF;
import com.agile.repository.IterationRepository;

@Service
public class IterationConverter implements
		ConverterIF<IterationServiceBean, IterationData> {

	@Autowired
	UserConverter userConverter;
	
	@Autowired
	IterationRepository repository;

	@Override
	public IterationData convert(IterationServiceBean iteration,
			IterationData iterationData) {
		if (iterationData == null) {
			iterationData = new IterationData();
		}if(repository.findOne(iteration.getName()) != null){
			iterationData = repository.findOne(iteration.getName());
		}else {
			iterationData.setName(iteration.getName());
		}
		iterationData.setDays(iteration.getDays());
		iterationData.setDetail(iteration.getDetail());
		iterationData.setEndDate(iteration.getEndDate());
		iterationData.setOwner(userConverter.convert(iteration.getOwner(),
				iterationData.getOwner()));
		iterationData.setStartDate(iteration.getStartDate());

		return iterationData;
	}

	@Override
	public IterationServiceBean convert(IterationData iterationData,
			IterationServiceBean iteration) {
		if (iteration == null) {
			iteration = new IterationServiceBean();
		}
		iteration.setName(iterationData.getName());
		iteration.setDays(iterationData.getDays());
		iteration.setDetail(iterationData.getDetail());
		iteration.setEndDate(iterationData.getEndDate());
		iteration.setOwner(userConverter.convert(iterationData.getOwner(),
				iteration.getOwner()));
		iteration.setStartDate(iterationData.getStartDate());

		return iteration;
	}

}
