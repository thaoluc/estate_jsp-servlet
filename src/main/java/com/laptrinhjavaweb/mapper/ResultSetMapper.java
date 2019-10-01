package com.laptrinhjavaweb.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Entity;

public class ResultSetMapper<T> {
	public List<T> mapRow(ResultSet rs, Class<T> zClass){
		List<T> results = new ArrayList<>();
		try {
			if(zClass.isAnnotationPresent(Entity.class)) {
				
				ResultSetMetaData rsmd=rs.getMetaData(); 
				Field[] allFields = zClass.getDeclaredFields(); 
				
				while(rs.next()) {	
					T object = zClass.newInstance();
					
					for(int i=0; i<rsmd.getColumnCount(); i++) {	
						String columnName = rsmd.getColumnName(i+1);	
						
						Object columnValue = rs.getObject(i+1);
						
						ColumnModel columnModel = new ColumnModel();
						columnModel.setColumnName(columnName);
						columnModel.setColumnValue(columnValue);
						
						convertResultSetToEntity(allFields, columnModel, object);
						
						Class<?> parentClass = zClass.getSuperclass();	
						
						while (parentClass != null) {	
							Field[] fieldParents = parentClass.getDeclaredFields();				
							convertResultSetToEntity(fieldParents, columnModel, object);
							parentClass = parentClass.getSuperclass();	
						}
					}
					results.add(object);
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return results;
	}
	
	@SuppressWarnings("unchecked")
	private void convertResultSetToEntity (Field[] fields, ColumnModel columnModel, Object...objects ) {
		T object = (T) objects[0];
		try {
			for(Field field:fields) {
				if(field.isAnnotationPresent(Column.class)) {	
					
					Column column = field.getAnnotation(Column.class);
					
					if(column.name().equals(columnModel.getColumnName()) && columnModel.getColumnValue() != null) {
						
						BeanUtils.setProperty(object, field.getName(), columnModel.getColumnValue());
						break;
					}
				}
			}
		}catch( InvocationTargetException |  IllegalAccessException e) {
			System.out.println();
		}
		
	}
	
	static class ColumnModel {
		private String columnName;
		private Object columnValue;
		public String getColumnName() {
			return columnName;
		}
		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}
		public Object getColumnValue() {
			return columnValue;
		}
		public void setColumnValue(Object columnValue) {
			this.columnValue = columnValue;
		}
	
	}
}
