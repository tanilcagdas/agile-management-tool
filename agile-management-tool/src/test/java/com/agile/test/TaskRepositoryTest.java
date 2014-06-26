package com.agile.test;

import static org.junit.Assert.fail;

import java.awt.print.Pageable;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.agile.beans.TaskData;
import com.agile.beans.UserData;
import com.agile.repository.TaskRepository;
import com.agile.repository.UserRepository;

public class TaskRepositoryTest implements TesterIF{
	TaskRepository taskRepo;
	UserRepository userRepo;
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");

	@Test
	@Transactional
	public void test() {
		try {
			
		taskRepo = context.getBean(TaskRepository.class);
		userRepo = context.getBean(UserRepository.class);
		UserData ownerData = (UserData) userRepo.findOne("developerUser");
		List<TaskData> test1 = taskRepo.findByOwner(ownerData);
		System.out.println(test1.get(0).getName());
//		taskRepo.findByOwnerContainig(ownerData);
	
		} catch (Exception e) {
			fail();
		}
		
		
	}

}
