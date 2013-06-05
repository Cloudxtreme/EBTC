package com.ebtc.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebtc.base.service.impl.GenericServiceImpl;
import com.ebtc.order.dao.TradeDao;
import com.ebtc.order.pojo.Trade;
import com.ebtc.order.service.TradeService;

@Service
public class TradeServiceImpl extends GenericServiceImpl<Trade,TradeDao> implements TradeService{

	@Autowired
	private TradeDao tradeDao;
	
	@Override
	public TradeDao getDao() {
		return tradeDao;
	}

}
