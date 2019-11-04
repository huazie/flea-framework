package com.huazie.frame.common.util;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;

/**
 * <p> 数据处理工具类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class DataHandleUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataHandleUtils.class);

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
        } else {
            byteLen = len / 2;
        }

        byte[] bytes = new byte[byteLen];
        for (int i = 0; i < byteLen; i++) {
            if (i == byteLen - 1) {
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

    /**
     * <p> 数据压缩(gzip) </p>
     *
     * @param originalStr 原始字符串
     * @return 压缩后的字符串（Base64编码了）
     * @since 1.0.0
     */
    public static String gzip(String originalStr) {
        if (StringUtils.isBlank(originalStr)) {
            return null;
        }

        String compressedStr = null;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
            gzipOutputStream.write(originalStr.getBytes());
            compressedStr = new String(new Base64().encode(byteArrayOutputStream.toByteArray()));
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("采用gzip方式压缩数据异常：", e);
            }
        }
        return compressedStr;
    }

    public static String ungzip(String input) {
        if (StringUtils.isNotBlank(input)) {
            return null;
        }

        return null;
    }
}
