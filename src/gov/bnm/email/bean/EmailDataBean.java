package gov.bnm.email.bean;

import java.io.File;
import java.util.List;
import java.util.Map;

public class EmailDataBean {
	String senderEmail;
	String senderName;
	String[] receiverEmails;
	String[] ccReceiverEmails;
	String[]  bccReceiverEmails;
	String templateId;
	List<File> attachments;
	Map<String,String> dataMap;
	public String getSenderEmail() {
		return senderEmail;
	}
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String[] getReceiverEmails() {
		return receiverEmails;
	}
	public void setReceiverEmails(String[] receiverEmails) {
		this.receiverEmails = receiverEmails;
	}
	public String[] getCcReceiverEmails() {
		return ccReceiverEmails;
	}
	public void setCcReceiverEmails(String[] ccReceiverEmails) {
		this.ccReceiverEmails = ccReceiverEmails;
	}
	public String[] getBccReceiverEmails() {
		return bccReceiverEmails;
	}
	public void setBccReceiverEmails(String[] bccReceiverEmails) {
		this.bccReceiverEmails = bccReceiverEmails;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public Map<String, String> getDataMap() {
		return dataMap;
	}
	public void setDataMap(Map<String, String> dataMap) {
		this.dataMap = dataMap;
	}
	public List<File> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<File> attachments) {
		this.attachments = attachments;
	}
	

}
