package gov.bnm.email.test;

import gov.bnm.email.Util.CommonUtil;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
 
public class SendMailTLS  {
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "mail.kgenportal.com");
//		props.put("mail.smtp.socketFactory.port", "2525");
//		props.put("mail.smtp.socketFactory.class",
//				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "25");
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("postmaster@kgenportal.com","welcome1");
				}
			});
//		Session session = Session.getDefaultInstance(props);
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("rama@kgenportal.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("rama@ksasb.com.my"));
			message.setSubject(" Subject SMTP");
		
			 BodyPart messageBodyPart = new MimeBodyPart();

	         // Fill the message
	         messageBodyPart.setContent("Dear shanker, <BR> hi how r u? <img src='http://www.ksasb.com.my/emailtracker/index.php?id=3a01cdc0-e9c4-42c9-be87-4b735221fc8f33d8&feedback=testemailtrack9'/> <br> bbbbbbbbb <br> hhhhhhffff <br> jjllkjhjhjh", "text/html; charset=utf-8");
	         
	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();
	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);
	         message.setContent(multipart);
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}