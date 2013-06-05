package com.ebtc.common.constants;

/**
 * 
 * @ClassName: CosFunctions 
 * @Description: 常量类jsp函数
 * @author Wayga_Chan 553806198@qq.com 
 * @date 2013-5-29 下午9:24:41 
 *
 */
public class CosFunctions {
	
	/**
	 * 
	 * @Title	display 
	 * @Description	TODO(这里用一句话描述这个方法的作用) 
	 * @param type
	 * @return String
	 */
	public static String display(String type,String attr){
		return Constants.get(type, attr);
	}
}
