package com.ebtc.base.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.ebtc.base.dao.GenericDao;
import com.ebtc.base.pagination.PaginationContext;
import com.ebtc.base.pagination.PaginationInfo;
import com.ebtc.base.pagination.PaginationResult;
import com.ebtc.base.pojo.Pojo;

/**
 * 
 * @ClassName: GenericDaoImpl 
 * @Description: 泛型dao
 * @author Wayga_Chan 553806198@qq.com 
 * @date 2013-5-16 上午12:43:10 
 * 
 * @param <POJO>
 */
public abstract class GenericDaoImpl<POJO extends Pojo> implements GenericDao<POJO> {

	protected Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int add(POJO pojo) throws DataAccessException {
		String javaType = pojo.getClass().getName();
		String sqlName = getInsertName(javaType);
		return getSqlSession().insert(sqlName, pojo);
	}

	@Override
	public int delete(POJO pojo) throws DataAccessException {
		String javaType = pojo.getClass().getName();
		String sqlName = getDeleteName(javaType);
		return getSqlSession().delete(sqlName, pojo);
	}

	@Override
	public int update(POJO pojo) throws DataAccessException {
		String javaType = pojo.getClass().getName();
		String sqlName = getUpdateName(javaType);
		return getSqlSession().update(sqlName, pojo);
	}
	
	@Override
	public int count(POJO pojo) throws DataAccessException{
		String javaType = pojo.getClass().getName();
		String sqlName = getCountName(javaType);
		return getSqlSession().update(sqlName, pojo);
	}

	@Override
	public List<POJO> query(POJO pojo) throws DataAccessException {
		String javaType = pojo.getClass().getName();
		String sqlName = getNestedSelectName(javaType);
		return getSqlSession().selectList(sqlName, pojo);
	}

	@Override
	public PaginationResult<POJO> query(POJO pojo, PaginationInfo pageInfo)
			throws DataAccessException {
		PaginationContext.set(pageInfo);
		PaginationResult<POJO> pageResult = new PaginationResult<POJO>();
		pageResult.setData(query(pojo));
		pageResult.setPaginationInfo(pageInfo);
		return pageResult;
	}

	@Override
	public List<POJO> query(POJO pojo, boolean join) throws DataAccessException {
		String javaType = pojo.getClass().getName();
		String sqlName = null;
		if(join){
			sqlName = getJoinSelectName(javaType);
		}else{
			sqlName = getNestedSelectName(javaType);
		}
		return getSqlSession().selectList(sqlName, pojo);
	}

	@Override
	public PaginationResult<POJO> query(POJO pojo, PaginationInfo pageInfo,
			boolean join) throws DataAccessException {
		PaginationContext.set(pageInfo);
		PaginationResult<POJO> pageResult = new PaginationResult<POJO>();
		pageResult.setData(query(pojo,join));
		pageResult.setPaginationInfo(pageInfo);
		return pageResult;
	}
	
	/**
	 * 
	 * @Title	getJoinSelectName 
	 * @Description	返回连表查询select语句的名字(id)
	 * @param alias
	 * @return String
	 */
	private static String getJoinSelectName(String javaType) {
		javaType = javaType.replace(".pojo.", ".mapper.");
		int dotIndex = javaType.lastIndexOf('.');
		String alias = javaType.substring(dotIndex+1);
		String mapperName = javaType.substring(0, dotIndex+1)+"mapper."+alias+"Mapper.";
		mapperName += "joinSelect"+alias;
		return mapperName;
	}
	/**
	 * 
	 * @Title	getInsertName 
	 * @Description	获取插入语句名字(id)
	 * @param javaType
	 * @return String
	 */
	private static String getInsertName(String javaType){
		javaType = javaType.replace(".pojo.", ".mapper.");
		int dotIndex = javaType.lastIndexOf('.');
		String alias = javaType.substring(dotIndex+1);
		String mapperName = javaType.substring(0, dotIndex+1)+alias+"Mapper.";
		mapperName += "insert"+alias;
		return mapperName;
	}
	/**
	 * 
	 * @Title	getDeleteName 
	 * @Description	获取删除语句名字(id) 
	 * @param javaType
	 * @return String
	 */
	private static String getDeleteName(String javaType){
		javaType = javaType.replace(".pojo.", ".mapper.");
		int dotIndex = javaType.lastIndexOf('.');
		String alias = javaType.substring(dotIndex+1);
		String mapperName = javaType.substring(0, dotIndex+1)+alias+"Mapper.";
		mapperName += "delete"+alias;
		return mapperName;
	}
	
	/**
	 * 
	 * @Title	getUpdateName 
	 * @Description	获取更新语句名字(id) 
	 * @param javaType
	 * @return String
	 */
	private static String getUpdateName(String javaType){
		javaType = javaType.replace(".pojo.", ".mapper.");
		int dotIndex = javaType.lastIndexOf('.');
		String alias = javaType.substring(dotIndex+1);
		String mapperName = javaType.substring(0, dotIndex+1)+alias+"Mapper.";
		mapperName += "update"+alias;
		return mapperName;
	}
	
	/**
	 * 
	 * @Title	getCountName 
	 * @Description	获取查数量语句sql名字
	 * @param javaType
	 * @return String
	 */
	private String getCountName(String javaType) {
		javaType = javaType.replace(".pojo.", ".mapper.");
		int dotIndex = javaType.lastIndexOf('.');
		String alias = javaType.substring(dotIndex+1);
		String mapperName = javaType.substring(0, dotIndex+1)+alias+"Mapper.";
		mapperName += "count"+alias;
		return mapperName;
	}
	
	/**
	 * 
	 * @Title	getNestedQueryName 
	 * @Description	获取嵌套查询语句名字(id)
	 * @param javaType
	 * @return String
	 */
	private static String getNestedSelectName(String javaType){
		javaType = javaType.replace(".pojo.", ".mapper.");
		int dotIndex = javaType.lastIndexOf('.');
		String alias = javaType.substring(dotIndex+1);
		String mapperName = javaType.substring(0, dotIndex+1)+alias+"Mapper.";
		mapperName += "nestedSelect"+alias;
		return mapperName;
	}

	/**
	 * 
	 * @Title	getMapperName 
	 * @Description	传入alias返回Mapper的类名
	 * @param alias
	 * @return String
	 */
	private static String getMapperName(String alias){
		return alias+"Mapper";
	}

	public SqlSessionTemplate getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

}
