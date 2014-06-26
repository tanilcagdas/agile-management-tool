package com.agile.populate;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.agile.beans.DeveloperUserData;
import com.agile.beans.IterationData;
import com.agile.beans.ProductOwnerUserData;
import com.agile.beans.ScrumMasterUserData;
import com.agile.beans.ScrumTeamData;
import com.agile.beans.TaskData;
import com.agile.beans.UserData;
import com.agile.beans.UserStoryData;
import com.agile.beans.security.AuthorityData;
import com.agile.fw.AgileUtil;
import com.agile.repository.IterationRepository;
import com.agile.repository.ScrumTeamRepository;
import com.agile.repository.TaskRepository;
import com.agile.repository.UserRepository;
import com.agile.repository.UserStoryRepository;
import com.agile.ui.util.AgileConstants;

public class PopulateDB extends TestCase {
	ScrumTeamRepository scrumTeamRepo;
	UserRepository<UserData, ?> userRepo;
	UserStoryRepository userStoryRepository;
	TaskRepository taskRepository;
	IterationRepository iterationRepository;

	String scrumTeamName = "scrumTeam";
	String scrumMasterName = "scrmMasterUser";
	String productOwnerName = "productOwnerName";
	String developerUserName = "developer1";
	String developerUserName2 = "developer2";
	String userStoryName = "userStory1";
	String userStoryName2 = "userStory2";
	String taskName = "task1";
	String taskName2 = "task2";
	String taskName3 = "task3";
	String taskName4 = "task4";
	String password = "password";
	
	private String iterationName = "iteration1";
	private String iterationName2 = "iteration2";
	
	private ScrumTeamData scrumTeam;
	private ScrumMasterUserData scrumMaster;
	private ProductOwnerUserData productOwner;
	private DeveloperUserData developerUser;
	private DeveloperUserData developerUser2;
	private UserStoryData userStory;
	private UserStoryData userStory2;
	private TaskData task;
	private TaskData task2;
	private TaskData task3;
	private TaskData task4;
	private IterationData iteration;
	private IterationData iteration2;
	

	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");

	@Test
	@Transactional
	public void test() {
		try {
			String algorithm = "MD5";
			MessageDigestPasswordEncoder encoder = new MessageDigestPasswordEncoder(algorithm);
			Object salt = "agile";
			
		userRepo = context.getBean(UserRepository.class);
		scrumTeamRepo= context.getBean(ScrumTeamRepository.class);
		userStoryRepository= context.getBean(UserStoryRepository.class);
		taskRepository= context.getBean(TaskRepository.class);
		iterationRepository = context.getBean(IterationRepository.class);
		
		AuthorityData authorityClient = new AuthorityData();
		authorityClient.setName("ROLE_CLIENT");
		
		AuthorityData authorityAdmin = new AuthorityData();
		authorityAdmin.setName("ROLE_ADMIN");
		
		scrumTeam = new ScrumTeamData();
		scrumTeam.setName(scrumTeamName);
		
		scrumTeamRepo.save(scrumTeam);
		
		scrumMaster = new ScrumMasterUserData();
		scrumMaster.setUsername(scrumMasterName);
		scrumMaster.setPassword(encoder.encodePassword(password, salt ));
		scrumMaster.setAuthority(authorityClient);
		scrumMaster.setScrumTeam(scrumTeam);
		
		productOwner = new ProductOwnerUserData();
		productOwner.setUsername(productOwnerName);
		productOwner.setPassword(encoder.encodePassword(password, salt ));
		productOwner.setAuthority(authorityClient);
		productOwner.setScrumTeam(scrumTeam);
		
		developerUser = new DeveloperUserData();
		developerUser.setUsername(developerUserName);
		developerUser.setPassword(encoder.encodePassword(password, salt ));
		developerUser.setAuthority(authorityClient);
		developerUser.setScrumTeam(scrumTeam);
		
		developerUser2 = new DeveloperUserData();
		developerUser2.setUsername(developerUserName2);
		developerUser2.setPassword(encoder.encodePassword(password, salt ));
		developerUser2.setAuthority(authorityClient);
		developerUser2.setScrumTeam(scrumTeam);
		

		userRepo.save(scrumMaster);
		userRepo.save(productOwner);
		userRepo.save(developerUser2);
		userRepo.saveAndFlush(developerUser);
		
		
		iteration = new IterationData(iterationName);
		iteration.setStartDate(AgileUtil.getAnotherDay(-8));
		iteration.setEndDate(AgileUtil.getAnotherDay(22));
		iteration.setOwner(developerUser);
		iterationRepository.save(iteration);
		
		iteration2 = new IterationData(iterationName2);
		iteration2.setStartDate(AgileUtil.getAnotherDay(-8));
		iteration2.setEndDate(AgileUtil.getAnotherDay(22));
		iteration2.setOwner(developerUser2);
		iterationRepository.saveAndFlush(iteration2);
		
		userStory = new UserStoryData();
		userStory.setName(userStoryName);
		userStory.setOwner(developerUser);
		userStory.setIteration(iteration);
		
		userStory2 = new UserStoryData();
		userStory2.setName(userStoryName2);
		userStory2.setOwner(developerUser2);
		userStory2.setIteration(iteration2);
		
		userStoryRepository.save(userStory);
		userStoryRepository.saveAndFlush(userStory2);
		
		task = new TaskData();
		task.setName(taskName);
		task.setUserStory(userStory);
		task.setOwner(developerUser);
		task.setEstimate(50);
		task.setTodo(18);
		
		task2 = new TaskData();
		task2.setName(taskName2);
		task2.setUserStory(userStory);
		task2.setOwner(developerUser);
		task2.setEstimate(40);
		task2.setTodo(20);
		
		task3 = new TaskData();
		task3.setName(taskName3);
		task3.setUserStory(userStory2);
		task3.setOwner(developerUser2);
		task3.setEstimate(60);
		task3.setTodo(20);
		
		task4 = new TaskData();
		task4.setName(taskName4);
		task4.setUserStory(userStory2);
		task4.setOwner(developerUser2);
		task4.setEstimate(55);
		task4.setTodo(11);
		
		taskRepository.save(task);
		taskRepository.save(task2);
		taskRepository.save(task3);
		taskRepository.saveAndFlush(task4);
		
		} catch (Exception e) {
			fail();
		}
		
		
	}

}
