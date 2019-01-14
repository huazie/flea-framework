package com.huazie.frame.common.util;

/**
 *  Object工具类
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年6月2日
 *
 */
public class ObjectUtils {
	/**
	 * 
	 * 判断value是否为空
	 * 
	 * @version v1.0.0
	 * @date 2018年6月2日
	 *
	 * @param value
	 *            校验的对象
	 * @return 如果为空或null，返回true；否则返回false
	 */
	public static boolean isEmpty(Object value){
		if(value instanceof String){
			return value == null || "".equals(value);
		}
		return value == null;
	}
	
	/**
	 * 
	 * 判断value是否为空
	 * 
	 * @version v1.0.0
	 * @date 2018年6月2日
	 *
	 * @param value
	 * 		校验的对象
	 * @return
	 * 		如果不为空或null，返回true；否则返回false
	 */
	public static boolean isNotEmpty(Object value){
		return !isEmpty(value);
	}
}
