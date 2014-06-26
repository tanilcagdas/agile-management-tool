package com.agile.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.agile.beans.security.AuthorityData;
import com.agile.interfaces.DataIF;

@Entity(name = "Users")
public class UserData implements UserDetails, DataIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8926504412083715068L;
	@Id
	@Column(name = "USERNAME", nullable = false, unique = true, length = 50)
	private String username;
	@Column(name = "PASSWORD", nullable = false, length = 50)
	private String password;
	@Column(name = "ENABLED", nullable = false)
	private boolean enabled = true;
	@ManyToOne
	@JoinColumn(name = "AUTHORITY", referencedColumnName = "NAME")
	private AuthorityData authority = new AuthorityData();
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

	public AuthorityData getAuthority() {
		return authority;
	}

	public void setAuthority(AuthorityData authority) {
		this.authority = authority;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(getAuthority());
		return authorities;
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