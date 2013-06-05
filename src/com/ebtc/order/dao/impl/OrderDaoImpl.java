package com.ebtc.order.dao.impl;

import java.util.HashMap;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ebtc.base.dao.impl.GenericDaoImpl;
import com.ebtc.base.pagination.PaginationInfo;
import com.ebtc.base.pagination.PaginationResult;
import com.ebtc.order.dao.OrderDao;
import com.ebtc.order.pojo.Order;

/**
 *
 * @ClassName: OrderDaoImpl
 * @author Wayga_Chan 553806198@qq.com
 * @date 2013-05-26 10:45:26
 *
 */
@Repository
public class OrderDaoImpl extends GenericDaoImpl<Order>  implements OrderDao{

//	@Override
//	public PaginationResult<Order> queryMyOrderList(Integer id,
//			PaginationInfo pageInfo) throws DataAccessException {
//		String mapperLocation = "com.ebtc.order.mapper.QueryMyOrderList.queryMyOrderList";
//		HashMap param = new HashMap();
//		param.put("id", id);
//		return executeQuery(mapperLocation, param, pageInfo);
//	}

}