package com.agile.populate;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.agile.beans.TaskChartData;
import com.agile.beans.TaskData;
import com.agile.fw.AgileUtil;
import com.agile.repository.TaskChartRepository;
import com.agile.repository.TaskRepository;

public class PopulateCharts extends TestCase {
	
		private TaskRepository taskRepository;
		private TaskChartRepository taskChartRepository;

		private String taskName = "task1";
		private String taskName2 = "task2";
		private String taskName3 = "task3";
		private String taskName4 = "task4";
		
		
		private TaskData task;
		private TaskData task2;
		private TaskData task3;
		private TaskData task4;
		

		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");

		@Test
		@Transactional
		public void test() {
			try {
			taskRepository= context.getBean(TaskRepository.class);
			taskChartRepository= context.getBean(TaskChartRepository.class);
			
			task = taskRepository.findOne(taskName);
			
			task2 = taskRepository.findOne(taskName2);
			
			task3 = taskRepository.findOne(taskName3);
			
			task4 = taskRepository.findOne(taskName4);
			
			TaskChartData taskChartData1_1 = new TaskChartData();
			taskChartData1_1.setTask(task);
			taskChartData1_1.setDate(AgileUtil.getAnotherDay(-7));
			taskChartData1_1.setDelta(50);
			
			TaskChartData taskChartData1_2 = new TaskChartData();
			taskChartData1_2.setTask(task);
			taskChartData1_2.setDate(AgileUtil.getAnotherDay(-4));
			taskChartData1_2.setDelta(42);

			
			TaskChartData taskChartData1_3 = new TaskChartData();
			taskChartData1_3.setTask(task);
			taskChartData1_3.setDate(AgileUtil.getAnotherDay(-3));
			taskChartData1_3.setDelta(34);

			
			TaskChartData taskChartData1_4 = new TaskChartData();
			taskChartData1_4.setTask(task);
			taskChartData1_4.setDate(AgileUtil.getAnotherDay(-2));
			taskChartData1_4.setDelta(26);

			
			TaskChartData taskChartData1_5 = new TaskChartData();
			taskChartData1_5.setTask(task);
			taskChartData1_5.setDate(AgileUtil.getAnotherDay(-1));
			taskChartData1_5.setDelta(18);
			
			
			
			
			TaskChartData taskChartData2_1 = new TaskChartData();
			taskChartData2_1.setTask(task2);
			taskChartData2_1.setDate(AgileUtil.getAnotherDay(-7));
			taskChartData2_1.setDelta(40);
			
			TaskChartData taskChartData2_2 = new TaskChartData();
			taskChartData2_2.setTask(task2);
			taskChartData2_2.setDate(AgileUtil.getAnotherDay(-6));
			taskChartData2_2.setDelta(35);

			
			TaskChartData taskChartData2_3 = new TaskChartData();
			taskChartData2_3.setTask(task2);
			taskChartData2_3.setDate(AgileUtil.getAnotherDay(-5));
			taskChartData2_3.setDelta(30);

			
			TaskChartData taskChartData2_4 = new TaskChartData();
			taskChartData2_4.setTask(task2);
			taskChartData2_4.setDate(AgileUtil.getAnotherDay(-4));
			taskChartData2_4.setDelta(25);

			
			TaskChartData taskChartData2_5 = new TaskChartData();
			taskChartData2_5.setTask(task2);
			taskChartData2_5.setDate(AgileUtil.getAnotherDay(-3));
			taskChartData2_5.setDelta(20);
			
			
			
			
			
			
			TaskChartData taskChartData3_1 = new TaskChartData();
			taskChartData3_1.setTask(task3);
			taskChartData3_1.setDate(AgileUtil.getAnotherDay(-7));
			taskChartData3_1.setDelta(60);
			
			TaskChartData taskChartData3_2 = new TaskChartData();
			taskChartData3_2.setTask(task3);
			taskChartData3_2.setDate(AgileUtil.getAnotherDay(-5));
			taskChartData3_2.setDelta(50);

			
			TaskChartData taskChartData3_3 = new TaskChartData();
			taskChartData3_3.setTask(task3);
			taskChartData3_3.setDate(AgileUtil.getAnotherDay(-4));
			taskChartData3_3.setDelta(40);

			
			TaskChartData taskChartData3_4 = new TaskChartData();
			taskChartData3_4.setTask(task3);
			taskChartData3_4.setDate(AgileUtil.getAnotherDay(-3));
			taskChartData3_4.setDelta(30);

			
			TaskChartData taskChartData3_5 = new TaskChartData();
			taskChartData3_5.setTask(task3);
			taskChartData3_5.setDate(AgileUtil.getAnotherDay(-2));
			taskChartData3_5.setDelta(20);
			
			
			
			
			
			TaskChartData taskChartData4_1 = new TaskChartData();
			taskChartData4_1.setTask(task4);
			taskChartData4_1.setDate(AgileUtil.getAnotherDay(-7));
			taskChartData4_1.setDelta(55);
			
			TaskChartData taskChartData4_2 = new TaskChartData();
			taskChartData4_2.setTask(task4);
			taskChartData4_2.setDate(AgileUtil.getAnotherDay(-6));
			taskChartData4_2.setDelta(44);

			
			TaskChartData taskChartData4_3 = new TaskChartData();
			taskChartData4_3.setTask(task4);
			taskChartData4_3.setDate(AgileUtil.getAnotherDay(-5));
			taskChartData4_3.setDelta(33);

			
			TaskChartData taskChartData4_4 = new TaskChartData();
			taskChartData4_4.setTask(task4);
			taskChartData4_4.setDate(AgileUtil.getAnotherDay(-2));
			taskChartData4_4.setDelta(22);

			
			TaskChartData taskChartData4_5 = new TaskChartData();
			taskChartData4_5.setTask(task4);
			taskChartData4_5.setDate(AgileUtil.getAnotherDay(-1));
			taskChartData4_5.setDelta(11);
			
			taskChartRepository.save(taskChartData1_1);
			taskChartRepository.save(taskChartData1_2);
			taskChartRepository.save(taskChartData1_3);
			taskChartRepository.save(taskChartData1_4);
			taskChartRepository.save(taskChartData1_5);
			taskChartRepository.save(taskChartData2_1);
			taskChartRepository.save(taskChartData2_2);
			taskChartRepository.save(taskChartData2_3);
			taskChartRepository.save(taskChartData2_4);
			taskChartRepository.save(taskChartData2_5);
			taskChartRepository.save(taskChartData3_1);
			taskChartRepository.save(taskChartData3_2);
			taskChartRepository.save(taskChartData3_3);
			taskChartRepository.save(taskChartData3_4);
			taskChartRepository.save(taskChartData3_5);
			taskChartRepository.save(taskChartData4_1);
			taskChartRepository.save(taskChartData4_2);
			taskChartRepository.save(taskChartData4_3);
			taskChartRepository.save(taskChartData4_4);
			taskChartRepository.saveAndFlush(taskChartData4_5);
			} catch (Exception e) {
				fail();
			}
			
			
		}

	}
