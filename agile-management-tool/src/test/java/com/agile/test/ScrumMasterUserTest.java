package com.agile.test;

import java.io.Serializable;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.agile.beans.AdminUserData;
import com.agile.beans.ScrumMasterUserData;
import com.agile.beans.ScrumTeamData;
import com.agile.beans.UserData;
import com.agile.repository.UserRepository;

public class ScrumMasterUserTest extends TestCase {
	UserRepository<UserData, Serializable> userRepo;
	String userName = "user";
	ApplicationContext context = new ClassPathXmlApplicationContext(
			"classpath*:applicationContext.xml");

	@Test
	@Transactional
	public void test() {
		try {

			userRepo = context.getBean(UserRepository.class);
			ScrumMasterUserData user = new ScrumMasterUserData();
			int i = 1;
			String searchUserName;
			do {
				searchUserName = userName + i;
				i++;
			} while (userRepo.findOne(searchUserName) != null);

			user.setUsername(searchUserName);
			user.setPassword("password1");
			user.setEnabled(true);
			
			ScrumTeamData scrumTeam = new ScrumTeamData();
			scrumTeam.setName("scrumTeam1");
			user.setScrumTeam(scrumTeam);
			userRepo.saveAndFlush(user);
//			userRepo.delete(user);

		} catch (Exception e) {
			fail();
		}

	}

}
