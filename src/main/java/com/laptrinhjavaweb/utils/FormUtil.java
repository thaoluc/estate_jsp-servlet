package com.laptrinhjavaweb.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class FormUtil {

	public static <T> T toModel(Class <T> zclass, HttpServletRequest request) {
		T object = null;
		try {
			object = zclass.newInstance();
			BeanUtils.populate(object, request.getParameterMap());	//populate: đổ dl lấy từ request vào object, map params vs object key
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return object;
	}
}
