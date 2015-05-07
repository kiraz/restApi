package com.renobidz.common.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSender {
	public void sendCCEmail(String sendFromEmail, String sendToEmail,String sendCCToEmail, String subject, String messageBody) {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(sendFromEmail));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
			msg.addRecipient(Message.RecipientType.CC, new InternetAddress(sendCCToEmail));
			msg.setSubject(subject);
			msg.setText(messageBody);
			Transport.send(msg);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public void sendEmail(String sendFromEmail, String sendToEmail, String subject, String messageBody) {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(sendFromEmail));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
			msg.setSubject(subject);
			msg.setText(messageBody);
			Transport.send(msg);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void sendHtmlEmail(String sendFromEmail, String sendToEmail, String subject, String htmlBody) {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(sendFromEmail));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					sendToEmail));
			msg.setSubject(subject);
			Multipart multipart = new MimeMultipart();
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(htmlBody, "text/html");
			multipart.addBodyPart(htmlPart);
			msg.setContent(multipart);
			Transport.send(msg);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
