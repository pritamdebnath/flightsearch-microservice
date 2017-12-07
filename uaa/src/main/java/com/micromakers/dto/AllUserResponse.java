package com.micromakers.dto;

import java.util.ArrayList;
import java.util.List;

import com.micromakers.entity.User;

public class AllUserResponse {
	
	private List<User> userList ;

	public List<User> getUserList() {
		if(userList==null){
			userList = new ArrayList<>();
		}
		return userList;
	}	

}
