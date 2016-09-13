
package com.agile.ui.controller;

import java.io.IOException;
import java.util.Date;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.agile.interfaces.UserServiceIF;

@Controller
@Scope("request")
public class LoginController {
	static Date loginDate = null;
	
	@Autowired
	UserServiceIF userService;

   public static String doLogin() throws IOException, ServletException {
      ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
      RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/ui/login");
      dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
      FacesContext.getCurrentInstance().responseComplete();
      loginDate = new Date();
      return "login";
   }

   public static String doLogout() throws IOException, ServletException {
      ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
      RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/ui/logout");
      dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
      FacesContext.getCurrentInstance().responseComplete();
      FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
      return "logout";
   }

   public static String getUser() {
      FacesContext ctx = FacesContext.getCurrentInstance();
      return "Logged in as \"" + ctx.getExternalContext().getRemoteUser() + "\" at " + loginDate+"  ";
   }
   
   public String getAuthority() {
	      FacesContext ctx = FacesContext.getCurrentInstance();
	      return userService.findOne(ctx.getExternalContext().getRemoteUser()).getAuthority().getName() ;
	   }
}
