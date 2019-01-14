package com.huazie.frame.common.util;

import java.util.Random;
import java.util.UUID;

/**
 * 
 * @Description 随机码生成公共类
 * 
 * @author huazie
 * @version v1.0.0
 * @date 2017年2月19日
 *
 */
public class RandomCode {

	// 创建随机数对象
	private static Random random = new Random();

	/**
	 * 产生随机的数字，位数由len控制,以字符串形式返回
	 * 
	 * @param len
	 *            返回的字符串的长度
	 * @return
	 */
	public static String generateNumberCode(int len) {
		// 随机产生认证码(len位数字)
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}

	/**
	 * 产生随机的字母，位数由len控制。以连续的字符串形式返回
	 * 
	 * @param len
	 * @return
	 */
	public static String generateLettersCode(int len) {
		String sNum = "";
		for (int i = 0; i < len; i++) {

		}

		return sNum;
	}
	
	/**
	 * 
	 * @Description
	 * 
	 * @version v1.0.0
	 * @date 2017年3月19日
	 *
	 * @return
	 */
	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		// 去掉"-"符号
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
	}
}
