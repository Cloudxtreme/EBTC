package com.ebtc.base.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebtc.base.pagination.PaginationInfo;
import com.ebtc.base.pagination.PaginationResult;
import com.ebtc.base.pojo.Pojo;

/**
 * 
 * @ClassName: GenericDao 
 * @Description: 泛型dao接口
 * @author Wayga_Chan 553806198@qq.com 
 * @date 2013-5-26 上午11:00:58 
 * 
 * @param <POJO>
 */
public abstract interface GenericDao<POJO extends Pojo> {
	
	/**
	 * 
	 * @Title	add 
	 * @Description	添加一条记录
	 * @param pojo
	 * @return int
	 * @throws DataAccessException
	 */
	public int add(POJO pojo) throws DataAccessException ;

	/**
	 * 
	 * @Title	delete 
	 * @Description	根据id删除一条记录
	 * @param pojo
	 * @return int
	 * @throws DataAccessException
	 */
	public int delete(POJO pojo) throws DataAccessException ;
	/**
	 * 
	 * @Title	update 
	 * @Description	根据id更新一条记录 
	 * @param pojo
	 * @return int
	 * @throws DataAccessException
	 */
	public int update(POJO pojo) throws DataAccessException ;

	/**
	 * 
	 * @Title	count 
	 * @Description	根据属性做条件查询符合条件的记录数
	 * @param pojo
	 * @return int
	 * @throws DataAccessException
	 */
	public int count(POJO pojo) throws DataAccessException;
	
	/**
	 * 
	 * @Title	query 
	 * @Description	根据属性作为条件,查询符合条件的记录
	 * @param pojo	封装了参数的实体类
	 * @return List<POJO>
	 * @throws DataAccessException
	 */
	public List<POJO> query(POJO pojo) throws DataAccessException ;

	/**
	 * 
	 * @Title	query 
	 * @Description	根据属性作为条件,查询符合条件的记录
	 * @param pojo 	封装了参数的实体类
	 * @param join	是否采用join查询（有配置相应sql时候才可以使用）
	 * @return List<POJO>
	 * @throws DataAccessException 
	 */
	public List<POJO> query(POJO pojo,boolean join) throws DataAccessException ;
	
	/**
	 * 
	 * @Title	query 
	 * @Description	根据属性作为条件,查询符合条件的记录（带分页）
	 * @param pojo 	封装了参数的实体类
	 * @return PaginationResult<POJO> 
	 * @throws DataAccessException
	 */
	public PaginationResult<POJO> query(POJO pojo, PaginationInfo pageInfo)
			throws DataAccessException ;
	
	/**
	 * 
	 * @Title	query 
	 * @Description	根据属性作为条件,查询符合条件的记录（带分页）
	 * @param pojo 	封装了参数的实体类
	 * @param join	是否采用join查询（有配置相应sql时候才可以使用）
	 * @return PaginationResult<POJO> 
	 * @throws DataAccessException
	 */
	public PaginationResult<POJO> query(POJO pojo, PaginationInfo pageInfo,boolean join)
			throws DataAccessException ;

	/**
	 * 
	 * @Title	executeUpdate 
	 * @Description	执行某个更新sql
	 * @param mapperLocation sqlMap的映射全限定名
	 * @return int
	 * @throws DataAccessException
	 */
	public int executeUpdate(String mapperLocation,Object param) throws DataAccessException;
	
	/**
	 * 
	 * @Title	executeQuery 
	 * @Description	执行某个查询sql
	 * @param mapperLocation
	 * @param param
	 * @return List<POJO>
	 * @throws DataAccessException 
	 */
	public List<POJO> executeQuery(String mapperLocation,HashMap param) throws DataAccessException;
	
	/**
	 * 
	 * @Title	executeQuery 
	 * @Description	执行某个查询sql（分页）
	 * @param mapperLocation
	 * @param param
	 * @param pageInfo
	 * @return
	 * @throws DataAccessException PaginationResult<POJO>
	 */
	public PaginationResult<POJO> executeQuery(String mapperLocation,HashMap param,PaginationInfo pageInfo) throws DataAccessException;
	
}
