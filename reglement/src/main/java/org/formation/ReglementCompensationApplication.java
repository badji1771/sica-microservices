package org.formation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;

@SpringBootApplication
@EnableReactiveMethodSecurity
public class ReglementCompensationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReglementCompensationApplication.class, args);
    }

}
