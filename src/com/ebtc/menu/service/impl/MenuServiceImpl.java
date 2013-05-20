package com.ebtc.menu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebtc.base.service.impl.GenericServiceImpl;
import com.ebtc.menu.dao.MenuDao;
import com.ebtc.menu.pojo.Menu;
import com.ebtc.menu.service.MenuService;

/**
 * 
 * @ClassName: MenuServiceImpl 
 * @Description: 菜单Service
 * @author Wayga_Chan 553806198@qq.com 
 * @date 2013-5-16 下午9:14:19 
 *
 */
@Service
public class MenuServiceImpl extends GenericServiceImpl<Menu, MenuDao>
		implements MenuService {

	@Autowired
	private MenuDao dao;

	@Override
	public MenuDao getDao() {
		return dao;
	}
}
