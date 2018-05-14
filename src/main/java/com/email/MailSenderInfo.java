package com.email;

public class MailSenderInfo {
	// 发送邮件的服务器的IP和端口 
	private String mailServerHost; 
	private String mailServerPort = "25"; 
	// 邮件发送者的地址 
	private String fromAddress; 
	// 邮件发送者的名称
	private String fromName; 
	// 邮件接收者的地址 
	private String toAddress; 
	// 登陆邮件发送服务器的用户名和密码 
	private String userName; 
	private String password; 
	// 邮件主题 
	private String subject; 
	// 邮件的文本内容 
	private String content; 
	// 邮件附件的文件名 
	private String[] attachFileNames;
	public MailSenderInfo(String mailServerHost, String mailServerPort,
			String fromAddress, String fromName, String toAddress,
			String userName, String password, String subject, String content,
			String[] attachFileNames) {
		super();
		this.mailServerHost = mailServerHost;
		this.mailServerPort = mailServerPort;
		this.fromAddress = fromAddress;
		this.fromName = fromName;
		this.toAddress = toAddress;
		this.userName = userName;
		this.password = password;
		this.subject = subject;
		this.content = content;
		this.attachFileNames = attachFileNames;
	}
	public String getMailServerHost() {
		return mailServerHost;
	}
	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}
	public String getMailServerPort() {
		return mailServerPort;
	}
	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String[] getAttachFileNames() {
		return attachFileNames;
	}
	public void setAttachFileNames(String[] attachFileNames) {
		this.attachFileNames = attachFileNames;
	}
}
