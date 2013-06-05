package com.ebtc.operation.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @ClassName: TradeOrderInfo 
 * @Description: 委托单信息
 * @author Wayga_Chan 553806198@qq.com 
 * @date 2013-6-1 下午3:22:49 
 *
 */
public class TradeOrderInfo implements Serializable{

	/** 
	 * @Fields serialVersionUID : 序列化id
	 */ 
	private static final long serialVersionUID = -7763266184976747748L;
	
	/**
	*	单价 1个虚拟币的价格
	*/
	private BigDecimal price;
	/**
	*	数量
	*/
	private BigDecimal quantity;
	/**
	 * 交易类型
	 */
	private String type;
	
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
