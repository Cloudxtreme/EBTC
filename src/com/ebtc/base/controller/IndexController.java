package com.ebtc.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @ClassName: IndexController 
 * @Description: 跳转到首页
 * @author Wayga_Chan 553806198@qq.com 
 * @date 2013-5-16 下午9:59:58 
 *
 */
@Controller
public class IndexController extends BaseController {

	
	@RequestMapping(value="/index")
	public String dashboard(){
		String uri = request.getRequestURI().toString();
		return "index";
	}
	@RequestMapping("/trade")
	public String trade(){
		return "trade/trade";
	}
	
	@RequestMapping("/myAcc")
	public String myAcc(){
		return "myAcc/myAcc";
	}
}
