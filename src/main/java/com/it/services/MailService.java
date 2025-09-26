package com.it.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService 
{
	@Autowired
	private JavaMailSender mailSender;
	
	public void userSaveMailSender(String name , String email, String pwd) throws MessagingException 
	{
		final String msg = "<h1> Hello "+ name +" </h1> <hr>"
				+ "<p> Welcome "+ name +" , Your registration is done. Your Login Password is mention below."
				+ "</p><br>"
				+ "<h4>Password : "+ pwd +"</h4>"
				+ "<b>Please Change the password as soon as possible.</b><br><br>"
				+ "Thanks.."
				+ "<hr>";
		
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

	    helper.setFrom("itstack.promotion@gmail.com");
	    helper.setTo(email);
	    helper.setSubject("Account Register Done");
	    helper.setText(msg, true); 

	    mailSender.send(mimeMessage);
	}
}
