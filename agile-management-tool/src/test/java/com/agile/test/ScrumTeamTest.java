package com.agile.test;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.agile.beans.ScrumTeamData;
import com.agile.repository.ScrumTeamRepository;

public class ScrumTeamTest extends TestCase {
	ScrumTeamRepository scrumTeamRepo;
	String scrumTeamName = "scrumTeam";
	ApplicationContext context = new ClassPathXmlApplicationContext(
			"classpath*:applicationContext.xml");

	@Test
	@Transactional
	public void test() {
		try {

			scrumTeamRepo = context.getBean(ScrumTeamRepository.class);
			ScrumTeamData scrumTeamData = new ScrumTeamData();
			int i = 1;
			String searchScrumTeamName;
			do {
				searchScrumTeamName = scrumTeamName + i;
				i++;
			} while (scrumTeamRepo.findOne(searchScrumTeamName) != null);

			scrumTeamData.setName(searchScrumTeamName);
			scrumTeamRepo.saveAndFlush(scrumTeamData);
//			userRepo.delete(user);

		} catch (Exception e) {
			fail();
		}

	}

}
