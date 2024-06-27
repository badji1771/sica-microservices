package com.formation.banque.adapter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CommunicationConfig {

	@Autowired
	RestTemplateBuilder builder;

	@Value("${pays-service.url}")
	String paysServiceUrl;
	
	@Bean
	@LoadBalanced
	RestTemplate PaysRestTemplate() {
		return builder.rootUri(paysServiceUrl).build();
	}
}
