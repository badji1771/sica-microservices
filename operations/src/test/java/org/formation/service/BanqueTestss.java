package org.formation.service;

import org.assertj.core.api.Assertions;
import org.formation.entities.Banque;
import org.formation.utils.Utulitaires;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class BanqueTestss {

	@Autowired
    CircuitBreakerFactory cbFactory;
	
	@Resource
	RestTemplate restTemplate;
    @Autowired
    private BanqueService banqueService;

	@Test
    public void getAllBanque () {
		CircuitBreaker circuitBreaker = cbFactory.create("circuitbreaker");
		    String url = "https://jsonplaceholder.typicode.com/albums";

		     circuitBreaker.run(() -> restTemplate.getForObject(url, String.class),
		    	      throwable -> "NOK");
	}

	@Test
	public void getBanqueBycode () {
		List<Banque> banques = new ArrayList<>();
		banques.add(new Banque("CI000","CI000", "Agence A00",null, Utulitaires.ETAT.OUVERT.name()));
		Banque banque = banqueService.getBanqueBycode(banques,"CI000" );
		Assertions.assertThat(banque).isNotNull();
	}

}
