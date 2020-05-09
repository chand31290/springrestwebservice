package com.chandan.learning.currencyexchangeservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chandan.learning.currencyexchangeservice.bean.ExchangeService;

@Repository
public interface ExchangeServiceRepository extends CrudRepository<ExchangeService, Long>
{
	ExchangeService findByFromAndTo(String from, String to);
}
