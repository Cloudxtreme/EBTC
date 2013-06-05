package com.ebtc.order.dao;

import org.springframework.dao.DataAccessException;

import com.ebtc.base.dao.GenericDao;
import com.ebtc.base.pagination.PaginationInfo;
import com.ebtc.base.pagination.PaginationResult;
import com.ebtc.order.pojo.Order;


/**
 *
 * @ClassName: OrderDao
 * @author Wayga_Chan 553806198@qq.com
 * @date 2013-05-26 10:45:26
 *
 */

public interface OrderDao extends GenericDao<Order>{

//	PaginationResult<Order> queryMyOrderList(Integer id, PaginationInfo pageInfo) throws DataAccessException;

}