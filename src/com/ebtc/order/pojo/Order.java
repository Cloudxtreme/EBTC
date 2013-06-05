package com.ebtc.order.pojo;

import com.ebtc.base.pojo.Pojo;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @ClassName: Order
 * @author Wayga_Chan 553806198@qq.com
 * @date 2013-06-02 11:27:37
 *
 */

public class Order extends Pojo {
	private Integer id;
	private Integer userId;
	/**
	*	单价 1个虚拟币的价格
	*/
	private BigDecimal price;
	/**
	*	数量
	*/
	private BigDecimal quantity;
	private BigDecimal remaining;
	/**
	*	买卖类型 见 constants
	*/
	private String type;
	/**
	*	状态 见 constants
	*/
	private String state;
	/**
	*	货币类型 见 constants
	*/
	private String currencyType;
	private BigDecimal fee;
	private BigDecimal feeRate;
	/**
	*	创建时间
	*/
	private Date createTime;
	/**
	*	创建人
	*/
	private String createUser;
	/**
	*	完成交易时间
	*/
	private Date doneTime;
	/**
	*	备注 100
	*/
	private String remark;

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
	 * @Title getUserId
	 * @Description 返回userId的值
	 * @return Integer
	 */
	public Integer getUserId(){

		return this.userId;

	}
	/**
	 *
	 * @Title setUserId
	 * @Description 设置userId的值
	 * @param userId
	 * @return void
	 */
	public void setUserId(Integer userId){

		this.userId = userId;

	}
	/**
	 *
	 * @Title getPrice
	 * @Description 返回price的值
	 * @return BigDecimal
	 */
	public BigDecimal getPrice(){

		return this.price;

	}
	/**
	 *
	 * @Title setPrice
	 * @Description 设置price的值
	 * @param price
	 * @return void
	 */
	public void setPrice(BigDecimal price){

		this.price = price;

	}
	/**
	 *
	 * @Title getQuantity
	 * @Description 返回quantity的值
	 * @return BigDecimal
	 */
	public BigDecimal getQuantity(){

		return this.quantity;

	}
	/**
	 *
	 * @Title setQuantity
	 * @Description 设置quantity的值
	 * @param quantity
	 * @return void
	 */
	public void setQuantity(BigDecimal quantity){

		this.quantity = quantity;

	}
	/**
	 *
	 * @Title getRemaining
	 * @Description 返回remaining的值
	 * @return BigDecimal
	 */
	public BigDecimal getRemaining(){

		return this.remaining;

	}
	/**
	 *
	 * @Title setRemaining
	 * @Description 设置remaining的值
	 * @param remaining
	 * @return void
	 */
	public void setRemaining(BigDecimal remaining){

		this.remaining = remaining;

	}
	/**
	 *
	 * @Title getType
	 * @Description 返回type的值
	 * @return String
	 */
	public String getType(){

		return this.type;

	}
	/**
	 *
	 * @Title setType
	 * @Description 设置type的值
	 * @param type
	 * @return void
	 */
	public void setType(String type){

		this.type = type;

	}
	/**
	 *
	 * @Title getState
	 * @Description 返回state的值
	 * @return String
	 */
	public String getState(){

		return this.state;

	}
	/**
	 *
	 * @Title setState
	 * @Description 设置state的值
	 * @param state
	 * @return void
	 */
	public void setState(String state){

		this.state = state;

	}
	/**
	 *
	 * @Title getCurrencyType
	 * @Description 返回currencyType的值
	 * @return String
	 */
	public String getCurrencyType(){

		return this.currencyType;

	}
	/**
	 *
	 * @Title setCurrencyType
	 * @Description 设置currencyType的值
	 * @param currencyType
	 * @return void
	 */
	public void setCurrencyType(String currencyType){

		this.currencyType = currencyType;

	}
	/**
	 *
	 * @Title getFee
	 * @Description 返回fee的值
	 * @return BigDecimal
	 */
	public BigDecimal getFee(){

		return this.fee;

	}
	/**
	 *
	 * @Title setFee
	 * @Description 设置fee的值
	 * @param fee
	 * @return void
	 */
	public void setFee(BigDecimal fee){

		this.fee = fee;

	}
	/**
	 *
	 * @Title getFeeRate
	 * @Description 返回feeRate的值
	 * @return BigDecimal
	 */
	public BigDecimal getFeeRate(){

		return this.feeRate;

	}
	/**
	 *
	 * @Title setFeeRate
	 * @Description 设置feeRate的值
	 * @param feeRate
	 * @return void
	 */
	public void setFeeRate(BigDecimal feeRate){

		this.feeRate = feeRate;

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
	/**
	 *
	 * @Title getDoneTime
	 * @Description 返回doneTime的值
	 * @return Date
	 */
	public Date getDoneTime(){

		return this.doneTime;

	}
	/**
	 *
	 * @Title setDoneTime
	 * @Description 设置doneTime的值
	 * @param doneTime
	 * @return void
	 */
	public void setDoneTime(Date doneTime){

		this.doneTime = doneTime;

	}
	/**
	 *
	 * @Title getRemark
	 * @Description 返回remark的值
	 * @return String
	 */
	public String getRemark(){

		return this.remark;

	}
	/**
	 *
	 * @Title setRemark
	 * @Description 设置remark的值
	 * @param remark
	 * @return void
	 */
	public void setRemark(String remark){

		this.remark = remark;

	}
}