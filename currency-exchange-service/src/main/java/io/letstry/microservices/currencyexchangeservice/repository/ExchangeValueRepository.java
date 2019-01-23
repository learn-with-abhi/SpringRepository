package io.letstry.microservices.currencyexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.letstry.microservices.currencyexchangeservice.model.ExchangeValue;

@Repository
public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Integer> {

	public ExchangeValue findByFromAndTo(String from, String to);
	
}
