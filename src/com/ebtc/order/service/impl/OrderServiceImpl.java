package com.ebtc.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebtc.base.exception.BusinessException;
import com.ebtc.base.pagination.PaginationInfo;
import com.ebtc.base.pagination.PaginationResult;
import com.ebtc.base.service.impl.GenericServiceImpl;
import com.ebtc.order.dao.OrderDao;
import com.ebtc.order.pojo.Order;
import com.ebtc.order.service.OrderService;

/**
 * 
 * @ClassName: OrderServiceImpl 
 * @Description: 委托订单service实现类 
 * @author Wayga_Chan 553806198@qq.com 
 * @date 2013-5-26 上午10:49:31 
 *
 */
@Service("orderService")
public class OrderServiceImpl extends GenericServiceImpl<Order, OrderDao>
		implements OrderService {

	@Autowired
	private OrderDao orderDao;
	
	@Override
	public OrderDao getDao() {
		return orderDao;
	}

	@Override
	public PaginationResult<Order> queryMyOrderList(Integer id,
			PaginationInfo pageInfo) throws BusinessException {
		Order order = new Order();
		order.setUserId(id);
		return orderDao.query(order, pageInfo);
//		return orderDao.queryMyOrderList(id,pageInfo);
	}


}
