package com.ebtc.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ebtc.base.controller.BaseController;
import com.ebtc.common.utils.ClassUtils;


public class ServletObjectInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		if(ClassUtils.hasField(handler, "bean")){
			BaseController base = (BaseController)ClassUtils.getFieldValue(handler, "bean"); ;
			base.setRequest(request);
			base.setResponse(response);
			base.setApplication(request.getServletContext());
			base.setSession(request.getSession());
		}
		
		return super.preHandle(request, response, handler);
	}
}
