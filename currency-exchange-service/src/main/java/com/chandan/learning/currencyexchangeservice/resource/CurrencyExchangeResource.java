package com.chandan.learning.currencyexchangeservice.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.chandan.learning.currencyexchangeservice.bean.ExchangeService;
import com.chandan.learning.currencyexchangeservice.repository.ExchangeServiceRepository;

@RestController
public class CurrencyExchangeResource
{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ExchangeServiceRepository exchangeServiceRepository;

	@Autowired
	private Environment environment;

	@GetMapping(path = "currency-exchange/from/{from}/to/{to}")
	public ExchangeService getExchangeRate(@PathVariable String from, @PathVariable String to)
	{
		ExchangeService exchangeService = exchangeServiceRepository.findByFromAndTo(from, to);

		exchangeService.setPort(Integer.valueOf(environment.getProperty("local.server.port")));
		
		logger.info("{}", exchangeService);
		
		return exchangeService;
	}
}
