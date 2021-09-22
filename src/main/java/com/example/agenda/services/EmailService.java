package com.example.agenda.services;

import com.example.agenda.model.dto.EmailInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	public void enviaEmail(EmailInput email) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();

		mailMessage.setFrom("paulaoliveirastore@gmail.com");
		mailMessage.setSubject(email.getAssunto());
		mailMessage.setText(email.getCorpo());
		mailMessage.setTo(email.getDestinatario());
		mailSender.send(mailMessage);
	}
}
