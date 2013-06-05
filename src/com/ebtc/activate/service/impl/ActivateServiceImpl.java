package com.ebtc.activate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebtc.activate.dao.ActivateDao;
import com.ebtc.activate.pojo.Activate;
import com.ebtc.activate.service.ActivateService;
import com.ebtc.base.service.impl.GenericServiceImpl;
@Service
public class ActivateServiceImpl extends GenericServiceImpl<Activate, ActivateDao> implements ActivateService{
	@Autowired
	private ActivateDao activateDao;
	
	@Override
	public ActivateDao getDao() {
		return activateDao;
	}

}
