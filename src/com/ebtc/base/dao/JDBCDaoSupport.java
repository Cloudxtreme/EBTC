package com.ebtc.base.dao;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.ebtc.base.pagination.Dialect;
import com.ebtc.base.pagination.PaginationInfo;
import com.ebtc.base.pagination.PaginationResult;

public class JDBCDaoSupport {

	@Autowired
	private BasicDataSource dataSource;
	
	private String dataBaseType = "mysql";
	
	private Dialect dialect;
	
	public JDBCDaoSupport(){
		dialect = new Dialect(Dialect.DatabaseType.valueOf(this.dataBaseType));
	}
	
	public int update(String sql,Object[] params) throws SQLException{
		int row = 0;
		Connection conn = dataSource.getConnection();
		PreparedStatement preStat = conn.prepareStatement(sql);
		if(params != null && params.length > 0){
			for(int i=0; i<params.length; ++i){
				Object param = params[i];
				Class clazz = param.getClass();
				int index = i+1;
				if(clazz.equals(Integer.class)){
					preStat.setInt(index, (Integer) param);
				}else if(clazz.equals(Short.class)){
					preStat.setShort(index, (Short) param);
				}else if(clazz.equals(Long.class)){
					preStat.setLong(index, (Long) param);
				}else if(clazz.equals(Float.class)){
					preStat.setFloat(index, (Float) param);
				}else if(clazz.equals(Double.class)){
					preStat.setDouble(index, (Double) param);
				}else if(clazz.equals(Byte.class)){
					preStat.setByte(index, (Byte) param);
				}else if(clazz.equals(Byte[].class)){
					preStat.setBytes(index, (byte[]) param);
				}else if(clazz.equals(String.class)){
					preStat.setString(index, (String) param);
				}else if(clazz.equals(Boolean.class)){
					preStat.setBoolean(index, (Boolean) param);
				}else if(clazz.equals(BigDecimal.class)){
					preStat.setBigDecimal(index, (BigDecimal) param);
				}else if(clazz.equals(Blob.class)){
					preStat.setBlob(index, (Blob) param);
				}else if(clazz.equals(Time.class)){
					preStat.setTime(index, (Time) param);
				}else if(clazz.equals(Date.class)){
					preStat.setDate(index, (Date) param);
				}else if(clazz.equals(Timestamp.class)){
					preStat.setTimestamp(index, (Timestamp) param);
				}else if(clazz.equals(java.util.Date.class)){
					preStat.setTimestamp(index,new Timestamp(((java.util.Date)param).getTime()));
				}else if(clazz.equals(SQLXML.class)){
					preStat.setSQLXML(index, (SQLXML) param);
				}else if(clazz.equals(URL.class)){
					preStat.setURL(index, (URL) param);
				}else{
					preStat.setObject(index, param);
				}
			}
			row = preStat.executeUpdate();
		}
		return row;
	}
	
	public PaginationResult query(String sql,Object[] params,PaginationInfo pageInfo) throws Exception{
		PaginationResult pageResult = null;
		List<HashMap<String, Object>> data = null;
		String pageSql = dialect.getPaginationSql(sql, pageInfo);
		data = query(sql,params);
		pageResult.setData(data);
		
		String totalRowNumSql = dialect.getTotalRowNumSql(sql);
		List<HashMap<String, Object>> rowData = query(totalRowNumSql,params);
		if(rowData != null && rowData.size() > 0){
			int rowNum = (Integer) rowData.get(0).get("COUNT(*)");
			int pageNum = rowNum / pageInfo.getPageSize() + 1;
			pageInfo.setTotalPageNum(pageNum);
			pageInfo.setTotalRowNum(rowNum);
		}
		pageResult.setPaginationInfo(pageInfo);
		
		return pageResult;
	}
	
	public List<HashMap<String,Object>> query(String sql,Object[] params) throws SQLException{
		List<HashMap<String, Object>> result = null;
		Connection conn = dataSource.getConnection();
		PreparedStatement preStat = conn.prepareStatement(sql);
		if(params != null && params.length > 0){
			for(int i=0; i<params.length; ++i){
				Object param = params[i];
				Class clazz = param.getClass();
				int index = i+1;
				if(clazz.equals(Integer.class)){
					preStat.setInt(index, (Integer) param);
				}else if(clazz.equals(Short.class)){
					preStat.setShort(index, (Short) param);
				}else if(clazz.equals(Long.class)){
					preStat.setLong(index, (Long) param);
				}else if(clazz.equals(Float.class)){
					preStat.setFloat(index, (Float) param);
				}else if(clazz.equals(Double.class)){
					preStat.setDouble(index, (Double) param);
				}else if(clazz.equals(Byte.class)){
					preStat.setByte(index, (Byte) param);
				}else if(clazz.equals(Byte[].class)){
					preStat.setBytes(index, (byte[]) param);
				}else if(clazz.equals(String.class)){
					preStat.setString(index, (String) param);
				}else if(clazz.equals(Boolean.class)){
					preStat.setBoolean(index, (Boolean) param);
				}else if(clazz.equals(BigDecimal.class)){
					preStat.setBigDecimal(index, (BigDecimal) param);
				}else if(clazz.equals(Blob.class)){
					preStat.setBlob(index, (Blob) param);
				}else if(clazz.equals(Time.class)){
					preStat.setTime(index, (Time) param);
				}else if(clazz.equals(Date.class)){
					preStat.setDate(index, (Date) param);
				}else if(clazz.equals(Timestamp.class)){
					preStat.setTimestamp(index, (Timestamp) param);
				}else if(clazz.equals(java.util.Date.class)){
					preStat.setTimestamp(index,new Timestamp(((java.util.Date)param).getTime()));
				}else if(clazz.equals(SQLXML.class)){
					preStat.setSQLXML(index, (SQLXML) param);
				}else if(clazz.equals(URL.class)){
					preStat.setURL(index, (URL) param);
				}else{
					preStat.setObject(index, param);
				}
			}
		}
		ResultSet rs = preStat.executeQuery();
		if(rs != null){
			rs.last();
			int rowCount = rs.getRow();
			rs.beforeFirst();
			ResultSetMetaData metaData = rs.getMetaData();
			int colCount = metaData.getColumnCount();
			result = new ArrayList<HashMap<String,Object>>(rowCount);
			while(rs.next()){
				HashMap<String,Object> rowData = new HashMap<String,Object>();
				for(int i=0; i<colCount; ++i){
					rowData.put(metaData.getColumnName(i+1), rs.getObject(i+1));
				}
				result.add(rowData);
			}
		}
		return result;
	}
	
	public static void main(String[] args){
		Object o = 2;
		System.out.println(o.getClass());
	}
	
	public Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}

	public BasicDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(BasicDataSource dataSource) {
		this.dataSource = dataSource;
	}
}
