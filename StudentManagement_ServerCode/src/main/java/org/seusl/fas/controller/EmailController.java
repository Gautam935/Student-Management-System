package org.seusl.fas.controller;

import org.seusl.dto.email.EmailConfigurationImpl;
import org.seusl.dto.email.Mail;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/email")
public class EmailController {

	@RequestMapping(value="/sendEmail", method=RequestMethod.POST)
	public void sentEmailsController(@RequestBody Mail mail) {
		
		String to = mail.getTo();
		String subject=mail.getSubject();
		
		EmailConfigurationImpl email = new EmailConfigurationImpl();
		email.sendEmail(to, subject);
	}
}
