package formation.example.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import formation.example.dto.Email;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class EmailControllerTest {

	@Container
	private static final GenericContainer<?> fakeSmtpContainer = new GenericContainer<>("dthibau/fake-smtp:1.0")
			.withExposedPorts(2525); // Vous pouvez spécifier les ports exposés par le conteneur

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	JavaMailSenderImpl mailSender;
	

	private byte[] content;

	@BeforeEach
	public void setUp() throws JsonProcessingException {
		String[] to = { "toto@gmail.com", "titi@gmail.com" };

		Email email = Email.builder().objet("Test").content("Un contenu").mListeExpeditor(to).build();
		content = mapper.writeValueAsBytes(email);
		
		int smtpPort = fakeSmtpContainer.getMappedPort(2525);
		mailSender.setPort(smtpPort);
		
		
	}

	@Test
	public void testSendEmail() throws Exception {

		mockMvc.perform(post("/api/email/send").contentType(MediaType.APPLICATION_JSON).content(content))
				.andExpect(status().isCreated());

	}

	@Test
	public void testSendAsyncEmail() throws Exception {

		mockMvc.perform(post("/api/email/sendAsync").contentType(MediaType.APPLICATION_JSON).content(content))
				.andExpect(status().isAccepted());

		Thread.sleep(5000);

	}

}
