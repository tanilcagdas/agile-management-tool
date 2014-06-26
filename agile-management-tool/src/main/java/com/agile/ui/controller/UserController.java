package com.agile.ui.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import com.agile.beans.UserData;
import com.agile.beans.security.AuthorityData;
import com.agile.beans.security.AuthorityServiceBean;
import com.agile.beans.servicebean.UserServiceBean;
import com.agile.converter.AdminConverter;
import com.agile.converter.DeveloperConverter;
import com.agile.converter.ProductOwnerConverter;
import com.agile.converter.ScrumMasterConverter;
import com.agile.repository.security.AuthorityRepository;
import com.agile.service.UserAuthenticationService;
import com.agile.service.UserService;
import com.agile.ui.util.AgileConstants;

@Scope("session")
@Controller
public class UserController {
	@Autowired
	UserAuthenticationService uas;

	@Autowired
	UserService userService;

	@Autowired
	AuthorityRepository authRep;
	
	@Autowired
	private ProductOwnerConverter productOwnerConverter;
	
	@Autowired
	private ScrumMasterConverter scrumMasterConverter;
	
	@Autowired
	private DeveloperConverter developerConverter;
	
	@Autowired
	private AdminConverter adminConverter;

	private String userName;
	
	private UserServiceBean selectedUser;
	
	private String password;
	
	private List<UserServiceBean> userList;
	
	private String userType;

	private FacesMessage fm = null;

	@PostConstruct
	public void init() {
		findAll();

	}

	public String find(String userName) {
		UserDetails user = uas.loadUserByUsername(userName);
		setUserName(user.getUsername());
		setSelectedUser(userService.findOne(userName));
		return "";
	}

	public String add() {
		UserServiceBean user = new UserServiceBean();
		user.setAuthority(new AuthorityServiceBean());
		user = encryptPassword(user, "password");
		userList.add(user);
		return "";
	}

	public String find() {
		UserDetails user = uas.loadUserByUsername(getUserName());
		setUserName(user.getUsername());
		setSelectedUser(userService.findOne(userName));
		return "";

	}

	public String findAll() {
		List<UserServiceBean> userList = userService.findAll();
		setUserList(userList);
		return "";

	}

	public String save() {
		for (UserServiceBean user : userList) {
			userService.save(user);
			findAll();
		}
		FacesMessage fm = new FacesMessage("save succesfull", null);
		FacesContext.getCurrentInstance().addMessage(null, fm);
		return "";
	}

	public String delete(Integer key) {
		String name = userList.get(key).getUsername();
		userService.delete(userList.get(key));
		findAll();
		fm = new FacesMessage(name + " deleted succesfully", null);
		FacesContext.getCurrentInstance().addMessage(null, fm);
		return "";
	}

	public String resetPassword(Integer key) {
		UserServiceBean user = userList.get(key);
		userService.save(encryptPassword(user, "password"));
		return "";
	}

	public String saveOne() {
		userService.save(selectedUser);
		return "";
	}

	public String changePassword() {
		userService.save(encryptPassword(getSelectedUser(), getPassword()));
		return "";
	}

	public UserServiceBean encryptPassword(UserServiceBean user, String password) {
		MessageDigestPasswordEncoder encoder = new MessageDigestPasswordEncoder("MD5");
		user.setPassword(encoder.encodePassword(password, "agile"));
		return user;
	}

	public String getLoggedinUser() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		setSelectedUser(userService.findOne(ctx.getExternalContext().getRemoteUser()));
		return "";
	}

	public Object[] getUserArray() {
		List<String> userNameList = new ArrayList<>();
		userNameList.add("select one");
		for (UserServiceBean user : userService.findAll()) {
			userNameList.add(user.getUsername());
		}
		return userNameList.toArray();
	}

	public List<String> getUserAuthorityArray() {
		List<String> userAuthorityList = new ArrayList<>();
		for (AuthorityData aut : authRep.findAll()) {
			userAuthorityList.add(aut.getName());
		}
		return userAuthorityList;
	}

	public List<String> getUserTypeList() {
		ArrayList<String> userTypeList = new ArrayList<String>(Arrays.asList(AgileConstants.ADMIN_USER_TYPE, AgileConstants.DEVELOPER_USER_TYPE, AgileConstants.PRODUCT_OWNER_USER_TYPE, AgileConstants.SCRUM_MASTER_USER_TYPE));
		return userTypeList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserAuthenticationService getUas() {
		return uas;
	}

	public void setUas(UserAuthenticationService uas) {
		this.uas = uas;
	}

	public List<UserServiceBean> getUserList() {
		return userList;
	}

	public void setUserList(List<UserServiceBean> userList) {
		this.userList = userList;
	}

	public UserServiceBean getSelectedUser() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		find(ctx.getExternalContext().getRemoteUser());
		return selectedUser;
	}

	public void setSelectedUser(UserServiceBean selectedUser) {
		this.selectedUser = selectedUser;
	}
	
	public void handleUserTypeSelect(Integer key) {
		UserServiceBean user = userList.get(key);
		if(getUserType().equals(AgileConstants.PRODUCT_OWNER_USER_TYPE)){
			user = productOwnerConverter.convert(user);
			userList.set(key,user);
		}else if(getUserType().equals(AgileConstants.SCRUM_MASTER_USER_TYPE)){
			user = scrumMasterConverter.convert(user);
			userList.set(key,user);
		}else if(getUserType().equals(AgileConstants.DEVELOPER_USER_TYPE)){
			user = developerConverter.convert(user);
			userList.set(key,user);
		}else if(getUserType().equals(AgileConstants.ADMIN_USER_TYPE)){
			user = adminConverter.convert(user);
			userList.set(key,user);
		}
	}
	
	public void userSelection(SelectEvent event) {
		FacesMessage msg = new FacesMessage("User Selected", ((UserData) event.getObject()).getUsername());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Long getCount() {
		return userService.count();
	}

}
