package com.agile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.beans.TaskChartData;
import com.agile.beans.servicebean.TaskChartServiceBean;
import com.agile.converter.TaskChartConverter;
import com.agile.converter.UserConverter;
import com.agile.interfaces.ServiceBeanIF;
import com.agile.repository.TaskChartRepository;

@Service
public class TaskChartService {

	@Autowired
	TaskChartRepository repository;

	@Autowired
	TaskChartConverter converter;
	
	@Autowired
	UserConverter userConverter;

	TaskChartData taskData = null;
	TaskChartServiceBean taskServiceBean;

	@Transactional
	public <T extends ServiceBeanIF> T save(T item) {
		item = (T) converter.convert(repository.save(converter.convert((TaskChartServiceBean) item, taskData)), taskServiceBean);
		return item;
	}

	public <T extends ServiceBeanIF> void delete(T item) {
		repository.delete(converter.convert((TaskChartServiceBean) item, taskData));

	}

	public <T extends ServiceBeanIF> T findOne(String name) {
		TaskChartServiceBean task = new TaskChartServiceBean();
		TaskChartData taskData = (TaskChartData) repository.findOne(name);
		if (taskData == null) {
			return null;
		} else {
			converter.convert(taskData, task);
			return (T) task;
		}
	}

	public <T extends ServiceBeanIF> List<T> findAll() {
		List<TaskChartData> taskDataList = repository.findAll();
		List<T> taskList = new ArrayList<>();
		for (TaskChartData task : taskDataList) {
			taskList.add((T) converter.convert(task, new TaskChartServiceBean()));
		}
		return (List<T>) taskList;
	}


}