package com.micromakers.notification.model;

public class User {
	
	private Long id;
	private String username;
	private String password;
	private String hobby;
	
	
	public User(){
		
	}
	public User( String username, String password, String hobby) {
		super();
		this.username = username;
		this.password = password;
		this.hobby=hobby;
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
	
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
}
