package com.micromakers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micromakers.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{
	
	User findByUsername(String username);

}
