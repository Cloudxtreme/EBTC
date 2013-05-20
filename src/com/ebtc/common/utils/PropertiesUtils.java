package com.ebtc.common.utils;

import java.io.IOException;  
import java.io.InputStream;  
import java.util.Properties;  
import java.util.Set;
	  
public class PropertiesUtils {

	private static Properties pros = null;// Properties Object

	/**
	 * Loading files to Properties object
	 * 
	 * @param fileName
	 */
	public static void load(String fileName) {
		pros = new Properties();// Create a Properties object
		/**
		 * Get properties file attributes for the current value of the
		 * inter-class compiled bytecode in file list of files of the documents
		 * called fileName input stream
		 */
		InputStream in = PropertiesUtils.class.getResourceAsStream(fileName);
		try {
			pros.load(in);// Load the file Properties to flow to the object
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title	unLoad 
	 * @Description	clear pros
	 */
	public static void unLoad(){
		pros = null;
	}
	
	/**
	 * Through the attribute name to get attribute values
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		return pros.getProperty(key);// Through the key specific get attribute
										// value
	}
	
	/**
	 * 
	 * @Title	keySet 
	 * @Description	返回keySet
	 * @return Set<Object>
	 */
	public static Set<Object> keySet(){
		return pros.keySet();
	}
}
