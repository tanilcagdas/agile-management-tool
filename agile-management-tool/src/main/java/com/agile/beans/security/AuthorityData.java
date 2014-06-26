package com.agile.beans.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.agile.interfaces.DataIF;

@Entity(name = "Authority")
@Table(name = "SECURITY_AUTHORITY")
public class AuthorityData implements DataIF, GrantedAuthority {

	@Id
	@Column(name = "NAME", nullable = false, length = 50, unique = true)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return getName();
	}

}
