package com.ebtc.base.service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class EmailAutherticator extends Authenticator {
	String username = null;

	String password = null;

	public EmailAutherticator() {
		super();
	}

	public EmailAutherticator(String user, String pwd) {
		super();
		username = user;
		password = pwd;
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}

}
