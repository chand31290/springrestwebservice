package com.chandan.learning.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.chandan.learning.currencyconversionservice.bean.CurrencyConversionBean;

@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy
{
	@GetMapping(path = "currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean getExchangeRate(@PathVariable(name = "from") String from, @PathVariable(name = "to") String to);
}
