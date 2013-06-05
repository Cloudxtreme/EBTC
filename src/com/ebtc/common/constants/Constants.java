package com.ebtc.common.constants;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ebtc.base.dao.JDBCDaoSupport;
import com.ebtc.common.utils.StringUtils;

/**
 * 
 * @ClassName: Constants 
 * @Description: 各种常量在这定义,减少硬编码
 * @author Wayga_Chan 553806198@qq.com 
 * @date 2013-5-18 下午9:43:07 
 *
 */
public class Constants extends TagSupport{
	
	//session中当前登陆用户的key
	public static final String LOGIN_USER = "login_user";
	//session中的验证码的key
	public static final String CAPTCHA = "captcha";
	//存放在session中的btc账户信息的key
	public static final String BTC_ACCOUNT = "btc_account";
	//存放在session中的cny账户信息的key
	public static final String CNY_ACCOUNT = "cny_account";
	
	//application中用户的菜单列表的key
	public static final String USER_MENU = "user_menu";
	//application中管理员菜单列表的key
	public static final String ADMIN_MENU = "admin_menu";
	
	//memcached 中 买1-买5
	public static final String HIGH_PRICE_BUY = "highPriceBuy";
	//memcached 中 卖1-卖5
	public static final String LOW_PRICE_SELL = "lowPriceSell";
	
	//output.jsp中输出的变量的key
	public static final String OUTPUT = "output"; 
	
	//页面上展示信息Tip
	public static final String INFO	= "info";
	//页面上展示错误Tip
	public static final String ERROR = "error";
	//页面上展示警告Tip
	public static final String WARN = "warn";
	
	//数据库常量值定义
	
	//账号类型 USER_TYPE 用户
	public static final String USER = "0";
	//账号类型 USER_TYPE 管理员
	public static final String MANAGER = "1";
	//状态 STATE 正常
	public static final String NORMAL = "0";
	//状态 STATE 未激活
	public static final String INACTIVE = "1";
	//状态 STATE 冻结
	public static final String FREEZE = "2";
	//状态 STATE 未完成
	public static final String UNDONE = "3";
	//状态 STATE 完成
	public static final String DONE = "4";
	
	//orders 交易类型 TRADE_TYPE 买
	public static final String BUY = "0";
	//orders 交易类型 TRADE_TYPE 卖
	public static final String SELL = "1";
	
	private static Logger log = Logger.getLogger(Constants.class);
	
	private static HashMap<String,String> DB_Constant;
	
	public static void load(JDBCDaoSupport JDBCDao){
		DB_Constant = new HashMap<String,String>();
//		JDBCDaoSupport JDBCDao = new JDBCDaoSupport();
		String sql = "select type,attr,value from constants";
		List<HashMap<String,Object>> datas = null;
		try {
			datas = JDBCDao.query(sql, null);
			for(HashMap<String,Object> data : datas){
				String type = data.get("type").toString();
				String attr = data.get("attr").toString();
				String value = data.get("value").toString();
				
				if(StringUtils.isNotBlank(attr)){
					DB_Constant.put(type+"_"+attr, value);
				}else{
					DB_Constant.put(type, value);
				}
			}
			
		} catch (SQLException e) {
			log.debug("Constants 初始化失败");
			log.debug(e);
		}
	}
	
	/**
	 * 
	 * @Title	get 
	 * @Description	获取数据库常量值
	 * @param type
	 * @param attr
	 * @return String
	 */
	public static String get(String type,String attr){
		if(StringUtils.isNotBlank(type)){
			if(StringUtils.isNotBlank(attr)){
				return DB_Constant.get(type+"_"+attr);
			}else{
				return DB_Constant.get(type);
			}
		}
		return null;
	}
	
}
