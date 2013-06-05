package com.ebtc.account.pojo;

import com.ebtc.base.pojo.Pojo;
import java.math.BigDecimal;

/**
 *
 * @ClassName: CnyAccount
 * @author Wayga_Chan 553806198@qq.com
 * @date 2013-05-23 11:10:05
 *
 */

public class CnyAccount extends Pojo {
	/**
	*	主键
	*/
	private Integer id;
	/**
	*	外键 用户id
	*/
	private Integer userId;
	/**
	*	人名币余额(总额)=可用额度+冻结额度
	*/
	private BigDecimal balance;
	/**
	*	冻结人民币额度
	*/
	private BigDecimal freeze;
	/**
	*	状态 见 constants
	*/
	private String state;
	/**
	*	备注 50
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
	 * @Title getBalance
	 * @Description 返回balance的值
	 * @return BigDecimal
	 */
	public BigDecimal getBalance(){

		return this.balance;

	}
	/**
	 *
	 * @Title setBalance
	 * @Description 设置balance的值
	 * @param balance
	 * @return void
	 */
	public void setBalance(BigDecimal balance){

		this.balance = balance;

	}
	/**
	 *
	 * @Title getFreeze
	 * @Description 返回freeze的值
	 * @return BigDecimal
	 */
	public BigDecimal getFreeze(){

		return this.freeze;

	}
	/**
	 *
	 * @Title setFreeze
	 * @Description 设置freeze的值
	 * @param freeze
	 * @return void
	 */
	public void setFreeze(BigDecimal freeze){

		this.freeze = freeze;

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