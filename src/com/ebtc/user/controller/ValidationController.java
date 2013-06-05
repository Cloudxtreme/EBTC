package com.ebtc.user.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ebtc.activate.pojo.Activate;
import com.ebtc.activate.service.ActivateService;
import com.ebtc.base.controller.BaseController;
import com.ebtc.base.exception.BusinessException;
import com.ebtc.common.utils.StringUtils;

@Controller
@RequestMapping("/validator")
public class ValidationController extends BaseController{

	@Autowired
	private ActivateService activateService;
	
	private Logger log = Logger.getLogger(ValidationController.class);
	
	/**
	 * 
	 * @Title	email 
	 * @Description	邮箱验证 
	 * @param value
	 * @param code
	 * @return
	 * @throws BusinessException String
	 */
	@RequestMapping("email/{value}/{code}")
	public String email(@PathVariable("value")String value,@PathVariable("code")String code) throws BusinessException{
		Activate activate = new Activate();
		activate.setValue(value);
		try {
			//查找对应的激活记录
			activate = activateService.find(activate);
			if(StringUtils.notBlankEquals(code, activate.getCode())){
				//验证成功,删除记录
				activateService.delete(activate);
				//返回成功页面
				return "activateSuccess";
			}
		} catch (BusinessException e) {
			log.debug(e);
			throw e;
		}
		//返回首页
		return "index";
	}
}
