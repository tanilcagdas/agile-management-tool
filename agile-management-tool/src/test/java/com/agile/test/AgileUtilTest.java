package com.agile.test;

import junit.framework.TestCase;

import org.junit.Test;

import com.agile.fw.AgileUtil;

public class AgileUtilTest extends TestCase {

	@Test
	public void test() {
		try {
			System.out.println(AgileUtil.getToday());
			System.out.println(AgileUtil.getAnotherDay(1));
			System.out.println(AgileUtil.getAnotherDay(-1));


		} catch (Exception e) {
			fail();
		}

	}

}
