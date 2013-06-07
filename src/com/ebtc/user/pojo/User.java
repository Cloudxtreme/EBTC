package com.ebtc.user.pojo;

import java.io.Serializable;
import java.util.Date;

import com.ebtc.base.pojo.Pojo;

/**
 *
 * @ClassName: User
 * @author Wayga_Chan 553806198@qq.com
 * @date 2013-05-15 09:51:27
 *
 */

public class User extends Pojo implements Serializable{
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = -6659911084003855575L;
	/**
	*	主键
	*/
	private Integer id;
	/**
	*	用户名
	*/
	private String username;
	/**
	*	密码
	*/
	private String password;
	/**
	*	支付密码
	*/
	private String paymentCode;
	/**
	*	昵称
	*/
	private String nickname;
	/**
	*	手机号码
	*/
	private String mobile;
	/**
	*	邮箱
	*/
	private String email;
	/**
	*	状态 见Constants
	*/
	private String state;
	/**
	*	类型 见 Constants
	*/
	private String type;
	/**
	*	创建时间
	*/
	private Date createTime;
	/**
	*	最后登陆时间
	*/
	private Date lastLoginTime;

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
	 * @Title getUsername
	 * @Description 返回username的值
	 * @return String
	 */
	public String getUsername(){

		return this.username;

	}
	/**
	 *
	 * @Title setUsername
	 * @Description 设置username的值
	 * @param username
	 * @return void
	 */
	public void setUsername(String username){

		this.username = username;

	}
	/**
	 *
	 * @Title getPassword
	 * @Description 返回password的值
	 * @return String
	 */
	public String getPassword(){

		return this.password;

	}
	/**
	 *
	 * @Title setPassword
	 * @Description 设置password的值
	 * @param password
	 * @return void
	 */
	public void setPassword(String password){

		this.password = password;

	}
	/**
	 *
	 * @Title getPaymentCode
	 * @Description 返回paymentCode的值
	 * @return String
	 */
	public String getPaymentCode(){

		return this.paymentCode;

	}
	/**
	 *
	 * @Title setPaymentCode
	 * @Description 设置paymentCode的值
	 * @param paymentCode
	 * @return void
	 */
	public void setPaymentCode(String paymentCode){

		this.paymentCode = paymentCode;

	}
	/**
	 *
	 * @Title getNickname
	 * @Description 返回nickname的值
	 * @return String
	 */
	public String getNickname(){

		return this.nickname;

	}
	/**
	 *
	 * @Title setNickname
	 * @Description 设置nickname的值
	 * @param nickname
	 * @return void
	 */
	public void setNickname(String nickname){

		this.nickname = nickname;

	}
	/**
	 *
	 * @Title getMobile
	 * @Description 返回mobile的值
	 * @return String
	 */
	public String getMobile(){

		return this.mobile;

	}
	/**
	 *
	 * @Title setMobile
	 * @Description 设置mobile的值
	 * @param mobile
	 * @return void
	 */
	public void setMobile(String mobile){

		this.mobile = mobile;

	}
	/**
	 *
	 * @Title getEmail
	 * @Description 返回email的值
	 * @return String
	 */
	public String getEmail(){

		return this.email;

	}
	/**
	 *
	 * @Title setEmail
	 * @Description 设置email的值
	 * @param email
	 * @return void
	 */
	public void setEmail(String email){

		this.email = email;

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
	 * @Title getLastLoginTime
	 * @Description 返回lastLoginTime的值
	 * @return Date
	 */
	public Date getLastLoginTime(){

		return this.lastLoginTime;

	}
	/**
	 *
	 * @Title setLastLoginTime
	 * @Description 设置lastLoginTime的值
	 * @param lastLoginTime
	 * @return void
	 */
	public void setLastLoginTime(Date lastLoginTime){

		this.lastLoginTime = lastLoginTime;

	}
}