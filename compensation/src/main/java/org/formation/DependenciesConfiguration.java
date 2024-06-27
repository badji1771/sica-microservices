package org.formation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class DependenciesConfiguration {
    @Autowired
    RestTemplateBuilder builder;


    @Bean
    @LoadBalanced
    RestTemplate banqueRestTemplate() {
        //http://localhost:8091/api/banques
        //http://banque/api/banques
        return builder.rootUri("http://banque").build();

    }

    @Bean
    @LoadBalanced
    RestTemplate operationRestTemplate() {
        //http://localhost:8091/api/banques
        //http://banque/api/banques
        return builder.rootUri("http://operation").build();

    }
}
