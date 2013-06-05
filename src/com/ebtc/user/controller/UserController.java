package com.ebtc.user.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ebtc.account.pojo.BtcAccount;
import com.ebtc.account.pojo.CnyAccount;
import com.ebtc.account.service.BtcAccountService;
import com.ebtc.account.service.CnyAccountService;
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
	@Autowired
	private CnyAccountService cnyAccountService;
	@Autowired
	private BtcAccountService btcAccountService;
	
	private Logger log = Logger.getLogger(UserController.class);

	//注册页面
	private static String regist = "regist";
	//注册成功页面
	private static String registSuccess = "registSuccess";
	
	//跳转到index页
	private static String redirectIndex = "redirect:/index";
	//跳转到myAccount页
	private static String redirectMyAcc = "redirect:/myAcc";
	
	/**
	 * 
	 * @Title	toRegist 
	 * @Description	返回注册页面
	 * @return String
	 */
	@RequestMapping(value="toRegist")
	public String toRegist(){
		return regist;
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
				user.setUsername(value.toLowerCase());
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
	 * @throws Exception 
	 */
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String register(User user ,@RequestParam("captcha")String captcha) throws Exception{
		try {
			String sessionCaptcha = (String) session.getAttribute(Constants.CAPTCHA);
			//校验验证码，正确则继续注册
			if(StringUtils.notBlankEquals(sessionCaptcha, captcha)){
				if(null != user){
					//创建新用户，状态为未激活。创建CNY账户和BTC账户
					int row = userService.createNewUser(user);
					if(row == 1){
						return registSuccess;
					}
				}else{
					//返回注册页面。提示信息填写不完整
					request.setAttribute(Constants.ERROR, "注册失败,信息填写不完整");
				}
				
			}else{
				//返回注册页面。提示验证码错误
				request.setAttribute(Constants.ERROR, "注册失败,验证码错误!");
			}
			
		} catch(Exception e){
			log.debug(e);
			throw e;
		}
		
		return regist;
	}
	
	/**
	 * 
	 * @Title	login 
	 * @Description	用户登录
	 * @return
	 * @throws BusinessException String
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login(User user) throws BusinessException{
		String username = user.getUsername();
		//检查当前是否有登陆用户
		if(session.getAttribute(Constants.LOGIN_USER) != null){
			//返回到我的账户中去
			return redirectMyAcc;
		}
		
		if(StringUtils.isNotBlank(user.getUsername())){
			if(StringUtils.isNotBlank(user.getPassword())){
				//校验密码等
				user = userService.login(user);
				if(user != null){
					//密码正确,存放到sessoin中
					session.setAttribute(Constants.LOGIN_USER, user);
					//查找当前账户信息存放session中
					BtcAccount btcAccount = new BtcAccount();
					btcAccount.setUserId(user.getId());
					btcAccount = btcAccountService.find(btcAccount);
					session.setAttribute(Constants.BTC_ACCOUNT, btcAccount);
					CnyAccount cnyAccount = new CnyAccount();
					cnyAccount.setUserId(user.getId());
					cnyAccount = cnyAccountService.find(cnyAccount);
					session.setAttribute(Constants.CNY_ACCOUNT, cnyAccount);
					//返回到我的账户中去
					return redirectMyAcc;
				}
			}
		}
		session.setAttribute("username", username);
		session.setAttribute(Constants.ERROR, "loginFail");
		return redirectIndex;
	}
	
	/**
	 * 
	 * @Title	logout 
	 * @Description	用户登出
	 * @return String
	 */
	@RequestMapping("logout")
	public String logout(){
		//清除session中登陆用户的值
		session.removeAttribute(Constants.LOGIN_USER);
		//跳到首页
		return redirectIndex;
	}
}
