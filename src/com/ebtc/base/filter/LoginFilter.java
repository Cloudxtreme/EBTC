package com.ebtc.base.filter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;
import org.apache.log4j.Logger;

import com.ebtc.common.constants.Constants;
import com.ebtc.user.pojo.User;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

	private HashSet<String>	accept;
	
	private Logger log = Logger.getLogger(LoginFilter.class);
	
    /**
     * Default constructor. 
     */
    public LoginFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		
		String uri = request.getRequestURI().toString();
		if(uri.endsWith("/")){
			uri = uri.substring(0, uri.length()-1);
		}
		uri = uri.substring(5);
		//是静态文件
		if(accept.contains(uri)){
			
		}else if(uri.startsWith("/img") || uri.startsWith("/css") || uri.startsWith("/js") || uri.startsWith("/validator")){
			
		}else{
			User user = (User) session.getAttribute(Constants.LOGIN_USER);
			if(user == null){
				response.sendRedirect(request.getContextPath());
				return;
			}
		}
		
		chain.doFilter(request, response);
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		accept = new HashSet<String>();
		try{
			BufferedReader br = new BufferedReader(new FileReader(this.getClass().getResource("/accept.cot").toString().substring(5)));
			String line = null;
	    	while((line = br.readLine()) != null){
	    		line = line.trim();
	    		if(!line.startsWith("#")){
	    			accept.add(line);
	    		}
	    	}
    	}catch(IOException e){
    		log.debug("URI过滤器初始化失败!");
    	}
	}

}
