package org.seusl.dto.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.seusl.configuration.project.PropertyConfiguration;

public class EmailConfigurationImpl implements EmailConfiguration {

	private Logger log = Logger.getLogger(EmailConfigurationImpl.class);
	
	public void sendEmail(String to, String subject) {
		final Mailer mailer = new Mailer();
		EmailTemplateCreation template = new EmailTemplateCreation();
		PropertyConfiguration instance = new PropertyConfiguration();
		
		mailer.setFrom(instance.getEmail());
		mailer.setUsername(instance.getEmailUsername());
		mailer.setPassword(instance.getEmailPassword());

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		//props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", "25");

		try {
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(mailer.getUsername(), mailer.getPassword());
				}
			});
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mailer.getFrom()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(template.getEmailText());
			Transport.send(message);

		} catch (MessagingException e) {
			log.error(e.getMessage());
		}
	}

}
