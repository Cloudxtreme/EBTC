package com.ebtc.base.cache;

import java.util.HashMap;
import java.util.Map;

public class LocalCached {

	private Map dataMap;
	
	private static LocalCached instance;
	
	public static LocalCached getInstance(){
		if(instance == null){
			instance = new LocalCached();
		}
		return instance;
	}
	
	private LocalCached(){
		dataMap = new HashMap();
	}
	
	public boolean set(Object key,Object value){
		dataMap.put(key, value);
		return true;
	}
	
	public boolean add(Object key,Object value){
		if(dataMap.containsKey(key)){
			return false;
		}else{
			dataMap.put(key, value);
			return true;
		}
	}
	
	public boolean delete(Object key){
		dataMap.remove(key);
		return true;
	}
	
	public boolean replace(Object key,Object value){
		if(dataMap.containsKey(key)){
			dataMap.put(key, value);
			return true;
		}else{
			return false;
		}
	}
	
	public Object get(Object key){
		return dataMap.get(key);
	}
	
	public boolean keyExist(Object key){
		return dataMap.containsKey(key);
	}
	
	public void flushAll(){
		dataMap = new HashMap();
	}
}
