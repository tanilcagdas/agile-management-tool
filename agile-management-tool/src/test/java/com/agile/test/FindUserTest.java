package com.agile.test;

import java.io.Serializable;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.agile.beans.UserData;
import com.agile.repository.UserRepository;

public class FindUserTest extends TestCase {
	UserRepository<UserData, Serializable> userRepo;
	String userName = "user";
	ApplicationContext context = new ClassPathXmlApplicationContext(
			"classpath*:applicationContext.xml");

	@Test
	@Transactional
	public void test() {
		try {

			userRepo = context.getBean(UserRepository.class);
			String userName = "developerUser";
			UserData user = userRepo.findOne(userName);
			System.out.println(user.getUsername());

		} catch (Exception e) {
			fail();
		}

	}

}
