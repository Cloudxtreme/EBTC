package com.ebtc.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.ebtc.base.dao.impl.GenericDaoImpl;
import com.ebtc.user.dao.UserDao;
import com.ebtc.user.pojo.User;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

}
