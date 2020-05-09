package com.chandan.learning.limitsservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chandan.learning.limitsservice.bean.LimitService;
import com.chandan.learning.limitsservice.config.Configuration;

@RestController
public class LimitResource
{
	@Autowired
	private Configuration config;  
	
	@GetMapping(path = "/limits")
	public LimitService getLimits()
	{
		return new LimitService(config.getMinimum(), config.getMaximum());
	}
}
