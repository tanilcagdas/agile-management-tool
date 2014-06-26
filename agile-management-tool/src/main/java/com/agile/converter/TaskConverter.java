package com.agile.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agile.beans.TaskData;
import com.agile.beans.servicebean.TaskServiceBean;
import com.agile.interfaces.ConverterIF;


@Service
public class TaskConverter implements ConverterIF<TaskServiceBean, TaskData>{

	@Autowired
	UserConverter userConverter;
	
	@Autowired
	UserStoryConverter userStoryConverter;
	
	
	
	@Override
	public TaskData convert(TaskServiceBean task, TaskData taskData) {
		if (taskData == null) {
			taskData = new TaskData();
		}
		taskData.setName(task.getName());
		taskData.setUserStory(userStoryConverter.convert(task.getUserStory(),taskData.getUserStory()));
		taskData.setDetail(task.getDetail());
		taskData.setOwner(userConverter.convert(task.getOwner(),taskData.getOwner()));
		taskData.setTodo(task.getTodo());
		taskData.setEstimate(task.getEstimate());
		taskData.setFinished(task.isFinished());
		
	

		return taskData;
	}

	@Override
	public TaskServiceBean convert(TaskData taskData, TaskServiceBean task) {
		if (task == null) {
			task = new TaskServiceBean();
		}
		task.setName(taskData.getName());
		task.setUserStory(userStoryConverter.convert(taskData.getUserStory(),task.getUserStory()));
		task.setDetail(taskData.getDetail());
		task.setOwner(userConverter.convert(taskData.getOwner(),task.getOwner()));
		task.setTodo(taskData.getTodo());
		task.setEstimate(taskData.getEstimate());
		task.setFinished(taskData.isFinished());

		return task;
	}

}
