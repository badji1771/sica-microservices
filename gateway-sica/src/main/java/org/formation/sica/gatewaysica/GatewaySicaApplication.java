package org.formation.sica.gatewaysica;

import com.google.common.base.Strings;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import org.formation.sica.gatewaysica.service.api.IJourneeCompensationApiService;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Push
@Theme(value = "gateway-sica")
@PWA(name = "Gateway Sica", shortName = "Gateway Sica")
@EnableFeignClients
@SpringBootApplication
public class GatewaySicaApplication implements AppShellConfigurator {

    private final Environment environment;
    private final IJourneeCompensationApiService journeeCompensationApiService;

    public GatewaySicaApplication(Environment environment, IJourneeCompensationApiService journeeCompensationApiService) {
        this.environment = environment;
        this.journeeCompensationApiService = journeeCompensationApiService;
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewaySicaApplication.class, args);
    }

    @Bean
    @Primary
    @ConfigurationProperties("main.datasource")
    DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("main.datasource.hikari")
    DataSource dataSource(final DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @EventListener
    public void printApplicationUrl(final ApplicationStartedEvent event) {
        LoggerFactory.getLogger(GatewaySicaApplication.class).info("Application started at "
                + "http://localhost:"
                + environment.getProperty("local.server.port")
                + Strings.nullToEmpty(environment.getProperty("server.servlet.context-path")));
    }

//    @Bean
//    public CommandLineRunner startup(){
//        return args -> journeeCompensationApiService.getJourneCompensationById(1L);
//    }
}
