package com.agile.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.beans.TaskChartData;
import com.agile.beans.TaskData;
import com.agile.beans.UserData;
import com.agile.beans.servicebean.TaskServiceBean;
import com.agile.beans.servicebean.UserServiceBean;
import com.agile.converter.TaskConverter;
import com.agile.converter.UserConverter;
import com.agile.fw.AgileUtil;
import com.agile.interfaces.ServiceBeanIF;
import com.agile.interfaces.TaskServiceIF;
import com.agile.repository.TaskChartRepository;
import com.agile.repository.TaskRepository;
import com.agile.ui.util.AgileConstants;

@Service
public class TaskService implements TaskServiceIF {

	@Autowired
	TaskRepository repository;

	@Autowired
	TaskChartRepository taskChartRepository;

	@Autowired
	TaskConverter converter;

	@Autowired
	UserConverter userConverter;

	TaskData taskData = null;
	TaskServiceBean taskServiceBean;

	@Override
	@Transactional
	public <T extends ServiceBeanIF> T save(T item) {
		taskData = converter.convert((TaskServiceBean) item, this.taskData);
		item = (T) converter.convert(repository.save(taskData), taskServiceBean);
		List<TaskChartData> taskChartList = taskChartRepository.findAll();
		boolean found = false;
		Date date = AgileUtil.getToday();
		List<TaskChartData> thisTaskChartList = null;
		for (TaskChartData taskChartData : taskChartList) {
			thisTaskChartList = new ArrayList<>();
			if (taskChartData.getTask().getName().equals(taskData.getName())) {
				thisTaskChartList.add(taskChartData);

			}
		}

		if (thisTaskChartList != null) {

			for (TaskChartData taskChartData : thisTaskChartList) {
				if (date.getDay() == taskChartData.getDate().getDay()) {
					taskChartData.setDate(date);
					taskChartData.setDelta(taskData.getTodo());
					taskChartData.setTask(taskData);
					taskChartRepository.save(taskChartData);
					found = true;
				}
			}
		}
		if (!found) {
			TaskChartData taskChartData = new TaskChartData();
			taskChartData.setDate(date);
			taskChartData.setDelta(taskData.getTodo());
			taskChartData.setTask(taskData);
			taskChartRepository.save(taskChartData);
		}
		return item;
	}

	@Override
	public <T extends ServiceBeanIF> void delete(T item) {
		repository.delete(converter.convert((TaskServiceBean) item, taskData));

	}

	@Override
	public <T extends ServiceBeanIF> T findOne(String name) {
		TaskServiceBean task = new TaskServiceBean();
		TaskData taskData = (TaskData) repository.findOne(name);
		if (taskData == null) {
			return null;
		} else {
			converter.convert(taskData, task);
			return (T) task;
		}
	}

	@Override
	public <T extends ServiceBeanIF> List<T> findAll() {
		List<TaskData> taskDataList = repository.findAll();
		List<T> taskList = new ArrayList<>();
		for (TaskData task : taskDataList) {
			taskList.add((T) converter.convert(task, new TaskServiceBean()));
		}
		return (List<T>) taskList;
	}

	@Override
	public List<TaskServiceBean> findByOwner(UserServiceBean user) {

		UserData userData = null;
		userData = userConverter.convert(user, userData);
		List<TaskData> taskDataList = repository.findByOwner(userData);
		List<TaskServiceBean> taskList = new ArrayList<>();
		for (TaskData taskData : taskDataList) {
			taskList.add(converter.convert(taskData, new TaskServiceBean()));
		}
		return taskList;
	}
}