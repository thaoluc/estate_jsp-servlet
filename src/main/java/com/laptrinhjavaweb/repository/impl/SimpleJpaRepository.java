package com.laptrinhjavaweb.repository.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.annotation.Entity;
import com.laptrinhjavaweb.annotation.Table;
import com.laptrinhjavaweb.mapper.ResultSetMapper;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.repository.JpaRepository;

public class SimpleJpaRepository<T> implements JpaRepository<T> {

	private Class<T> zClass;
	
	@SuppressWarnings("unchecked")
	public SimpleJpaRepository() {
		Type type  = getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) type;
		zClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}
	@Override
	public List<T> findAll(Map<String, Object> properties, Pageable pageable, Object...where) {
		
		String tableName = "";
		
		if(zClass.isAnnotationPresent(Entity.class) && zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class); 
			tableName = table.name();	
		}
		
		StringBuilder sql = new StringBuilder( "Select * from "+tableName+" A"); 
		sql.append(" WHERE 1=1" );
		sql = createSQLfindAll(sql, properties);
		if(where != null && where.length > 0) {
			sql.append(where[0]);
		}
		sql.append(" limit "+pageable.getOffset()+", "+pageable.getLimit()+"");
		
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
		Connection connection = null;	
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = EntityManagerFactory.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql.toString());
			
			return resultSetMapper.mapRow(resultSet, this.zClass); 
		} catch (SQLException e) {
			return new ArrayList<>();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				return new ArrayList<>();
			}
		}
		
	}
	protected StringBuilder createSQLfindAll(StringBuilder where, Map<String, Object> params) {
		
		if(params != null && params.size() > 0) {
			String [] keys = new String [params.size()];
			Object [] values = new Object [params.size()];
			int i=0;
			for (Map.Entry<String, Object> item : params.entrySet()) {	//kw: foreach hashmap java
				keys[i] = item.getKey();
				values[i] = item.getValue();
				i++;
			}
			
			for(int i1 = 0; i1<keys.length; i1++) {
				if((values[i1] instanceof String) && (StringUtils.isNotBlank(values[i1].toString()))) {
					where.append(" AND LOWER (A."+ keys[i1]+") LIKE '%"+values[i1].toString()+"%' ");
				}else if ((values[i1] instanceof Integer) && (values[i1]!=null)) {
					where.append(" AND LOWER (A."+ keys[i1]+") = "+values[i1]+" "); 
				}else if ((values[i1] instanceof Long)&& (values[i1]!=null)) {
					where.append(" AND LOWER (A."+ keys[i1]+") = "+values[i1]+" "); 
				}
			}
		}
		return where;
	}
	
	
	@Override
	public List<T> findAll(Map<String, Object> properties, Object... where) {
		

		String tableName = "";
	
		if(zClass.isAnnotationPresent(Entity.class) && zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		
		StringBuilder sql = new StringBuilder( "Select * from "+tableName+" A where 1=1 "); 	
		sql = createSQLfindAll(sql, properties);
		if(where != null && where.length > 0) {
			sql.append(where[0]);
		}
		
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = EntityManagerFactory.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql.toString());
			
			return resultSetMapper.mapRow(resultSet, this.zClass); 
		} catch (SQLException e) {
			return new ArrayList<>();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				return new ArrayList<>();
			}
		}
		
	}
	
	@Override
	public List<T> findAll(String sqlSearch, Pageable pageable, Object... objects) {
		
		StringBuilder sql = new StringBuilder(sqlSearch); 
		
		sql.append(" limit "+pageable.getOffset()+", "+pageable.getLimit()+"");
		
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
		Connection connection = null;	
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = EntityManagerFactory.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql.toString());
			
			return resultSetMapper.mapRow(resultSet, this.zClass); 
		} catch (SQLException e) {
			return new ArrayList<>();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				return new ArrayList<>();
			}
		}

	}
	
}
