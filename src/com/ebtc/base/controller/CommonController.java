package com.ebtc.base.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ebtc.account.pojo.BtcAccount;
import com.ebtc.account.pojo.CnyAccount;
import com.ebtc.account.service.BtcAccountService;
import com.ebtc.account.service.CnyAccountService;
import com.ebtc.base.pagination.PaginationInfo;
import com.ebtc.base.pagination.PaginationInfo.SortOrder;
import com.ebtc.base.pagination.PaginationResult;
import com.ebtc.common.constants.Constants;
import com.ebtc.order.pojo.Order;
import com.ebtc.order.service.OrderService;
import com.ebtc.user.pojo.User;

/**
 * 
 * @ClassName: CommonController 
 * @Description: 通用Controller,用于处理菜单的链接
 * @author Wayga_Chan 553806198@qq.com 
 * @date 2013-5-16 下午9:59:58 
 *
 */
@Controller
public class CommonController extends BaseController {

	private Logger log = Logger.getLogger(CommonController.class);
	
	@Autowired
	private BtcAccountService btcAccountService;
	@Autowired
	private CnyAccountService cnyAccountService;
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/main")
	public String main(){
		return "main";
	}
	
	@RequestMapping(value="/index",method=RequestMethod.POST)
	public String dashboard(){
		String uri = request.getRequestURI().toString();
		return "index";
	}
	@RequestMapping(value="/trade",method=RequestMethod.POST)
	public String trade(){
		return "trade/trade";
	}
	
	@RequestMapping(value="/myAcc",method=RequestMethod.POST)
	public String myAcc() throws Exception{
		try{
		User user = (User) session.getAttribute(Constants.LOGIN_USER);
		//查比特币账户
		BtcAccount btcAccount = new BtcAccount();
		btcAccount.setUserId(user.getId());
		btcAccount = btcAccountService.find(btcAccount);
		request.setAttribute("btcAccount", btcAccount);
		//查人民币账户
		CnyAccount cnyAccount = new CnyAccount();
		cnyAccount.setUserId(user.getId());
		cnyAccount = cnyAccountService.find(cnyAccount);
		request.setAttribute("cnyAccount", cnyAccount);
		//查我的委托单
		//设置分页参数
		PaginationInfo pageInfo = new PaginationInfo();
		pageInfo.setSortCol("create_time");
		pageInfo.setSortOrder(SortOrder.desc);
		//查询
		PaginationResult<Order> page = orderService.queryMyOrderList(user.getId(),pageInfo);
		List<Order> myOrderList = page.getData();
		request.setAttribute("myOrderList", myOrderList);
		pageInfo = page.getPaginationInfo();
		request.setAttribute("pageInfo", pageInfo);
		
		//实时价格放在application中。到页面之后才进行请求
		}catch(Exception e){
			log.debug(e);
			throw e;
		}
		return "myAcc/myAcc";
	}
}
