package com.micromakers.notification.dto;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.micromakers.notification.model.User;

@JsonSerialize
public class AllUserResponse {
	
	private List<User> userList ;

	public List<User> getUserList() {
		return userList;
	}	

}
