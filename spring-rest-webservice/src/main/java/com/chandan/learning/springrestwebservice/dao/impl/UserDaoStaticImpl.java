package com.chandan.learning.springrestwebservice.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.chandan.learning.springrestwebservice.bean.User;
import com.chandan.learning.springrestwebservice.dao.UserDao;

/**
 * @author chandan This is a simple Class which holds static users objects
 */
@Component
public class UserDaoStaticImpl implements UserDao
{
	private static List<User> users = new ArrayList<>();

	static
	{
		users.add(new User(1, "TheMandalorian", new Date()));
		users.add(new User(2, "BabyYoda", new Date()));
		users.add(new User(3, "CaraDune", new Date()));
	}

	private static int usersCount = users.size();

	@Override
	public List<User> findAll()
	{
		return users;
	}

	@Override
	public User save(User user)
	{
		if (user.getId() == null)
		{
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}

	@Override
	public Optional<User> findOne(int id)
	{
		return users.stream().filter(user -> user.getId() == id).findFirst();
	}

	@Override
	public boolean deleteById(int id)
	{
		return users.removeIf(user -> user.getId() == id);
	}

}
