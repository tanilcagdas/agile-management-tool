package com.agile.test;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.agile.beans.AdminUserData;
import com.agile.beans.UserData;
import com.agile.repository.UserRepository;

public class UserRepositoryTest implements TesterIF{
	UserRepository userRepo;
	String userName = "user";
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");

	@Test
	@Transactional
	public void test() {
		try {
			
		userRepo = context.getBean(UserRepository.class);
//		AdminUserData user = new AdminUserData();
		UserData user = new UserData();
		int i = 1;
		String searchUserName;
		do {
			searchUserName = userName + i;
			i++;
		} while (userRepo.findOne(searchUserName) != null);

		
		user.setUsername(searchUserName);
		user.setPassword("password1");
		user.setEnabled(true);
		userRepo.saveAndFlush(user);
//		userRepo.delete(user);
	
		} catch (Exception e) {
			fail();
		}
		
		
	}

}
