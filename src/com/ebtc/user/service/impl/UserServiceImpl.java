package com.ebtc.user.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebtc.base.exception.BusinessException;
import com.ebtc.base.service.impl.GenericServiceImpl;
import com.ebtc.common.utils.MD5Utils;
import com.ebtc.user.dao.UserDao;
import com.ebtc.user.pojo.User;
import com.ebtc.user.service.UserService;

@Service
public class UserServiceImpl extends GenericServiceImpl<User,UserDao> implements UserService{

	@Autowired
	private UserDao dao;
	
	@Override
	public UserDao getDao() {
		return dao;
	}

	@Override
	public int createNewUser(User user) throws DataAccessException, BusinessException{
		int row = 0;
		
		//做唯一性校验
		User tem = new User();
		tem.setUsername(user.getUsername());
		if(isUnique(tem)){
			tem.setUsername(null);
			tem.setMobile(user.getMobile());
			if(isUnique(tem)){
				tem.setMobile(null);
				tem.setEmail(user.getEmail());
				if(isUnique(tem)){
					tem = null;
					//密码加密
					user.setPassword(MD5Utils.getCodeMD5(user.getPassword().getBytes()));
					user.setPaymentCode(user.getPassword());
					user.setCreateTime(new Date());
					row = getDao().add(user);
				}
			}
		}
		return row;
	}
}
