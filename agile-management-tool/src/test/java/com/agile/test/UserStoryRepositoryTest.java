package com.agile.test;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.agile.beans.UserStoryData;
import com.agile.repository.UserStoryRepository;

public class UserStoryRepositoryTest implements TesterIF{
	UserStoryRepository userStoryRepo;
	String userStoryName = "user";
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");

	@Test
	@Transactional
	public void test() {
		try {
			
		userStoryRepo = context.getBean(UserStoryRepository.class);
		UserStoryData userStory = new UserStoryData();
		int i = 1;
		String searchUserStoryName;
		do {
			searchUserStoryName = userStoryName + i;
			i++;
		} while (userStoryRepo.findOne(searchUserStoryName) != null);

		
		userStory.setName(searchUserStoryName);
		userStoryRepo.saveAndFlush(userStory);
		userStoryRepo.delete(userStory);
	
		} catch (Exception e) {
			fail();
		}
		
		
	}

}
