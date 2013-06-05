package com.ebtc.base.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.ebtc.base.service.EmailAutherticator;
import com.ebtc.base.service.MailService;
/**
 * 
 * @ClassName: MailService 
 * @Description: 邮件服务类,已经在spring中做声明式配置了
 * @author Wayga_Chan 553806198@qq.com 
 * @date 2013-5-21 下午9:13:26 
 *
 */
public class MailServiceImpl implements MailService{
	private String host;
	private String personalName = "易比特";
	//用户名
	private String username;
	//密码
	private String password;

	@Override
	public void sendMail(String mailSubject,String mailBody,String mailTo) throws MessagingException, UnsupportedEncodingException{
		Properties props = new Properties();									// 获取系统环境
		Authenticator auth = new EmailAutherticator(username,password);							// 进行邮件服务用户认证
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props, auth);
		MimeMessage message = new MimeMessage(session);							// 设置session,和邮件服务器进行通讯
		message.setSubject(mailSubject);										// 设置邮件主题
//		message.setContent(mailBody, "text/html;charset=UTF-8");
		message.setText(mailBody);												// 设置邮件内容
		message.setSentDate(new Date());										// 设置邮件发送时期
		Address address = new InternetAddress(username, personalName);
		message.setFrom(address);												// 设置邮件发送者的地址
		Address toaddress = new InternetAddress(mailTo);						// 设置邮件接收者的地址
		message.addRecipient(Message.RecipientType.TO, toaddress);
		Transport.send(message);
		System.out.println("Send Mail Ok!");
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPersonalName() {
		return personalName;
	}

	public void setPersonalName(String personalName) {
		this.personalName = personalName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
