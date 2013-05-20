package com.ebtc.menu.pojo;

import java.util.List;

import com.ebtc.base.pojo.Pojo;

/**
 *
 * @ClassName: Menu
 * @author Wayga_Chan 553806198@qq.com
 * @date 2013-05-16 09:02:34
 *
 */

public class Menu extends Pojo{
	/**
	*	主键
	*/
	private Integer id;
	/**
	*	父id
	*/
	private Integer parentId;
	/**
	*	名称
	*/
	private String name;
	/**
	*	uri
	*/
	private String uri;
	/**
	*	顺序
	*/
	private String seq;
	/**
	*	类型 0:用户 1管理员
	*/
	private String type;
	private List<Menu> subMenus;

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
	 * @Title getParentId
	 * @Description 返回parentId的值
	 * @return Integer
	 */
	public Integer getParentId(){

		return this.parentId;

	}
	/**
	 *
	 * @Title setParentId
	 * @Description 设置parentId的值
	 * @param parentId
	 * @return void
	 */
	public void setParentId(Integer parentId){

		this.parentId = parentId;

	}
	/**
	 *
	 * @Title getName
	 * @Description 返回name的值
	 * @return String
	 */
	public String getName(){

		return this.name;

	}
	/**
	 *
	 * @Title setName
	 * @Description 设置name的值
	 * @param name
	 * @return void
	 */
	public void setName(String name){

		this.name = name;

	}
	/**
	 *
	 * @Title getUri
	 * @Description 返回uri的值
	 * @return String
	 */
	public String getUri(){

		return this.uri;

	}
	/**
	 *
	 * @Title setUri
	 * @Description 设置uri的值
	 * @param uri
	 * @return void
	 */
	public void setUri(String uri){

		this.uri = uri;

	}
	/**
	 *
	 * @Title getSeq
	 * @Description 返回seq的值
	 * @return String
	 */
	public String getSeq(){

		return this.seq;

	}
	/**
	 *
	 * @Title setSeq
	 * @Description 设置seq的值
	 * @param seq
	 * @return void
	 */
	public void setSeq(String seq){

		this.seq = seq;

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
	 * @Title getSubMenus
	 * @Description 返回subMenus的值
	 * @return List<Menu>
	 */
	public List<Menu> getSubMenus(){

		return this.subMenus;

	}
	/**
	 *
	 * @Title setSubMenus
	 * @Description 设置subMenus的值
	 * @param subMenus
	 * @return void
	 */
	public void setSubMenus(List<Menu> subMenus){

		this.subMenus = subMenus;

	}
}