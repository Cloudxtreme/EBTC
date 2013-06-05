package com.ebtc.user.service.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebtc.account.pojo.BtcAccount;
import com.ebtc.account.pojo.CnyAccount;
import com.ebtc.account.service.BtcAccountService;
import com.ebtc.account.service.CnyAccountService;
import com.ebtc.activate.pojo.Activate;
import com.ebtc.activate.service.ActivateService;
import com.ebtc.base.exception.BusinessException;
import com.ebtc.base.pagination.PaginationInfo;
import com.ebtc.base.service.MailService;
import com.ebtc.base.service.impl.GenericServiceImpl;
import com.ebtc.common.constants.Constants;
import com.ebtc.common.utils.MD5Utils;
import com.ebtc.user.dao.UserDao;
import com.ebtc.user.pojo.User;
import com.ebtc.user.service.UserService;

@Service
public class UserServiceImpl extends GenericServiceImpl<User,UserDao> implements UserService{

	@Autowired
	private UserDao userDao;
	@Autowired
	private ActivateService activateService;
	@Autowired
	private CnyAccountService cnyAccountService;
	@Autowired
	private BtcAccountService btcAccountService;
	
	@Resource(name = "mailService")
	private MailService mailService;
	
	@Override
	public UserDao getDao() {
		return userDao;
	}

	@Override
	public int createNewUser(User user) throws DataAccessException, BusinessException, UnsupportedEncodingException, MessagingException{
		int row = 0;
		//用户名转成小写
		user.setUsername(user.getUsername().toLowerCase());
		
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
					user.setPaymentCode(user.getPassword());//支付密码
					user.setCreateTime(new Date());	//注册时间
					user.setType(Constants.USER);//普通用户
					user.setState(Constants.INACTIVE);	//未激活
					//创建用户
					row = getDao().add(user);
					//创建人民币账户
					CnyAccount cnyAccount = new CnyAccount();
					cnyAccount.setUserId(user.getId());
					cnyAccount.setBalance(BigDecimal.ZERO);
					cnyAccount.setFreeze(BigDecimal.ZERO);
					cnyAccount.setState(Constants.NORMAL);
					cnyAccountService.add(cnyAccount);
					//创建比特币账户
					BtcAccount btcAccount = new BtcAccount();
					btcAccount.setUserId(user.getId());
					btcAccount.setBalance(BigDecimal.ZERO);
					btcAccount.setFreeze(BigDecimal.ZERO);
					btcAccount.setState(Constants.NORMAL);
					btcAccountService.add(btcAccount);
					if(row == 1){
						//往激活表里边加一条记录
						Activate activate = new Activate();
						activate.setValue(user.getId().toString());
						activate.setCode(MD5Utils.getCodeMD5((activate.getValue()+new Date().getTime()).getBytes()));
						activateService.add(activate);
						//发邮件给他的注册邮箱
						String subject = user.getNickname()+",感谢您注册易比特";
						String body = "感谢您注册易比特，我们将竭诚为您提供最优质的服务!https://exch-btc.com/validator/email/"+activate.getValue()+"/"+activate.getCode()+"";
						mailService.sendMail(subject, body, user.getEmail());
					}
				}
			}
		}
		return row;
	}

	@Override
	public User login(User user) throws BusinessException {
		//用户名转成小写
		user.setUsername(user.getUsername().toLowerCase());
		//校验密码
		User tem = new User();
		tem.setUsername(user.getUsername());
		tem = find(tem);
		if(tem != null){
			if(MD5Utils.getCodeMD5(user.getPassword().getBytes()).equals(tem.getPassword())){
				//正确,返回实体类
				tem.setPassword(null);
				tem.setPaymentCode(null);
				//更新最后登陆时间
				updateLastLoginTime(tem.getId());
				return tem;
			}
		}
		return null;
	}

	@Override
	public int updateLastLoginTime(int id) throws BusinessException {
		User user = new User();
		user.setId(id);
		user.setLastLoginTime(new Date());
		return update(user);
	}
}
