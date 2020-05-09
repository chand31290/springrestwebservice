package com.chandan.learning.springrestwebservice.resource;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.chandan.learning.springrestwebservice.bean.HelloWorldBean;

@RestController
public class HelloWorldResource
{
	@Autowired
	private MessageSource messageSource;

	@GetMapping(path = "/hello-world")
	public String helloWorld(Locale locale)
	{
		return messageSource.getMessage("good.morning.message", null, locale);
	}

	@GetMapping(path = "/hello-world-bean/{name}")
	public HelloWorldBean helloWorldBean(@PathVariable String name)
	{
		return new HelloWorldBean(String.format("Hello-World,%1$s !", name));
	}

}
