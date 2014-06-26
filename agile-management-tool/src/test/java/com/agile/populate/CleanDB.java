package com.agile.populate;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.agile.beans.UserData;
import com.agile.repository.ScrumTeamRepository;
import com.agile.repository.TaskRepository;
import com.agile.repository.UserRepository;
import com.agile.repository.UserStoryRepository;

public class CleanDB extends TestCase {
	ScrumTeamRepository scrumTeamRepo;
	UserRepository<UserData, ?> userRepo;
	UserStoryRepository userStoryRepository;
	TaskRepository taskRepository;

		

	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");

	@Test
	@Transactional
	public void test() {
		try {
			
		userRepo = context.getBean(UserRepository.class);
		scrumTeamRepo= context.getBean(ScrumTeamRepository.class);
		userStoryRepository= context.getBean(UserStoryRepository.class);
		taskRepository= context.getBean(TaskRepository.class);
		
		scrumTeamRepo.deleteAll();
		
		userRepo.deleteAll();
		
		userStoryRepository.deleteAll();
		
		taskRepository.deleteAll();;

		} catch (Exception e) {
			fail();
		}
		
		
	}

}
