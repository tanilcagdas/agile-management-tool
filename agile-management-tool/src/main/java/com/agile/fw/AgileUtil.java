package com.agile.fw;

import java.util.Date;

import com.agile.ui.util.AgileConstants;

public class AgileUtil {
	
	public static Date getToday(){
		Date today = new Date((new Date().getTime()/AgileConstants.DAY)*AgileConstants.DAY-3*60*60*1000);
		return today;
	}
	
	public static Date getAnotherDay(int days){
		Date anotherDay = new Date(((new Date().getTime()+days*AgileConstants.DAY)/AgileConstants.DAY)*AgileConstants.DAY-3*60*60*1000);
		return anotherDay;
	}
}
