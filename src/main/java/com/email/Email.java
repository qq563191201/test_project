package com.email;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email {
    public static void main(String[] args){
//    	String host = "smtp.sina.com"; // smtp服务器
//        String user = "qq563191201"; // 用户名
//        String pwd = "a47831128"; // 密码
//        String from = "qq563191201@sina.com"; // 发件人地址
//        String to = "563191201@qq.com"; // 收件人地址
//    	String subject = "测试邮件";
//    	String content = "<a href='http://fanyi.baidu.com/translate?query=&keyfrom=baidu&smartresult=dict&lang=auto2zh#zh/en/%E9%AA%8C%E8%AF%81'>测试</a>";
//    	MailSenderInfo info = new MailSenderInfo(host, "25", from,
//    			user, to, user, pwd, subject, content, null);
//    	new Email().sendHtml(info);
//    	System.out.println("\u591A\u5F00\u670D\u52A1\u7684\u65F6\u5019\u8FD9\u4E2A\u8981\u552F\u4E00");
//    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    	String date = sdf.format(new Date(1453347581*1000L));
    	System.out.println(System.currentTimeMillis()); 
    }
    public void send(MailSenderInfo info) {
    	Properties props = new Properties();
	     // 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
	     props.put("mail.smtp.host", info.getMailServerHost());
	     // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
	     props.put("mail.smtp.auth", "true");
	     // 用刚刚设置好的props对象构建一个session
	     Session session = Session.getDefaultInstance(props);
	     // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
	     // 用（你可以在控制台（console)上看到发送邮件的过程）
	     session.setDebug(true);
	     // 用session为参数定义消息对象
	     MimeMessage message = new MimeMessage(session);
	     try {
	      // 加载发件人地址
	      message.setFrom(new InternetAddress(info.getFromAddress()));
	      // 加载收件人地址
	      message.addRecipient(Message.RecipientType.TO, new InternetAddress(info.getToAddress()));
	      // 加载标题
	      message.setSubject(info.getSubject());
	      // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
	      Multipart multipart = new MimeMultipart();
	
	      // 设置邮件的文本内容
	      BodyPart contentPart = new MimeBodyPart();
	      contentPart.setText(info.getContent());
	      multipart.addBodyPart(contentPart);
	      
	      // 添加附件
	      //BodyPart messageBodyPart = new MimeBodyPart();
	      //DataSource source = new FileDataSource(affix);
	      // 添加附件的内容
	      //messageBodyPart.setDataHandler(new DataHandler(source));
	      // 添加附件的标题
	      // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
	      //sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
	      //messageBodyPart.setFileName("=?GBK?B?"+ enc.encode(affixName.getBytes()) + "?=");
	      //multipart.addBodyPart(messageBodyPart);
	      
	      // 将multipart对象放到message中
	      message.setContent(multipart);
	      // 保存邮件
	      message.saveChanges();
	      // 发送邮件
	      Transport transport = session.getTransport("smtp");
	      // 连接服务器的邮箱
	      transport.connect(info.getMailServerHost(), info.getUserName(), info.getPassword());
	      // 把邮件发送出去
	      transport.sendMessage(message, message.getAllRecipients());
	      transport.close();
	     } catch (Exception e) {
	    	 e.printStackTrace();
	     }
    }
    
    public void sendHtml(MailSenderInfo info) {
    	Properties props = new Properties();
	     // 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
	     props.put("mail.smtp.host", info.getMailServerHost());
	     // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
	     props.put("mail.smtp.auth", "true");
	     // 用刚刚设置好的props对象构建一个session
	     Session session = Session.getDefaultInstance(props);
	     // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
	     // 用（你可以在控制台（console)上看到发送邮件的过程）
	     session.setDebug(true);
	     // 用session为参数定义消息对象
	     MimeMessage message = new MimeMessage(session);
	     try {
	      // 加载发件人地址
	      message.setFrom(new InternetAddress(info.getFromAddress()));
	      // 加载收件人地址
	      message.addRecipient(Message.RecipientType.TO, new InternetAddress(info.getToAddress()));
	      // 加载标题
	      message.setSubject(info.getSubject());
	      // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
	      Multipart multipart = new MimeMultipart();
	
	      // 设置邮件的文本内容
	      BodyPart contentPart = new MimeBodyPart();
	      contentPart.setContent(info.getContent(), "text/html; charset=utf-8");
	      multipart.addBodyPart(contentPart);
	      
	      // 添加附件
	      //BodyPart messageBodyPart = new MimeBodyPart();
	      //DataSource source = new FileDataSource(affix);
	      // 添加附件的内容
	      //messageBodyPart.setDataHandler(new DataHandler(source));
	      // 添加附件的标题
	      // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
	      //sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
	      //messageBodyPart.setFileName("=?GBK?B?"+ enc.encode(affixName.getBytes()) + "?=");
	      //multipart.addBodyPart(messageBodyPart);
	      
	      // 将multipart对象放到message中
	      message.setContent(multipart);
	      // 保存邮件
	      message.saveChanges();
	      // 发送邮件
	      Transport transport = session.getTransport("smtp");
	      // 连接服务器的邮箱
	      transport.connect(info.getMailServerHost(), info.getUserName(), info.getPassword());
	      // 把邮件发送出去
	      transport.sendMessage(message, message.getAllRecipients());
	      transport.close();
	     } catch (Exception e) {
	    	 e.printStackTrace();
	     }
    }
}
