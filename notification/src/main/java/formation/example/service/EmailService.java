package formation.example.service;
 
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import formation.example.dto.Email;
import lombok.extern.java.Log;

@Service
@Log
public class EmailService {

    
    private final JavaMailSender emailSender;
    
 
    public EmailService(JavaMailSender emailSender) {
		super();
		this.emailSender = emailSender;
	}

	public void sendMessage(Email email) {	
    	send(email);
    }

    @Async
    public void sendAsyncMessage(Email email) {
    	send(email);
    }
    
    private void send(Email email) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(email.getMListeExpeditor()); 
        message.setSubject(email.getObjet()); 
        message.setText(email.getContent());
        emailSender.send(message);
    }
}
