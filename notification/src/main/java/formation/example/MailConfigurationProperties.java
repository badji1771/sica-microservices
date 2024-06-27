package formation.example;

import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Component
@ConfigurationProperties(prefix = "notification")
@Data
public class MailConfigurationProperties {

	@URL
	private String host;
	@NotNull
	private Integer port;
	private String username, password;

	

}
