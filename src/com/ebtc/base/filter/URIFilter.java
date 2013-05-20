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

import org.apache.log4j.Logger;

/**
 * Servlet Filter implementation class URIFilter
 */
public class URIFilter implements Filter {

	private HashSet<String> URIRegistration;
	
	private Logger log = Logger.getLogger(URIFilter.class);
    /**
     * Default constructor. 
     */
    public URIFilter() {
    	
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
		
		String uri_404 = "/WEB-INF/pages/error/404.jsp";
		
		String uri = request.getRequestURI().toString();
		uri = uri.substring(5);
		//TODO 判断是否为静态文件
		
		if(URIRegistration.contains(uri)){
			chain.doFilter(request, response);
		}else{
			request.getRequestDispatcher(uri_404).forward(request, response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		URIRegistration = new HashSet<String>();
		try{
			BufferedReader br = new BufferedReader(new FileReader(this.getClass().getResource("/URI_registration.cot").toString().substring(5)));
			String line = null;
	    	while((line = br.readLine()) != null){
	    		line = line.trim();
	    		if(!line.startsWith("#")){
	    			URIRegistration.add(line);
	    		}
	    	}
    	}catch(IOException e){
    		log.debug("URI过滤器初始化失败!");
    	}
	}

}
