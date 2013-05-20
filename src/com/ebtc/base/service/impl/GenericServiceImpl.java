package com.ebtc.base.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebtc.base.dao.GenericDao;
import com.ebtc.base.exception.BusinessException;
import com.ebtc.base.pagination.PaginationInfo;
import com.ebtc.base.pagination.PaginationResult;
import com.ebtc.base.pojo.Pojo;
import com.ebtc.base.service.GenericService;

@Service
public abstract class GenericServiceImpl<POJO extends Pojo,GENERICDAO extends GenericDao> implements GenericService<POJO, GENERICDAO> {

	protected Logger log = Logger.getLogger(this.getClass());
	
	
	@Override
	public int add(POJO pojo) throws BusinessException {
		if(pojo != null)
			return getDao().add(pojo);
		throw new BusinessException("操作失败，数据为null");
	}

	@Override
	public int delete(POJO pojo) throws BusinessException {
		if(pojo != null)
			return getDao().delete(pojo);
		throw new BusinessException("操作失败，数据为null");
	}

	@Override
	public int update(POJO pojo) throws BusinessException {
		if(pojo != null)
			return getDao().update(pojo);
		throw new BusinessException("操作失败，数据为null");
	}
	
	@Override
	public int count(POJO pojo) throws BusinessException {
		if(pojo != null)
			return getDao().count(pojo);
		throw new BusinessException("操作失败，数据为null");
	}
	
	@Override
	public POJO find(POJO pojo) throws BusinessException {
		POJO result = null;
		PaginationInfo pageInfo = new PaginationInfo();
		pageInfo.setPageSize(1);
		pageInfo.setCurrentPage(1);
		List<POJO> list = query(pojo,pageInfo).getData();
		if(list != null && list.size() > 0){
			result = list.get(0);
		}
		return result;
	}
	
	/**
	 * 
	 * @Title	isUnique 
	 * @Description	检测唯一性
	 * @param pojo
	 * @return
	 * @throws BusinessException boolean
	 */
	public boolean isUnique(POJO pojo) throws BusinessException {
		if(count(pojo) <= 0){
			return true;
		}
		return false;
	}
	
	@Override
	public List<POJO> query(POJO pojo) throws BusinessException {
		if(pojo != null)
			return getDao().query(pojo);
		throw new BusinessException("操作失败，数据为null");
	}

	@Override
	public PaginationResult<POJO> query(POJO pojo, PaginationInfo pageInfo)
			throws BusinessException {
		if(pojo != null)
			return getDao().query(pojo,pageInfo);
		throw new BusinessException("操作失败，数据为null");
	}

	@Override
	public List<POJO> query(POJO pojo, boolean join) throws BusinessException {
		if(pojo != null)
			return getDao().query(pojo,join);
		throw new BusinessException("操作失败，数据为null");
	}

	@Override
	public PaginationResult<POJO> query(POJO pojo, PaginationInfo pageInfo,
			boolean join) throws BusinessException {
		if(pojo != null)
			return getDao().query(pojo,pageInfo,join);
		throw new BusinessException("操作失败，数据为null");
	}

	public abstract GENERICDAO getDao();

	
}
