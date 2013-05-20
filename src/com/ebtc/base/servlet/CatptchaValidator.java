package com.ebtc.base.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ebtc.common.constants.Constants;
import com.ebtc.common.utils.StringUtils;

/**
 * Servlet implementation class ValidateCaptchaServlet
 */
public class CatptchaValidator extends HttpServlet {
	
	private static final long serialVersionUID = 11312313455633L;
	private Logger log = Logger.getLogger(CatptchaValidator.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Boolean isResponseCorrect = Boolean.FALSE;  
		 HttpSession session = request.getSession();   
		 //retrieve the response  
		 String responseCode = request.getParameter(Constants.CAPTCHA);
		 
		 String sessionCode = (String) session.getAttribute(Constants.CAPTCHA);
		 
		 isResponseCorrect = StringUtils.notBlankEquals(responseCode, sessionCode);
		 
		 if(isResponseCorrect){
			 response.getWriter().print("true");
		 }else{
			 response.getWriter().print("false");  
		 }  
		 response.getWriter().flush();
		 response.getWriter().close();
	}

}
