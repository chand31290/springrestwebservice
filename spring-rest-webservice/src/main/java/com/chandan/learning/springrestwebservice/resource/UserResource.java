package com.chandan.learning.springrestwebservice.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.chandan.learning.springrestwebservice.bean.User;
import com.chandan.learning.springrestwebservice.dao.UserRepository;
import com.chandan.learning.springrestwebservice.exception.UserNotFoundException;

@RestController
public class UserResource
{

	@Autowired(required = true)
	private UserRepository userDao;
	
	@GetMapping(path = "users/jpa/{id}")
	public User findOne(@PathVariable int id)
	{
		return userDao.findById(id).orElseThrow(() -> new UserNotFoundException("id: " + id));
	}

	@GetMapping(path = "users/jpa/")
	public List<User> findAll()
	{
		return userDao.findAll();
	}

	@PostMapping(path = "users/jpa/")
	public ResponseEntity<Object> save(@Valid @RequestBody User user)
	{
		User savedUser = userDao.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "users/jpa/{id}")
	public void deleteById(@PathVariable int id)
	{
		userDao.deleteById(id);
	}
}
