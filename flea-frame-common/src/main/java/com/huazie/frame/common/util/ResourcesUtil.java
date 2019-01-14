package com.huazie.frame.common.util;

import java.io.InputStream;

/**
 *  <p>
 *  	资源文件获取工具类
 *  </p>
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月26日
 *
 */
public class ResourcesUtil {
	
	public static InputStream getInputStreamFromClassPath(String path){
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		return input;
	}
	
}
