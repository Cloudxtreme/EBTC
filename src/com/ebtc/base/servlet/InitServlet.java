package com.ebtc.base.servlet;

import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ebtc.base.cache.Memcached;
import com.ebtc.base.dao.JDBCDaoSupport;
import com.ebtc.base.exception.BusinessException;
import com.ebtc.base.pagination.PaginationInfo;
import com.ebtc.base.pagination.PaginationInfo.SortOrder;
import com.ebtc.common.constants.Constants;
import com.ebtc.common.utils.PropertiesUtils;
import com.ebtc.menu.pojo.Menu;
import com.ebtc.menu.service.MenuService;
import com.ebtc.operation.bean.TradeOrderInfo;
import com.ebtc.operation.socket.WSServer;
import com.ebtc.order.pojo.Order;
import com.ebtc.order.service.OrderService;

/**
 * Servlet implementation class InitServlet
 */
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Logger log = Logger.getLogger(InitServlet.class);

	private MenuService menuService;
	
	private OrderService orderService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitServlet() {
        super();
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init();
    	//加载系统配置文件
    	PropertiesUtils.load("/sys_props.properties");
    	
    	ServletContext application = config.getServletContext();
		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(application);
		//memcached 缓存
		Memcached memcached = Memcached.getInstance();
		//清空缓存
		memcached.flushAll();
		
		JDBCDaoSupport JDBCDao = (JDBCDaoSupport) applicationContext.getBean("JDBCDaoSupport");
	    //加载菜单
		menuService = (MenuService) applicationContext.getBean("menuService");
    	
    	Menu menu = new Menu();
        menu.setParentId(0);
        menu.setType("0");
        try {
			List<Menu> menus = menuService.query(menu);
			application.setAttribute(Constants.USER_MENU, menus);
		} catch (BusinessException e) {
			log.debug("初始化用户菜单数据失败");
		}
        
        menu.setType("1");
        try {
			List<Menu> menus = menuService.query(menu);
			application.setAttribute(Constants.ADMIN_MENU, menus);
		} catch (BusinessException e) {
			log.debug("初始化管理员菜单数据失败");
		}
        //常量类加载数据库常量表
        Constants.load(JDBCDao);
        
        orderService = (OrderService) applicationContext.getBean("orderService");
        
        //加载实时价格委托单。买1-买5，卖1-卖5
        TradeOrderInfo[] highPriceBuy = new TradeOrderInfo[5];
        TradeOrderInfo[] lowPriceSell = new TradeOrderInfo[5];
        PaginationInfo pageInfo = new PaginationInfo();
        //设置根据价格排序,取第一页前5条记录
        pageInfo.setSortCol("price");
        pageInfo.setPageSize(5);
        pageInfo.setCurrentPage(1);
        
        Order order = new Order();
        //未完成状态
        order.setState(Constants.UNDONE);
        try {
	        //设置成购买类型
	        order.setType(Constants.BUY);
	        //设置成降序
	        pageInfo.setSortOrder(SortOrder.desc);
			List<Order> list = orderService.query(order, pageInfo).getData();
			for(int i=0; i<list.size(); ++i){
				Order o = list.get(i);
				TradeOrderInfo t = new TradeOrderInfo();
				t.setPrice(o.getPrice());
				t.setQuantity(o.getRemaining());
				t.setType(o.getType());
				highPriceBuy[i] = t;
			}
			//设置成出售类型
			order.setType(Constants.SELL);
			//设置成升序
			pageInfo.setSortOrder(SortOrder.asc);
			list = orderService.query(order, pageInfo).getData();
			for(int i=0; i<list.size(); ++i){
				Order o = list.get(i);
				TradeOrderInfo t = new TradeOrderInfo();
				t.setPrice(o.getPrice());
				t.setQuantity(o.getRemaining());
				t.setType(o.getType());
				lowPriceSell[i] = t;
			}
		} catch (BusinessException e) {
			log.debug("加载实时价格委托单失败");
			log.debug(e);
		}
        
        //存放进缓存中
        memcached.set(Constants.HIGH_PRICE_BUY, highPriceBuy);
        memcached.set(Constants.LOW_PRICE_SELL, lowPriceSell);
        
        //加载交易历史（最新100条记录）
        pageInfo = new PaginationInfo();
        pageInfo.setCurrentPage(1);
        pageInfo.setPageSize(100);
        pageInfo.setSortCol("done_time");
        pageInfo.setSortOrder(SortOrder.desc);
        //查询条件为已经完成的交易
        order = new Order();
        order.setState(Constants.DONE);
        List<Order> transactionRecords = null;
        try {
			transactionRecords = orderService.query(order, pageInfo).getData();
		} catch (BusinessException e) {
			log.debug("加载交易历史记录失败");
			log.debug(e);
		}
        //放进缓存中
        application.setAttribute("transactionRecords", transactionRecords);
        
		
        //加载手续费率
        Double buyHandlingFeeRate = null;
        Double sellHandlingFeeRate = null;
        buyHandlingFeeRate = Double.parseDouble(PropertiesUtils.get("buy_handlingFeeRate"));
		sellHandlingFeeRate = Double.parseDouble(PropertiesUtils.get("sell_handlingFeeRate"));
        application.setAttribute("buyHandlingFeeRate", buyHandlingFeeRate);
        application.setAttribute("sellHandlingFeeRate", sellHandlingFeeRate);
       
        //初始化WebSocketServer
        WSServer webSocketServer = (WSServer) applicationContext.getBean("WSServer");
        webSocketServer.start();
        application.setAttribute("WSS", webSocketServer);
        
        //卸载系统配置文件
        PropertiesUtils.unLoad();
    }

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

}
