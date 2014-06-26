package com.agile.test;

import java.util.Date;

public class Dividetest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Date test = new Date((new Date().getTime()/(24 * 60 * 60 * 1000))*(24 * 60 * 60 * 1000));
		test = new Date(((new Date().getTime()+(24 * 60 * 60 * 1000))/(24 * 60 * 60 * 1000))*(24 * 60 * 60 * 1000));
//		test = new Date(0);
		System.out.println(test);

	}

}
