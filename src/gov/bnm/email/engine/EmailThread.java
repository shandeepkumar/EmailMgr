package gov.bnm.email.engine;



import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import gov.bnm.email.Util.CommonUtil;
import gov.bnm.email.Util.FileUtil;
import gov.bnm.email.bean.EmailBean;
import gov.bnm.email.property.PropertyFileConfigManager;
/**
 * @author Ramakrishna Metla
 *	
 */
public class EmailThread implements Runnable{

	private static Properties props;
	private static Logger logger=Logger.getLogger(EmailThread.class);
	
	private EmailBean emailBean;
	
	public EmailBean getEmailBean() {
		return emailBean;
	}

	public void setEmailBean(EmailBean emailBean) {
		this.emailBean = emailBean;
	}
	public void run() {
		logger.info("===== Begin of sendEMail =====");
		try 
		{
			boolean debug = false;
			PropertyFileConfigManager propertyFileConfigManager = PropertyFileConfigManager.getInstance();
			
			props =  propertyFileConfigManager.getConfigFile(FileUtil.RH_LOCATION+FileUtil.RH_PROP_FILE);
			//props =  propertyFileConfigManager.getConfigFile(FileUtil.EAPPS_LOCATION+FileUtil.EAPPS_PROP_FILE);
			Session session;
			
			if(props.getProperty("mail.smtp.auth").equalsIgnoreCase("true")){
			logger.debug("Authenticated...");
				
				
				session = Session.getInstance(props, new javax.mail.Authenticator() 
				{
					protected PasswordAuthentication getPasswordAuthentication() 
					{
						return new PasswordAuthentication(props.getProperty("mail.smtp.user"),props.getProperty("mail.smtp.password"));					
					}
				});				
			}
			else{
				logger.debug("Not Authenticated...");
				session = Session.getInstance(props,null);
			}				
			session.setDebug(debug);
			// create a message
			MimeMessage msg = new MimeMessage(session);
					    
			msg.setHeader("X-Mailer", "eApps");	
			msg.setHeader("Content-Type", "text/plain; charset=UTF-8");
			// set the from address
			InternetAddress addressFrom = new InternetAddress( CommonUtil.getStr(emailBean.getSenderEmail()), CommonUtil.getStr(emailBean.getSenderName()));
			msg.setFrom(addressFrom);
			// set the subject
			msg.setSubject( CommonUtil.getStr(emailBean.getSubject()),"utf-8");
			
			 // Create the message part 
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Fill the message
	         messageBodyPart.setContent(CommonUtil.getStr(emailBean.getMessageBody()), "text/html; charset=utf-8");
	         
	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         if(emailBean.getAttachments() != null && emailBean.getAttachments().size() >0){
		         for(File attachment:emailBean.getAttachments()){
		        	 messageBodyPart = new MimeBodyPart();
		        	 FileDataSource fileDataSource =new FileDataSource(attachment);
		        	 messageBodyPart.setDataHandler(new DataHandler(fileDataSource));
		        	 messageBodyPart.setFileName(attachment.getName());
			         multipart.addBodyPart(messageBodyPart);
		         }
	         }
	         // Send the complete message parts
	         msg.setContent(multipart);
	         
			// set the content
			//msg.setContent( CommonUtil.getStr(emailBean.getMessageBody()), "text/html; charset=utf-8");
			
			
			// set the to address
			InternetAddress[] addressTo = new InternetAddress[emailBean.getReceiverEmails().length];
			
			int count = 0 ;
			for(String str : emailBean.getReceiverEmails()){
				addressTo[count] = new InternetAddress(str);
				count++;
			}
			msg.setRecipients(Message.RecipientType.TO,addressTo);
			
			// Set the cc address
			if (emailBean.getCcReceiverEmails() != null){
				InternetAddress[] addressCc = new InternetAddress[emailBean.getCcReceiverEmails().length];				
				int countCc = 0 ;
				
				for(String str : emailBean.getCcReceiverEmails()){	
					addressCc[countCc] = new InternetAddress(str);
					countCc++;					
				}
				msg.setRecipients(Message.RecipientType.CC ,addressCc);
			}
			
			// Set the bcc address
			if (emailBean.getBccReceiverEmails() != null){
				InternetAddress[] addressBcc = new InternetAddress[emailBean.getBccReceiverEmails().length];				
				int countBcc = 0 ;
				
				for(String str : emailBean.getBccReceiverEmails()){	
					addressBcc[countBcc] = new InternetAddress(str);
					countBcc++;					
				}
				msg.setRecipients(Message.RecipientType.BCC ,addressBcc);
			}
			 
			Transport.send(msg);
			logger.info("===== End of Transport =====. ");
			
		}	catch(Exception ex) {
				logger.error(ex.getMessage(),ex);
			
		}	
		logger.info("===== End of sendEmail =====");
	
	}	

	
}
