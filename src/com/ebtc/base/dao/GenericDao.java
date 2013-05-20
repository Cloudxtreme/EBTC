package com.ebtc.base.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ebtc.base.pagination.PaginationInfo;
import com.ebtc.base.pagination.PaginationResult;
import com.ebtc.base.pojo.Pojo;

public abstract interface GenericDao<POJO extends Pojo> {
	
	
	public int add(POJO pojo) throws DataAccessException ;

	public int delete(POJO pojo) throws DataAccessException ;

	public int update(POJO pojo) throws DataAccessException ;

	public int count(POJO pojo) throws DataAccessException;
	
	public List<POJO> query(POJO pojo) throws DataAccessException ;

	public List<POJO> query(POJO pojo,boolean join) throws DataAccessException ;
	
	public PaginationResult<POJO> query(POJO pojo, PaginationInfo pageInfo)
			throws DataAccessException ;
	
	public PaginationResult<POJO> query(POJO pojo, PaginationInfo pageInfo,boolean join)
			throws DataAccessException ;

}
