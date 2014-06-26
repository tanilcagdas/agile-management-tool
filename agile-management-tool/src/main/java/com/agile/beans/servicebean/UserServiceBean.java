package com.agile.beans.servicebean;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.agile.beans.security.AuthorityServiceBean;
import com.agile.interfaces.ServiceBeanIF;

public class UserServiceBean implements UserDetails, ServiceBeanIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3847599601459453619L;
	private String username;
	private String password;
	private boolean enabled = true;
	private AuthorityServiceBean authority = new AuthorityServiceBean();
	protected boolean credentialsNonExpired = true;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public AuthorityServiceBean getAuthority() {
		return authority;
	}

	public void setAuthority(AuthorityServiceBean authority) {
		this.authority = authority;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
}