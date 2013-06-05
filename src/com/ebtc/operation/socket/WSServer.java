package com.ebtc.operation.socket;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketServer;
import org.java_websocket.handshake.ClientHandshake;
import org.springframework.beans.factory.annotation.Autowired;

import com.ebtc.base.cache.Memcached;
import com.ebtc.common.constants.Constants;
import com.ebtc.operation.bean.Action;
import com.ebtc.operation.bean.Message;
import com.ebtc.operation.bean.TradeOrderInfo;
import com.ebtc.user.pojo.User;
import com.ebtc.user.service.UserService;

public class WSServer extends WebSocketServer {

	
	private Logger log = Logger.getLogger(WSServer.class);
	
	//Memcached缓存
	private Memcached memcached = Memcached.getInstance();
	
	//买1-买5的连接
	private List<WebSocket> currentRateConnections;
	//交易记录的连接
	private List<WebSocket> transactionRecordConnections;
	
	@Autowired
	private UserService userService;
	
	public WSServer(int port) {
		this(new InetSocketAddress("127.0.0.1",port));
	}
	
	public WSServer(InetSocketAddress address) {
		super(address);
		currentRateConnections = new ArrayList<WebSocket>();
		transactionRecordConnections = new ArrayList<WebSocket>();
	}

	/**
	 * 
	 * @Title	sendToAll 
	 * @Description	向所有连接的客户端发送消息
	 * @param text
	 * @throws InterruptedException void
	 */
	public void sendToAll( String text ) throws InterruptedException {
		Set<WebSocket> conn = connections();
		synchronized ( conn ) {
			for( WebSocket connection : conn ) {
				connection.send( text );
			}
		}
	}
	
	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		System.out.println(conn.getLocalSocketAddress()+"  "+conn.getRemoteSocketAddress()+"  has close the connection!");
		removeConnection(conn);
	}

	/**
	 * 
	 * @Title	removeConnection 
	 * @Description	从已连接集合中删除该连接
	 * @param conn void
	 */
	private void removeConnection(WebSocket conn) {
		this.currentRateConnections.remove(conn);
		this.transactionRecordConnections.remove(conn);
	}

	@Override
	public void onError(WebSocket conn, Exception e) {
		log.debug(e);
	}

	@Override
	public void onMessage(WebSocket conn, String message) {
		JSONObject json = JSONObject.fromObject(message);
		
		doAction(conn,json);
		
		System.out.println(json);

	}

	/**
	 * 
	 * @Title	doAction 
	 * @Description	处理过来的消息
	 * @param conn
	 * @param json void
	 */
	private void doAction(WebSocket conn, JSONObject json) {
		JSONObject action = json.getJSONObject("action");
		if(action != null){
			String command = action.getString("command");
			if(command != null){
				//命令是服务service
				if(command.equals("service")){
					doService(conn,action);
				}else if(command.equals("login")){
					User user = new User();
					
//					userService.login()；
				}
			}
		}
	}

	/**
	 * 
	 * @Title	doService 
	 * @Description	处理服务请求
	 * @param conn
	 * @param action void
	 */
	private void doService(WebSocket conn, JSONObject action) {
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

	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		System.out.println(conn.getLocalSocketAddress()+"  "+conn.getRemoteSocketAddress()+"  has enter the connection!");
	}

}
