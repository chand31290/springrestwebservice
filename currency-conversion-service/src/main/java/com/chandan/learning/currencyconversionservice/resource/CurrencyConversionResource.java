package com.chandan.learning.currencyconversionservice.resource;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.chandan.learning.currencyconversionservice.CurrencyExchangeServiceProxy;
import com.chandan.learning.currencyconversionservice.bean.CurrencyConversionBean;

@RestController
public class CurrencyConversionResource
{
	@Autowired
	private CurrencyExchangeServiceProxy  currencyExchangeServiceProxy;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping(path = "/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity)
	{

		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8001/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,
				uriVariables);

		CurrencyConversionBean currencyConversionBean = responseEntity.getBody();

		return new CurrencyConversionBean(currencyConversionBean.getId(), from, to,
				currencyConversionBean.getExchangeRate(), quantity,
				quantity.multiply(currencyConversionBean.getExchangeRate()), currencyConversionBean.getPort());
	}
	
	@GetMapping(path = "/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyViaFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity)
	{

		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);


		CurrencyConversionBean currencyConversionBean = currencyExchangeServiceProxy.getExchangeRate(from, to);
		
		logger.info("{}", currencyConversionBean);

		return new CurrencyConversionBean(currencyConversionBean.getId(), from, to,
				currencyConversionBean.getExchangeRate(), quantity,
				quantity.multiply(currencyConversionBean.getExchangeRate()), currencyConversionBean.getPort());
	}
}
