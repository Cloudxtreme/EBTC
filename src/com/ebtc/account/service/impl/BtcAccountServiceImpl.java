package com.ebtc.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebtc.account.dao.BtcAccountDao;
import com.ebtc.account.pojo.BtcAccount;
import com.ebtc.account.service.BtcAccountService;
import com.ebtc.base.service.impl.GenericServiceImpl;

@Service
public class BtcAccountServiceImpl extends
		GenericServiceImpl<BtcAccount, BtcAccountDao> implements
		BtcAccountService {

	@Autowired
	private BtcAccountDao btcAccountDao;
	
	@Override
	public BtcAccountDao getDao() {
		return this.btcAccountDao;
	}

}
