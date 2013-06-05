package com.ebtc.user.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.dao.DataAccessException;

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
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 * @throws DataAccessException 
	 */
	public int createNewUser(User user) throws BusinessException, DataAccessException, UnsupportedEncodingException, MessagingException;

	/**
	 * 
	 * @Title	login 
	 * @Description	用户登陆验证
	 * @param user
	 * @return
	 * @throws BusinessException User
	 */
	public User login(User user) throws BusinessException;
	
	/**
	 * 
	 * @Title	updateLastLoginTime 
	 * @Description	更新最后登陆时间
	 * @param id
	 * @return
	 * @throws BusinessException int
	 */
	public int updateLastLoginTime(int id) throws BusinessException;

}
