package com.chandan.learning.springrestwebservice.dao;

import java.util.List;
import java.util.Optional;

import com.chandan.learning.springrestwebservice.bean.User;

public interface UserDao
{
	List<User> findAll();
	
	User save(User user);
	
	Optional<User> findOne(int id);
	
	boolean deleteById(int id);
}
