package com.agile.test;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.agile.beans.AdminUserData;
import com.agile.beans.DeveloperUserData;
import com.agile.beans.EpicData;
import com.agile.beans.IterationData;
import com.agile.beans.ProductOwnerUserData;
import com.agile.beans.ScrumMasterUserData;
import com.agile.beans.ScrumTeamData;
import com.agile.beans.TaskData;
import com.agile.beans.UserData;
import com.agile.beans.UserStoryData;
import com.agile.repository.IterationRepository;
import com.agile.repository.ScrumTeamRepository;
import com.agile.repository.TaskRepository;
import com.agile.repository.UserRepository;
import com.agile.repository.UserStoryRepository;

public class TestUserStoyWithIteration extends TestCase {
	ScrumTeamRepository scrumTeamRepo;
	UserRepository<UserData, ?> userRepo;
	UserStoryRepository userStoryRepository;
	TaskRepository taskRepository;
	IterationRepository iterationRepository;

	String scrumTeamName = "scrumTeam";
	String scrumMasterName = "scrmMasterUser";
	String productOwnerName = "productOwnerName";
	String developerUserName = "developerUser";
	String adminUserName = "adminUser";
	String userName = "user";
	String epicName = "epic";
	String userStoryName = "userStory";
	String taskName = "task";
	String password = "password";
	private String iterationName = "iteration2";

	private IterationData iteration;
	ScrumTeamData scrumTeam;
	ScrumMasterUserData scrumMaster;
	ProductOwnerUserData productOwner;
	DeveloperUserData developerUser;
	AdminUserData adminUser;
	UserData user;
	EpicData epic;
	UserStoryData userStory;
	TaskData task;

	ApplicationContext context = new ClassPathXmlApplicationContext(
			"classpath*:applicationContext.xml");

	@Test
	@Transactional
	public void test() {
		try {

			userRepo = context.getBean(UserRepository.class);
			userStoryRepository = context.getBean(UserStoryRepository.class);

			iterationRepository = context.getBean(IterationRepository.class);

			iteration = iterationRepository.findOne(iterationName);
			developerUser = (DeveloperUserData) userRepo.findOne(developerUserName);

			userStory = new UserStoryData(userStoryName);
			userStory.setName(userStoryName);
			userStory.setOwner(developerUser);
			userStory.setIteration(iteration);

			userStoryRepository.saveAndFlush(userStory);

			// task = new TaskData();
			// task.setName(taskName);
			// task.setUserStory(userStory);
			// task.setOwner(developerUser);
			//
			// taskRepository.saveAndFlush(task);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}

}
