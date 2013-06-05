package com.ebtc.order.service;

import com.ebtc.base.exception.BusinessException;
import com.ebtc.base.pagination.PaginationInfo;
import com.ebtc.base.pagination.PaginationResult;
import com.ebtc.base.service.GenericService;
import com.ebtc.order.dao.OrderDao;
import com.ebtc.order.pojo.Order;
/**
 * 
 * @ClassName: OrderService 
 * @Description: 委托订单service接口
 * @author Wayga_Chan 553806198@qq.com 
 * @date 2013-5-26 上午10:49:14 
 *
 */
public interface OrderService extends GenericService<Order, OrderDao> {

	/**
	 * 
	 * @Title	queryMyOrderList 
	 * @Description	分页查询我的委托单
	 * @param id
	 * @return
	 * @throws BusinessException PaginationResult<Order>
	 */
	public PaginationResult<Order> queryMyOrderList(Integer id,PaginationInfo pageInfo) throws BusinessException;

}
