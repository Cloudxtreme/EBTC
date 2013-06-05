package com.ebtc.activate.pojo;

import com.ebtc.base.pojo.Pojo;

/**
 *
 * @ClassName: Activate
 * @author Wayga_Chan 553806198@qq.com
 * @date 2013-05-23 10:53:01
 *
 */

public class Activate extends com.ebtc.base.pojo.Pojo {
	private Integer id;
	/**
	*	值
	*/
	private String value;
	/**
	*	码
	*/
	private String code;

	/**
	 *
	 * @Title getId
	 * @Description 返回id的值
	 * @return Integer
	 */
	public Integer getId(){

		return this.id;

	}
	/**
	 *
	 * @Title setId
	 * @Description 设置id的值
	 * @param id
	 * @return void
	 */
	public void setId(Integer id){

		this.id = id;

	}
	/**
	 *
	 * @Title getValue
	 * @Description 返回value的值
	 * @return String
	 */
	public String getValue(){

		return this.value;

	}
	/**
	 *
	 * @Title setValue
	 * @Description 设置value的值
	 * @param value
	 * @return void
	 */
	public void setValue(String value){

		this.value = value;

	}
	/**
	 *
	 * @Title getCode
	 * @Description 返回code的值
	 * @return String
	 */
	public String getCode(){

		return this.code;

	}
	/**
	 *
	 * @Title setCode
	 * @Description 设置code的值
	 * @param code
	 * @return void
	 */
	public void setCode(String code){

		this.code = code;

	}
}