package com.micromakers.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

	private Long id;
	@JsonProperty("username")
	private String username;
	private String password;
	@JsonProperty("hobby")
	private String hobby;
	private List<Role> roles;

	public User() {

	}

	public User(String username, String password, String hobby, List<Role> roles) {
		super();
		this.username = username;
		this.password = password;
		this.hobby = hobby;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
}
