package com.ebtc.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ebtc.order.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="buy",method=RequestMethod.POST)
	public String buy(){
		return "";
	}
	
	@RequestMapping(value="sell",method=RequestMethod.POST)
	public String sell(){
		return "";
	}
}
