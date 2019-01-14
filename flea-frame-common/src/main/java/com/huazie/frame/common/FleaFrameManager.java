package com.huazie.frame.common;

import java.util.Locale;

/**
 *  Flea Frame Manager
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年11月15日
 *
 */
public class FleaFrameManager {
	
	private static volatile FleaFrameManager manager;
	
	private static ThreadLocal<Locale> sLocale = new ThreadLocal<Locale>();// 存储国际化标识，每个线程留有一个副本
	
	private FleaFrameManager(){
	}
	
	public static FleaFrameManager getManager(){
		if(null == manager){
			synchronized (FleaFrameManager.class) {
				if(null == manager){
					manager = new FleaFrameManager();
				}
			}
		}
		return manager;
	}
	
	/**
	 * 获取当前线程的国际化标识
	 * 
	 * @date 2018年11月18日
	 * @return 当前线程设置的国际化标识
	 */
	public Locale getLocale(){
		Locale locale = sLocale.get();
		if(null == locale){
			locale = Locale.getDefault();
		}
		return locale;
	}
	
	/**
	 * 设置当前线程的国际化标识
	 * 
	 * @date 2018年11月18日
	 * @param locale
	 *            待设置的国际化标识
	 */
	public void setLocale(Locale locale){
		sLocale.set(locale);
	}
	
}
