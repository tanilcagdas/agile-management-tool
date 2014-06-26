package com.agile.test;

import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

public class TestAll {
	Set<TesterIF> testers = new HashSet<TesterIF>(){
	};
	
	public void test() {
		TesterIF userTest = new UserRepositoryTest();
		TesterIF userStoryTest = new UserStoryRepositoryTest();
		testers.add(userTest);
		testers.add(userStoryTest);
		try {
		for (TesterIF tester : testers) {
			tester.test();
		}
	

		
	
		} catch (Exception e) {
			fail();
		}
		
		
	}

}
