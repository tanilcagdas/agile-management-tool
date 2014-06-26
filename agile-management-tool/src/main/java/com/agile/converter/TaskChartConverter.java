package com.agile.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agile.beans.TaskChartData;
import com.agile.beans.servicebean.TaskChartServiceBean;
import com.agile.interfaces.ConverterIF;


@Service
public class TaskChartConverter implements ConverterIF<TaskChartServiceBean, TaskChartData>{

	@Autowired
	UserConverter userConverter;
	
	@Autowired
	TaskConverter taskConverter;
	
	@Override
	public TaskChartData convert(TaskChartServiceBean taskChart, TaskChartData taskChartData) {
		if (taskChartData == null) {
			taskChartData = new TaskChartData();
		}
		taskChartData.setId(taskChart.getId());
		taskChartData.setTask(taskConverter.convert(taskChart.getTask(), taskChartData.getTask()));
		taskChartData.setDate(taskChart.getDate());
		taskChartData.setDelta(taskChart.getDelta());

		return taskChartData;
	}

	@Override
	public TaskChartServiceBean convert(TaskChartData taskChartData, TaskChartServiceBean taskChart) {
		if (taskChart == null) {
			taskChart = new TaskChartServiceBean();
		}
		taskChart.setId(taskChartData.getId());
		taskChart.setTask(taskConverter.convert(taskChartData.getTask(), taskChart.getTask()));
		taskChart.setDate(taskChartData.getDate());
		taskChart.setDelta(taskChartData.getDelta());

		return taskChart;
	}

}
