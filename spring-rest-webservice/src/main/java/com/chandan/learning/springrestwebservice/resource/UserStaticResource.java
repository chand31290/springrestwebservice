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
import com.chandan.learning.springrestwebservice.dao.UserDao;
import com.chandan.learning.springrestwebservice.exception.UserNotFoundException;

@RestController
public class UserStaticResource
{

	@Autowired(required = true)
	private UserDao userDao;

	/*
	 * @GetMapping(path = "users/{id}") public EntityModel<User>
	 * findOne(@PathVariable int id) { User user =
	 * userDao.findOne(id).orElseThrow(() -> new UserNotFoundException("id: " +
	 * id));
	 * 
	 * EntityModel<User> model = new EntityModel<>(user);
	 * 
	 * WebMvcLinkBuilder linkTo =
	 * WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll(
	 * ));
	 * 
	 * model.add(linkTo.withRel("all-users"));
	 * 
	 * return model; }
	 */
	
	@GetMapping(path = "users/{id}")
	public User findOne(@PathVariable int id)
	{
		return userDao.findOne(id).orElseThrow(() -> new UserNotFoundException("id: " + id));
	}

	@GetMapping(path = "users/")
	public List<User> findAll()
	{
		return userDao.findAll();
	}

	@PostMapping(path = "users/")
	public ResponseEntity<Object> save(@Valid @RequestBody User user)
	{
		User savedUser = userDao.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "users/{id}")
	public void deleteById(@PathVariable int id)
	{
		if (!userDao.deleteById(id))
		{
			throw new UserNotFoundException("id: " + id);
		}
	}
}
