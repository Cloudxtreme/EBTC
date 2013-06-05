package com.ebtc.base.cache;

import java.util.Date;
import java.util.Map;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.ebtc.common.utils.PropertiesUtils;

/**
 * 
 * @ClassName: Memcached 
 * @Description: memcached缓存类
 * @author Wayga_Chan 553806198@qq.com 
 * @date 2013-5-29 下午11:22:55 
 *
 */
public class Memcached {

	// 创建全局的唯一实例
	protected static MemCachedClient mcc = new MemCachedClient();

	public static MemCachedClient getMcc() {
		return mcc;
	}

	protected static Memcached memCached = new Memcached();

	// 设置与缓存服务器的连接池
	static {
		PropertiesUtils.load("/sys_props.properties");

		// 服务器列表和其权重
		String[] servers = PropertiesUtils.get("memcached_servers").split(",");
		String[] weightStr = PropertiesUtils.get("memcached_weights")
				.split(",");

		Integer[] weights = new Integer[weightStr.length];
		for (int i = 0; i < weights.length; ++i) {
			weights[i] = Integer.parseInt(weightStr[i]);
		}

		// 获取socke连接池的实例对象
		SockIOPool pool = SockIOPool.getInstance();

		// 设置服务器信息
		pool.setServers(servers);
		pool.setWeights(weights);

		// 设置初始连接数、最小和最大连接数以及最大处理时间
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaxIdle(1000 * 60 * 60 * 6);

		// 设置主线程的睡眠时间
		pool.setMaintSleep(30);

		// 设置TCP的参数，连接超时等
		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setSocketConnectTO(0);

		// 初始化连接池
		pool.initialize();

		// 压缩设置，超过指定大小（单位为K）的数据都会被压缩
		mcc.setCompressEnable(true);
		mcc.setCompressThreshold(64 * 1024);
	}

	/**
	 * 保护型构造方法，不允许实例化！
	 * 
	 */
	protected Memcached() {

	}

	/**
	 * 获取唯一实例.
	 * 
	 * @return
	 */
	public static Memcached getInstance() {
		return memCached;
	}

	/**
	 * 添加一个指定的值到缓存中.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean add(String key, Object value) {
		return mcc.add(key, value);
	}

	public boolean add(String key, Object value, Date expiry) {
		return mcc.add(key, value, expiry);
	}

	public boolean add(String key, Object value, int hashCode) {
		return mcc.add(key, value, hashCode);
	}

	public boolean add(String key, Object value, Date expiry, int hashCode) {
		return mcc.add(key, value, expiry, hashCode);
	}

	public boolean flushAll() {
		return mcc.flushAll();
	}

	public boolean flushAll(String[] param) {
		return mcc.flushAll(param);
	}

	public boolean delete(String key) {
		return mcc.delete(key);
	}

	public boolean delete(String key, Date expiry) {
		return mcc.delete(key, expiry);
	}

	public boolean delete(String key, int hashCode, Date expiry) {
		return mcc.delete(key, hashCode, expiry);
	}

	public Object get(String key) {
		return mcc.get(key);
	}

	public Object get(String key, int hashCode) {
		return mcc.get(key, hashCode);
	}

	public Object get(String key, int hashCode, boolean flag) {
		return mcc.get(key, hashCode, flag);
	}

	public boolean keyExist(String key) {
		return mcc.keyExists(key);
	}

	public Map state() {
		return mcc.stats();
	}

	public Map state(String[] servers) {
		return mcc.stats(servers);
	}

	public boolean set(String key, Object value) {
		return mcc.set(key, value);
	}

	public boolean set(String key, Object value, Date expiry) {
		return mcc.set(key, value, expiry);
	}

	public boolean set(String key, Object value, int hashCode) {
		return mcc.set(key, value, hashCode);
	}

	public boolean set(String key, Object value, Date expiry, int hashCode) {
		return mcc.set(key, value, expiry, hashCode);
	}

	public boolean replace(String key, Object value) {
		return mcc.replace(key, value);
	}

	public boolean replace(String key, Object value, Date expiry) {
		return mcc.replace(key, value, expiry);
	}

	public boolean replace(String key, Object value, int hashCode) {
		return mcc.replace(key, value, hashCode);
	}

	public boolean replace(String key, Object value, Date expiry, int hashCode) {
		return mcc.replace(key, value, expiry, hashCode);
	}
	
	public static void main(String args[]){
        String key   = "counterKey";    
        mcc.storeCounter(key, new Integer(100));
        System.out.println("counter after adding      1: " +mcc.incr(key));       
        System.out.println("counter after adding      5: " +mcc.incr(key, 5));    
        System.out.println("counter after subtracting 4: " +mcc.decr(key, 4));    
        System.out.println("counter after subtracting 1: " +mcc.decr(key));       
	}
}
