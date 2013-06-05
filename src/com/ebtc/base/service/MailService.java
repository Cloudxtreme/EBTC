package com.ebtc.base.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

public interface MailService {

	void sendMail(String mailSubject, String mailBody, String mailTo)
			throws MessagingException, UnsupportedEncodingException;

}
