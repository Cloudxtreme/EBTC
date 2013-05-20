package com.ebtc.base.servlet;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ebtc.base.exception.BusinessException;
import com.ebtc.common.constants.Constants;
import com.ebtc.menu.pojo.Menu;
import com.ebtc.menu.service.MenuService;

/**
 * Servlet implementation class InitServlet
 */
@WebServlet("/InitServlet")
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Logger log = Logger.getLogger(InitServlet.class);
	@Autowired
	private MenuService menuService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitServlet() {
        super();
        Menu menu = new Menu();
        menu.setParentId(0);
        menu.setType("0");
        try {
			List<Menu> menus = menuService.query(menu);
			getServletContext().setAttribute(Constants.USER_MENU, menus);
		} catch (BusinessException e) {
			log.debug("初始化用户菜单数据失败");
		}
        
        menu.setType("1");
        try {
			List<Menu> menus = menuService.query(menu);
			getServletContext().setAttribute(Constants.ADMIN_MENU, menus);
		} catch (BusinessException e) {
			log.debug("初始化管理员菜单数据失败");
		}
    }

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

    

}
