package gov.bnm.email.smtp;

import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends javax.mail.Authenticator
{
	String username;
	String password;
	
	public SMTPAuthenticator(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	
    public PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication(username, password);
    }
}