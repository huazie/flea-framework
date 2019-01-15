package com.huazie.frame.common.util;

/**
 *  Unicode工具类
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年8月19日
 *
 */
public class UnicodeUtils {
	
	private final static int LENGTH_OF_SINGLE_UNICODE = 4;
	private final static String COMPLEMENT_UNICODE_STRING = "0";
	
	/**
	 *  本地字符串转换为Unicode字符串
	 * 
	 * @date 2018年9月3日
	 *
	 * @param nativeStr
	 *            本地字符串
	 * @return
	 * @throws Exception
	 */
	public static String nativeToUnicode(String nativeStr)throws Exception{
		StringBuilder strBuilder = new StringBuilder(); 
		if(StringUtils.isNotBlank(nativeStr)){
			for(int i = 0; i < nativeStr.length(); i++){
				char ch = nativeStr.charAt(i);
				String hex = Integer.toHexString(ch);
				if(StringUtils.isNotBlank(hex)){
					int len = hex.length();
					if(len < LENGTH_OF_SINGLE_UNICODE){
						for(int j = 0; j < LENGTH_OF_SINGLE_UNICODE - len; j++){
							hex = COMPLEMENT_UNICODE_STRING + hex;
						}
					}
					strBuilder.append("\\u").append(hex);
				}
			}
		}
		return strBuilder.toString();
	}
	
	/**
	 *  Unicode字符串转换为本地字符串
	 * 
	 * @date 2018年9月3日
	 *
	 * @param unicodeStr
	 *            unicode字符串
	 * @return
	 * @throws Exception
	 */
	public static String unicodeToNative(String unicodeStr)throws Exception{
		StringBuilder strBuilder = new StringBuilder();
		if(StringUtils.isNotBlank(unicodeStr)){
			String[] unicodeArr = unicodeStr.split("\\\\u");
			if(ArrayUtils.isNotEmpty(unicodeArr)){
				for(int i = 0; i < unicodeArr.length; i++){
					String unicode = unicodeArr[i];
					if(StringUtils.isNotBlank(unicode)){
						int unicodeInt = Integer.valueOf(unicodeArr[i], 16);
						strBuilder.append((char)unicodeInt);
					}
				}
			}
		}
		return strBuilder.toString();
	}
	
	/**
	 * 本地文件转成Unicode文件
	 * 
	 * @date 2018年10月10日
	 *
	 * @throws Exception
	 */
	public static void fileNativeToUnicode(String exePath, String nativeFilePath, String unicodeFilePath)throws Exception{
		String[] cmd = {exePath, "-encoding", "utf-8", nativeFilePath, unicodeFilePath};
		Runtime.getRuntime().exec(cmd);
	}
	
	/**
	 * Unicode文件转成本地文件
	 * 
	 * @date 2018年10月10日
	 *
	 * @throws Exception
	 */
	public static void fileUnicodeToNative(String exePath, String unicodeFilePath, String nativeFilePath)throws Exception{
		String[] cmd = {exePath, "-reverse", unicodeFilePath, nativeFilePath};
		Runtime.getRuntime().exec(cmd);
	}
	
}
