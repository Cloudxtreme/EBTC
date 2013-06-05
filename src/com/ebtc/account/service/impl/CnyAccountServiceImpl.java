package com.ebtc.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebtc.account.dao.CnyAccountDao;
import com.ebtc.account.pojo.CnyAccount;
import com.ebtc.account.service.CnyAccountService;
import com.ebtc.base.service.impl.GenericServiceImpl;

@Service
public class CnyAccountServiceImpl extends GenericServiceImpl<CnyAccount, CnyAccountDao> implements CnyAccountService {

	@Autowired
	private CnyAccountDao cnyAccountDao;

	@Override
	public CnyAccountDao getDao() {
		return this.cnyAccountDao;
	}

}
