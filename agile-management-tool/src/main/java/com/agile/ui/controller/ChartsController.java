package com.agile.ui.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.agile.beans.servicebean.IterationServiceBean;
import com.agile.beans.servicebean.TaskChartServiceBean;
import com.agile.beans.servicebean.TaskServiceBean;
import com.agile.interfaces.IterationServiceIF;
import com.agile.interfaces.TaskServiceIF;
import com.agile.service.TaskChartService;
import com.agile.ui.util.AgileConstants;

@Scope("session")
@Controller
public class ChartsController {

	@Autowired
	private TaskServiceIF taskService;

	@Autowired
	private TaskChartService taskChartService;

	@Autowired
	private IterationServiceIF iterationService;

	private String selectedIterationName;

	@PostConstruct
	public void init() {
		createLinearModel();
	}

	private int minY = 0;

	private int maxY = 10;

	private int maxX = 100;

	private List<TaskChartServiceBean> taskChartList = null;
	
	private Set<TaskServiceBean> taskSet = null;
	
	private Map<Long, Integer> iterationDateMap = new HashMap<>();

	private CartesianChartModel linearModel;
	
	private List<Date> taskChartDateList;
	
	private List<Date> dateIterationList;
	
	private LineChartSeries allTasksLine = null;
	
	private LineChartSeries baseLine = null;

	public CartesianChartModel getLinearModel() {
		createLinearModel();
		return linearModel;
	}

	public String getSelectedIterationName() {
		return selectedIterationName;
	}

	public void setSelectedIterationName(String selectedIterationName) {
		this.selectedIterationName = selectedIterationName;
	}

	public int getMinY() {
		return minY;
	}

	public void setMinY(int minY) {
		this.minY = minY;
	}

	public int getMaxY() {
		return maxY;
	}

	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}

	public int getMaxX() {
		return maxX;
	}

	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}
	
	private void createLinearModel() {
		linearModel = new CartesianChartModel();
		allTasksLine = new LineChartSeries();
		allTasksLine.setLabel("Tasks");
		baseLine = new LineChartSeries();
		baseLine.setLabel("Base");
		baseLine.setMarkerStyle("diamond");

		if (selectedIterationName != null) {
			dateIterationList = getDateList((IterationServiceBean) iterationService.findOne(selectedIterationName));
			int dayNumber = 0;
			for (Date date : dateIterationList) {
				iterationDateMap.put(date.getTime(), dayNumber);
				dayNumber++;
			}

			taskChartList = taskChartService.findAll();
			if (selectedIterationName != null) {
				for (int i = 0; i < taskChartList.size();) {
					if (!selectedIterationName.equals(taskChartList.get(i).getTask().getUserStory().getIteration().getName())) {
						taskChartList.remove(i);
					} else {
						i++;
					}
				}
			}
			setMaxY(0);

			taskChartDateList = getDateList(taskChartList);

			// Draw seperate task lines
			DrawSeperateTaskLines();

			// Draw task line
			DrawTaskLine();

			// Draw base line
			DrawBaseLine();
		} else {
			setMaxY(10);
			baseLine.set(1, 0);
			allTasksLine.set(1, 0);
		}

		linearModel.addSeries(allTasksLine);
		linearModel.addSeries(baseLine);
	}
	
	private void DrawBaseLine() {
		int iterationDays = 1;
		for (@SuppressWarnings("unused") Date date : dateIterationList) {
			double factor = (dateIterationList.size() - iterationDays + 1) / (double) dateIterationList.size();
			int value = (int) (getMaxY() * factor);
			baseLine.set(iterationDays, value);
			iterationDays++;
		}
		setMaxX(iterationDays + 3);

		if (taskChartDateList.size() != 0) {
			setMaxY(getMaxY() + getMaxY() / taskChartDateList.size());
		}
	}

	private void DrawTaskLine() {
		Map<String, Integer> deltaMap = new HashMap<>();
		for (Date taskChartDate : taskChartDateList) {
			int delta = 0;
			int index = 0;
			for (TaskChartServiceBean taskChartServiceBean : taskChartList) {
				if (taskChartServiceBean.getDate().getTime() == taskChartDate.getTime()) {
					if (deltaMap.containsKey(taskChartServiceBean.getTask().getName())) {
						deltaMap.remove(taskChartServiceBean.getTask().getName());
					}
					deltaMap.put(taskChartServiceBean.getTask().getName(), taskChartServiceBean.getDelta());
				}
			}
			for (TaskServiceBean task : taskSet) {
				if (deltaMap.containsKey(task.getName())) {
					delta = delta + deltaMap.get(task.getName());
				} else {
						System.out.println("something wrong");
				}
			}
			if (iterationDateMap.containsKey(taskChartDate.getTime())) {
				index = iterationDateMap.get(taskChartDate.getTime());
			} else {
				index = 30;
			}
			allTasksLine.set(index, delta);
			if (delta > getMaxY()) {
				setMaxY(delta);
			}
		}
	}

	private void DrawSeperateTaskLines() {
		taskSet = new HashSet<>();
		for (TaskChartServiceBean taskChart : taskChartList) {
			boolean nameExists = false;
			for (TaskServiceBean taskServiceBean : taskSet) {
				if (taskServiceBean.getName() == taskChart.getTask().getName()) {
					nameExists = true;
				}
			}
			if (!nameExists) {
				taskSet.add(taskChart.getTask());
			}
		}
		for (TaskServiceBean task : taskSet) {
			LineChartSeries seriesX = new LineChartSeries();
			seriesX.setLabel(task.getName());
			int index = 1;
			for (TaskChartServiceBean taskChartServiceBean : taskChartList) {
				if (taskChartServiceBean.getTask().getName().equals(task.getName())) {
					if (iterationDateMap.containsKey(taskChartServiceBean.getDate().getTime())) {
						index = iterationDateMap.get(taskChartServiceBean.getDate().getTime());
					} else {
						index = 30;
					}
					seriesX.set(index, taskChartServiceBean.getDelta());
				}
			}
			linearModel.addSeries(seriesX);
		}

		
	}

	private List<Date> getDateList(List<TaskChartServiceBean> taskChartList) {
		List<Date> dateList = new ArrayList<>();
		for (TaskChartServiceBean taskChartServiceBean : taskChartList) {
			boolean found = false;
			for (Date date : dateList) {
				if (taskChartServiceBean.getDate().getTime() == date.getTime()) {
					found = true;
					break;
				}
			}
			if (!found) {
				dateList.add(taskChartServiceBean.getDate());
			}
		}
		Collections.sort(dateList);
		return dateList;
	}

	private List<Date> getDateList(IterationServiceBean iterationServiceBean) {
		List<Date> dateList = new ArrayList<>();
		Date i = iterationServiceBean.getStartDate();
		for (int day = 0; day < iterationServiceBean.getDays(); day++) {

			dateList.add(i);
			i = new Date(i.getTime() + AgileConstants.DAY);
		}

		return dateList;
	}

	public void handleIterationSelect() {
		createLinearModel();
	}

}
