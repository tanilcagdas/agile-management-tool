package com.agile.interfaces;

import java.util.List;

import com.agile.beans.servicebean.TaskServiceBean;
import com.agile.beans.servicebean.UserServiceBean;


public interface TaskServiceIF extends ServiceIF{
	
	@SuppressWarnings("unchecked")
	List<TaskServiceBean> findByOwner(UserServiceBean user); 

}
