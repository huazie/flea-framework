package com.huazie.frame.common.util;

/**
 * <p> 数据转换工具类 </p>
 *
 * @author huazie
 * @version v1.0.0
 * @since 1.0.0
 */
public class DataConvert {

    /**
     * <p> 十六进制字符串转化为字节数组 </p>
     *
     * @param hex 十六进制字符串
     * @return 字节数组
     * @since 1.0.0
     */
    public static byte[] hex2byte(String hex) {
        if (StringUtils.isBlank(hex)) {
            return null;
        }
        int len = hex.length();
        int byteLen;
        if (len % 2 == 1) {
            byteLen = len / 2 + 1;
        }else{
            byteLen = len / 2;
        }

        byte[] bytes = new byte[byteLen];
        for (int i = 0; i < byteLen; i++) {
            if( i == byteLen - 1){
                if (len % 2 == 1) {
                    bytes[i] = (byte) Integer.parseInt(hex.substring(i * 2, i * 2 + 1), 16);
                    continue;
                }
            }
            bytes[i] = (byte) Integer.parseInt(hex.substring(i * 2, i * 2 + 2), 16);
        }
        return bytes;
    }

    /**
     * <p> 将字节数组转化为十六进制字符串 </p>
     *
     * @param bytes 字节数组
     * @return 十六进制字符串
     * @since 1.0.0
     */
    public static String byte2hex(byte[] bytes) {
        if (ArrayUtils.isEmpty(bytes)) {
            return null;
        }
        String hs = "";
        for (int n = 0; n < bytes.length; n++) {
            String stmp = (java.lang.Integer.toHexString(bytes[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }
}
