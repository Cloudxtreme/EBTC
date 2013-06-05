package com.ebtc.order.pojo;

import com.ebtc.base.pojo.Pojo;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @ClassName: Trade
 * @author Wayga_Chan 553806198@qq.com
 * @date 2013-06-02 11:35:21
 *
 */

public class Trade extends Pojo {
	private Integer id;
	/**
	*	主订单id
	*/
	private Integer ordersId;
	/**
	*	交易订单id
	*/
	private Integer tradeOrderId;
	/**
	*	数量
	*/
	private String quantity;
	/**
	*	金额
	*/
	private BigDecimal totalamount;
	/**
	*	创建时间
	*/
	private Date createTime;
	/**
	*	创建用户
	*/
	private String createUser;

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
	 * @Title getOrdersId
	 * @Description 返回ordersId的值
	 * @return Integer
	 */
	public Integer getOrdersId(){

		return this.ordersId;

	}
	/**
	 *
	 * @Title setOrdersId
	 * @Description 设置ordersId的值
	 * @param ordersId
	 * @return void
	 */
	public void setOrdersId(Integer ordersId){

		this.ordersId = ordersId;

	}
	/**
	 *
	 * @Title getTradeOrderId
	 * @Description 返回tradeOrderId的值
	 * @return Integer
	 */
	public Integer getTradeOrderId(){

		return this.tradeOrderId;

	}
	/**
	 *
	 * @Title setTradeOrderId
	 * @Description 设置tradeOrderId的值
	 * @param tradeOrderId
	 * @return void
	 */
	public void setTradeOrderId(Integer tradeOrderId){

		this.tradeOrderId = tradeOrderId;

	}
	/**
	 *
	 * @Title getQuantity
	 * @Description 返回quantity的值
	 * @return String
	 */
	public String getQuantity(){

		return this.quantity;

	}
	/**
	 *
	 * @Title setQuantity
	 * @Description 设置quantity的值
	 * @param quantity
	 * @return void
	 */
	public void setQuantity(String quantity){

		this.quantity = quantity;

	}
	/**
	 *
	 * @Title getTotalamount
	 * @Description 返回totalamount的值
	 * @return BigDecimal
	 */
	public BigDecimal getTotalamount(){

		return this.totalamount;

	}
	/**
	 *
	 * @Title setTotalamount
	 * @Description 设置totalamount的值
	 * @param totalamount
	 * @return void
	 */
	public void setTotalamount(BigDecimal totalamount){

		this.totalamount = totalamount;

	}
	/**
	 *
	 * @Title getCreateTime
	 * @Description 返回createTime的值
	 * @return Date
	 */
	public Date getCreateTime(){

		return this.createTime;

	}
	/**
	 *
	 * @Title setCreateTime
	 * @Description 设置createTime的值
	 * @param createTime
	 * @return void
	 */
	public void setCreateTime(Date createTime){

		this.createTime = createTime;

	}
	/**
	 *
	 * @Title getCreateUser
	 * @Description 返回createUser的值
	 * @return String
	 */
	public String getCreateUser(){

		return this.createUser;

	}
	/**
	 *
	 * @Title setCreateUser
	 * @Description 设置createUser的值
	 * @param createUser
	 * @return void
	 */
	public void setCreateUser(String createUser){

		this.createUser = createUser;

	}
}