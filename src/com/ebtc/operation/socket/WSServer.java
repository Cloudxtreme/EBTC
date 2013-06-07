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
import com.ebtc.base.exception.BusinessException;
import com.ebtc.common.constants.Constants;
import com.ebtc.operation.bean.Action;
import com.ebtc.operation.bean.Message;
import com.ebtc.operation.bean.TradeOrderInfo;
import com.ebtc.operation.business.OperationsBusiness;
import com.ebtc.user.pojo.User;
import com.ebtc.user.service.UserService;

public class WSServer extends WebSocketServer {

	
	private Logger log = Logger.getLogger(WSServer.class);
	
	//Memcached缓存
	private Memcached memcached = Memcached.getInstance();
	
	//操作业务逻辑类
	@Autowired
	private OperationsBusiness operationsBusiness;
	
	public WSServer(int port) {
		this(new InetSocketAddress("127.0.0.1",port));
	}
	
	public WSServer(InetSocketAddress address) {
		super(address);
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
		operationsBusiness.removeConn(conn);
	}

	@Override
	public void onError(WebSocket conn, Exception e) {
		log.debug(e);
	}

	@Override
	public void onMessage(WebSocket conn, String message) {
		JSONObject json = JSONObject.fromObject(message);
		
		operationsBusiness.doAction(conn,json);
		
		System.out.println(json);

	}

	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		System.out.println(conn.getLocalSocketAddress()+"  "+conn.getRemoteSocketAddress()+"  has enter the connection!");
	}

}
