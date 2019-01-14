package com.huazie.frame.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huazie.frame.common.CommonConstants;

/**
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月29日
 *
 */
public class ReflectUtils {
	
	public final static Logger LOGGER = LoggerFactory.getLogger(ReflectUtils.class);

	/**
	 * 获取类实例对象
	 * 
	 * @date 2018年1月29日
	 * @param className
	 *            类名
	 * @return 实例化的类对象
	 */
	public static Object newInstance(String className){
		Object obj = null;
		try {
			Class<?> clazz = Class.forName(className);
			obj = clazz.newInstance();
		} catch (Exception e) {
			if(ReflectUtils.LOGGER.isErrorEnabled()){
				ReflectUtils.LOGGER.error("当前类反射出错，Class={}", className);
			}
		}
		return obj;
	}
	
	/**
	 * 获取对象指定属性的值
	 * 
	 * @date 2018年12月24日
	 *
	 * @param obj
	 *            待取值对象
	 * @param attrName
	 *            对象的一个属性
	 * @return 返回attrName属性对应的值
	 */
	public static Object getObjectAttrValue(Object obj, String attrName){
		Object value = null;
		try {
			Field field = obj.getClass().getDeclaredField(attrName);
			if(field != null){
				String getter = CommonConstants.MethodConstants.GET + StringUtils.toUpperCaseInitial(attrName);// 属性的get方法名
				Method method = obj.getClass().getMethod(getter, new Class[] {});
				value = method.invoke(obj, new Object[] {});
			}
		} catch (Exception e) {
			if(ReflectUtils.LOGGER.isErrorEnabled()){
				ReflectUtils.LOGGER.error("Exception={}", e);
			}
		}
		return value;
	}
	
}
