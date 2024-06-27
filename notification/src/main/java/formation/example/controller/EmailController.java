package formation.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import formation.example.dto.Email;
import formation.example.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/api/email")
public class EmailController {
	
	private final EmailService emailService;

	public EmailController(EmailService emailService) {
		this.emailService = emailService;
	}
	@PostMapping("/sendAsync")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@Operation(summary = "Send email", description = "Send new email en asynchrone", tags = { "Email" })
	public void sendEmailAsync(@RequestBody Email email) {
		
		

	}
	
	
	@PostMapping("/send")
	@ResponseStatus(code = HttpStatus.CREATED)
	@Operation(summary = "Send email", description = "Send new email synchrone", tags = { "Email" })
	public void sendEmail(@RequestBody Email email) {
		emailService.sendMessage(email);
	}
}
