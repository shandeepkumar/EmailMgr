package gov.bnm.email.adapter;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import gov.bnm.email.Util.CommonUtil;
import gov.bnm.email.Util.FileUtil;

import gov.bnm.email.engine.EmailThread;
import gov.bnm.email.property.PropertyFileConfigManager;
import gov.bnm.email.bean.EmailDataBean;
import gov.bnm.email.bean.EmailBean;

/**
 * @author Ramakrishna Metla
 *	
 */
public class EmailManager {
	private static Logger logger= Logger.getLogger(EmailManager.class);
	
	public void sendEmail(EmailDataBean emailDataBean) {
		boolean errorIndicator = false;
		String errorMsg = "";
		if (emailDataBean != null) {
			if (emailDataBean.getTemplateId() != null) {
				try {
					//Get Template File and replace values 
					PropertyFileConfigManager propertyFileConfigManager = PropertyFileConfigManager.getInstance();
					//Properties props =propertyFileConfigManager.getConfigFile(FileUtil.EAPPS_LOCATION+FileUtil.EAPPS_PROP_FILE);
					Properties props =propertyFileConfigManager.getConfigFile(FileUtil.RH_LOCATION+FileUtil.RH_PROP_FILE);
					String templateMsg = FileUtil.scanFile(props.getProperty("email_template_location")+CommonUtil.getStr(emailDataBean.getTemplateId())+".txt");
	
					if(emailDataBean.getDataMap() != null){
						Map<String,String> dataMap = emailDataBean.getDataMap();
						for (Map.Entry<String, String> entry : dataMap.entrySet()) {
							templateMsg = templateMsg.replaceAll(entry.getKey(),entry.getValue());
						}
					}
					logger.debug("TemplateMsg After Replace::"+templateMsg);
					String[] lines = templateMsg.split(System.getProperty("line.separator"));
					templateMsg ="";
					String subject ="";
					for (String string : lines) {
						if(string.contains("<%SUBJECT%>")){							
							subject =string.replaceAll("<%SUBJECT%>", "");
						}else{
							templateMsg +=string;
						}
					}
					
					EmailBean emailBean = new EmailBean();
					emailBean.setSenderEmail(CommonUtil.getStr(emailDataBean.getSenderEmail()));
					emailBean.setSenderName(CommonUtil.getStr(emailDataBean.getSenderName()));
					emailBean.setReceiverEmails(emailDataBean.getReceiverEmails());
					emailBean.setCcReceiverEmails(emailDataBean.getCcReceiverEmails());
					emailBean.setBccReceiverEmails(emailDataBean.getBccReceiverEmails());
					emailBean.setSubject(CommonUtil.getStr(subject));
					emailBean.setMessageBody(CommonUtil.getStr(templateMsg));
					if(emailDataBean.getAttachments() != null && emailDataBean.getAttachments().size() >0){
						emailBean.setAttachments(emailDataBean.getAttachments());
					}
					//call email thread
					EmailThread em = new EmailThread();
					em.setEmailBean(emailBean);
					Thread thread = new Thread(em);
					thread.start();
					
				} catch (FileNotFoundException e) {
					logger.error(e.getMessage(), new Throwable("Template Not Found"));
				}catch (Exception exception) {
					logger.error(exception.getMessage(),exception);
				}
			} else {
				errorIndicator = true;
				logger.debug("no template ");
				errorMsg ="template file not found ";
			}
		} else {
			errorIndicator = true;
			logger.debug("email Data Bean is null");
			errorMsg ="email Data Bean is null";
		}
		if (errorIndicator) {
			throw new RuntimeException("RuntimeException ::"+errorMsg);			
			
		}

	}

}
