package com.ebtc.user.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ebtc.base.controller.BaseController;
import com.ebtc.base.exception.BusinessException;
import com.ebtc.common.constants.Constants;
import com.ebtc.common.utils.StringUtils;
import com.ebtc.user.pojo.User;
import com.ebtc.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

	@Autowired
	private UserService userService;
	
	private Logger log = Logger.getLogger(UserController.class);

	/**
	 * 
	 * @Title	toRegist 
	 * @Description	返回注册页面
	 * @return String
	 */
	@RequestMapping(value="toRegist")
	public String toRegist(){
		return "regist";
	}
	
	/**
	 * 
	 * @Title	uniqueChecker 
	 * @Description	检测唯一性
	 * @param type
	 * @param value
	 * @return String
	 */
	@RequestMapping(value="uniqueChecker")
	public String uniqueChecker(@RequestParam("type")String type,@RequestParam("value")String value){
		User user = new User();
		String result = "false";
		if(StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)){
			if(type.equals("username")){
				user.setUsername(value);
			}else if(type.equals("email")){
				user.setEmail(value);
			}else if(type.equals("mobile")){
				user.setMobile(value);
			}
			try {
				if(userService.isUnique(user)){
					result = "true";
				}
			} catch (BusinessException e) {
				log.debug("判断唯一性出错!");
				log.debug(e);
			}
		}
		request.setAttribute(Constants.OUTPUT, result);
		return Constants.OUTPUT;
	}
	
	/**
	 * 
	 * @Title	register 
	 * @Description	注册
	 * @param captcha
	 * @return String
	 */
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String register(User user ,@RequestParam("captcha")String captcha){
		try {
			String sessionCaptcha = (String) session.getAttribute(Constants.CAPTCHA);
			if(StringUtils.notBlankEquals(sessionCaptcha, captcha)){
				
				if(null != user){
					userService.createNewUser(user);
				}else{
					
				}
				
			}else{
				
			}
			
		} catch (BusinessException e) {
			log.debug("创建用户失败");
			log.debug(e);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @Title	login 
	 * @Description	用户登录
	 * @return
	 * @throws BusinessException String
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login() throws BusinessException{
		User user = new User();
		user.setUsername("wayga");
		user.setPassword("123");
		user.setPaymentCode("123");
		user.setMobile("123");
		user.setNickname("nick");
		user.setEmail("email");
		user.setState("1");
		user.setType("1");
		user.setCreateTime(new Date());
		userService.add(user);
		return "login";
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
}
