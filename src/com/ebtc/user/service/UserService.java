package com.ebtc.user.service;

import com.ebtc.base.exception.BusinessException;
import com.ebtc.base.service.GenericService;
import com.ebtc.user.dao.UserDao;
import com.ebtc.user.pojo.User;


public interface UserService extends GenericService<User,UserDao>{

	/**
	 * 
	 * @Title	createNewUser 
	 * @Description	新添加1个新用户
	 * @param user
	 * @return int
	 */
	public int createNewUser(User user) throws BusinessException;

}
