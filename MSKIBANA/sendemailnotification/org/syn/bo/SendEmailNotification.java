package org.syn.bo;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.syn.bo.component.soa.core.ServiceConnector;
import org.syn.bo.component.soa.model.RequestData;
import org.syn.bo.component.soa.security.model.SecurityContext;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@RestController
public class SendEmailNotification {
	@Autowired
	private ServiceConnector connector;
	@Autowired
	private SecurityContext securityContext;
	
	
	@RequestMapping("/sendemailnotification")
	public String sendemailnotification(RequestData data){
		System.out.println("New sendemailnotification COMPONENT");
		
		String inputdata=data.getData();
		JSONObject inputJson = new JSONObject(inputdata);
		String serviceName=inputJson.getString("serviceName");
		long timeTaken=inputJson.getLong("timeTaken");
		String toEmail=inputJson.getString("toEmail");
		
		final String username = "shaik.naimuddin@syncoms.com";
		final String password = "";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "outlook.office365.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("shaik.naimuddin@syncoms.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			message.setSubject("Elastic Search-Alert");
			message.setText("Dear Team," + "\n\n\n  "+serviceName.toUpperCase()+" Service is taking more than "+timeTaken+" milliseconds to execute ");

			Transport.send(message);

			System.out.println("Email Sent");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
		
		
		return "emailnotificationsent";
		
	}
}
