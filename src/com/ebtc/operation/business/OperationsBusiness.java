package com.ebtc.operation.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.java_websocket.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ebtc.base.cache.LocalCached;
import com.ebtc.base.cache.Memcached;
import com.ebtc.base.exception.BusinessException;
import com.ebtc.common.constants.Constants;
import com.ebtc.operation.bean.Action;
import com.ebtc.operation.bean.Message;
import com.ebtc.operation.bean.TradeOrderInfo;
import com.ebtc.user.pojo.User;
import com.ebtc.user.service.UserService;

@Repository
/**
 * 操作业务类
 * @author Administrator
 *
 */
public class OperationsBusiness {

	private Memcached memcached = Memcached.getInstance();
	
	private LocalCached localCached = LocalCached.getInstance();
	
	private Logger log = Logger.getLogger(OperationsBusiness.class);
	
	//买1-买5的连接
	private List<WebSocket> currentRateConnections;
	//交易记录的连接
	private List<WebSocket> transactionRecordConnections;
	
	@Autowired
	//用户service
	private UserService userService;
	
	public OperationsBusiness(){
		super();
		currentRateConnections = new ArrayList<WebSocket>();
		transactionRecordConnections = new ArrayList<WebSocket>();
	}

	
	/**
	 * 
	 * @Title	removeConnection 
	 * @Description	从已连接集合中删除该连接
	 * @param conn
	 */
	public void removeConn(WebSocket conn) {
		this.currentRateConnections.remove(conn);
		this.transactionRecordConnections.remove(conn);
		String uuid = (String) localCached.get(conn);
		localCached.delete(conn);
		localCached.delete(uuid+"locale");
		localCached.delete(uuid+"loginUser");
	}
	
	/**
	 * 
	 * @Title	doAction 
	 * @Description	处理过来的消息
	 * @param conn
	 * @param message 
	 */
	public void doAction(WebSocket conn, JSONObject message) {
		JSONObject action = message.getJSONObject("action");
		if(action != null){
			String command = action.getString("command");
			if(command != null){
				if(command.equals("setLocale")){
					//设置客户语言
					setLocale(conn,message);
				}else if(command.equals("service")){
					//命令是服务service
					doService(conn,action);
				}else if(command.equals("login")){
					JSONObject data = message.getJSONObject("data");
					doLogin(conn,data);
				}
			}
		}
	}
	
	private void setLocale(WebSocket conn, JSONObject message) {
		JSONObject action = message.getJSONObject("action");
		if(action != null){
			JSONArray params = action.getJSONArray("params");
			if(params.size()>0){
				String locale = params.getString(0);
				JSONObject data = message.getJSONObject("data");
				if(data != null){
					String uuid = data.getString("uuid");
					if(localCached.add(conn, uuid)){
						localCached.add(uuid+"locale", locale);
					}
				}
			}
		}
	}


	/**
	 * 
	 * @Title	doService 
	 * @Description	处理服务请求
	 * @param conn
	 * @param action
	 */
	public void doService(WebSocket conn, JSONObject action) {
		JSONArray param = action.getJSONArray("params");
		if(param != null && !param.isEmpty()){
			Iterator it = param.iterator();
			while(it.hasNext()){
				String p = it.next().toString();
				//添加进相应集合中。并返回一次消息
				if(p.equals("currentRate")){
					this.currentRateConnections.add(conn);
					refreshCurrentRateMessage(conn);
				}
				if(p.equals("transactionRecord")){
					this.transactionRecordConnections.add(conn);
				}
				
			}
		}
	}
	
	/**
	 * 
	 * @Title	refreshCurrentRateMessage 
	 * @Description	发送当前汇率信息
	 * @param conn void
	 */
	private void refreshCurrentRateMessage(WebSocket conn) {
		TradeOrderInfo[] highPriceBuy = (TradeOrderInfo[]) memcached.get(Constants.HIGH_PRICE_BUY);
		TradeOrderInfo[] lowPriceSell = (TradeOrderInfo[]) memcached.get(Constants.LOW_PRICE_SELL);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(Constants.HIGH_PRICE_BUY, highPriceBuy);
		map.put(Constants.LOW_PRICE_SELL, lowPriceSell);
		Message message = new Message();
		Action action = new Action();
		message.setAction(action);
		message.setData(map);
		action.setCommand("refresh");
		String[] params = {"currentRate"};
		action.setParams(params);
		
		synchronized ( conn ) {
			try {
				conn.send(JSONObject.fromObject(message).toString());
			} catch (InterruptedException e) {
				log.debug(e);
			}
		}
	}

	/**
	 * 处理登录请求
	 * @param conn
	 * @param data
	 */
	public void doLogin(WebSocket conn, JSONObject data) {
		User user = new User();
		String username = data.getString("username");
		String password = data.getString("password");
		String uuid = data.getString("uuid");
		user.setUsername(username);
		user.setPassword(password);
		try {
			user = userService.login(user);
			Message message = new Message();
			Action action = new Action();
			HashMap map = new HashMap(1);
			if(user != null){
				//登录成功
				//保存用户到memcached和localCached
				String key = uuid+"loginUser";
				memcached.set(key, user);
				localCached.set(key, user);
				
				//返回登录成功
				action.setCommand("loginSuccess");
				message.setAction(action);
				message.setData(map);
			}else{
				//用户名或密码错误
				action.setCommand("loginFail");
				String errorMessage = null;
				String locale = (String) localCached.get(uuid+"locale");
				if(locale.equals("zh_cn")){
					errorMessage = "登录失败,用户名或密码错误!";
				}else if(locale.equals("en_us")){
					errorMessage = "login fail,username or password is wrong!";
				}else if(locale.equals("zh_tw")){
					errorMessage = "登錄失敗,用戶名或密碼錯誤!";
				}
				
				map.put("errorMessage", errorMessage);
				message.setData(map);
			}
			synchronized ( conn ) {
				try {
					conn.send(JSONObject.fromObject(message).toString());
				} catch (InterruptedException e) {
					log.debug(e);
				}
			};
			
		} catch (BusinessException e) {
			log.debug(e);
		}
	}
	
	
}
