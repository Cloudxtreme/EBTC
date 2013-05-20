package com.ebtc.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebtc.base.dao.GenericDao;
import com.ebtc.base.exception.BusinessException;
import com.ebtc.base.pagination.PaginationInfo;
import com.ebtc.base.pagination.PaginationResult;
import com.ebtc.base.pojo.Pojo;

public abstract interface GenericService <POJO extends Pojo,GENERICDAO extends GenericDao>{

	public POJO find(POJO pojo) throws BusinessException ;
	
	public int add(POJO pojo) throws BusinessException ;

	public int delete(POJO pojo) throws BusinessException ;

	public int update(POJO pojo) throws BusinessException ;

	public int count(POJO pojo) throws BusinessException ;

	public boolean isUnique(POJO pojo) throws BusinessException ;
	
	public List<POJO> query(POJO pojo) throws BusinessException ;

	public List<POJO> query(POJO pojo,boolean join) throws BusinessException ;
	
	public PaginationResult<POJO> query(POJO pojo, PaginationInfo pageInfo)
			throws BusinessException ;
	
	public PaginationResult<POJO> query(POJO pojo, PaginationInfo pageInfo,boolean join)
			throws BusinessException ;

}
