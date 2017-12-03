package com.jeikode.microserviceauth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeikode.microserviceauth.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long>{
	
	public User findByEmailAndPassword(String email,String password);

}
