package com.huazie.frame.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

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

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("DataHandleUtils#gzip(String) Original Length = {}", originalStr.length());
        }

        String compressedStr = null;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
                gzipOutputStream.write(originalStr.getBytes());
            } finally {
                // 获取输出流的数据之前，需要将gzipOutputStream关闭
                compressedStr = new String(Base64Helper.getInstance().encode(byteArrayOutputStream.toByteArray()));
            }
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("采用gzip方式压缩数据异常：", e);
            }
        }

        if (LOGGER.isDebugEnabled()) {
            if (StringUtils.isNotBlank(compressedStr)) {
                LOGGER.debug("DataHandleUtils#gzip(String) Compressed Length = {}", compressedStr.length());
            }
        }

        return compressedStr;
    }

    /**
     * <p> 数据解压(gzip)</p>
     *
     * @param compressedStr 压缩后的字符串
     * @return 原始字符串
     * @since 1.0.0
     */
    public static String unGzip(String compressedStr) {
        if (StringUtils.isBlank(compressedStr)) {
            return null;
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("DataHandleUtils#unGzip(String) Compressed Length = {}", compressedStr.length());
        }

        String originalStr = null;
        byte[] compressedArr = Base64Helper.getInstance().decode(compressedStr.getBytes());
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(compressedArr);
             GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream)) {
            originalStr = IOUtils.toString(gzipInputStream, false);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("采用gzip方式解压数据异常：", e);
            }
        }

        if (LOGGER.isDebugEnabled()) {
            if (StringUtils.isNotBlank(originalStr)) {
                LOGGER.debug("DataHandleUtils#unGzip(String) Original Length = {}", originalStr.length());
            }
        }

        return originalStr;
    }

    /**
     * <p> 数据压缩(zip) </p>
     *
     * @param originalStr 原始字符串
     * @return 压缩后的字符串（Base64编码）
     * @since 1.0.0
     */
    public static String zip(String originalStr) {
        if (StringUtils.isBlank(originalStr)) {
            return null;
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("DataHandleUtils#zip(String) Original Length = {}", originalStr.length());
        }

        String compressedStr = null;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream)) {
            // 开始写入新的 ZIP 文件条目并将流定位到条目数据的开始处
            zipOutputStream.putNextEntry(new ZipEntry("zip"));
            zipOutputStream.write(originalStr.getBytes());
            // 关闭当前的 ZIP 条目并定位流以读取下一个条目
            zipOutputStream.closeEntry();

            compressedStr = new String(Base64Helper.getInstance().encode(byteArrayOutputStream.toByteArray()));
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("采用zip方式压缩数据异常：", e);
            }
        }

        if (LOGGER.isDebugEnabled()) {
            if (StringUtils.isNotBlank(compressedStr)) {
                LOGGER.debug("DataHandleUtils#zip(String) Compressed Length = {}", compressedStr.length());
            }
        }

        return compressedStr;
    }

    /**
     * <p> 数据解压(zip) </p>
     *
     * @param compressedStr 压缩后的字符串(Base64编码)
     * @return 原始字符串
     * @since 1.0.0
     */
    public static String unZip(String compressedStr) {
        if (StringUtils.isBlank(compressedStr)) {
            return null;
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("DataHandleUtils#unZip(String) Compressed Length = {}", compressedStr.length());
        }

        String originalStr = null;
        byte[] compressedArr = Base64Helper.getInstance().decode(compressedStr.getBytes());
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(compressedArr);
             ZipInputStream zipInputStream = new ZipInputStream(byteArrayInputStream)) {
            zipInputStream.getNextEntry();
            originalStr = IOUtils.toString(zipInputStream, false);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("采用zip方式解压数据异常：", e);
            }
        }

        if (LOGGER.isDebugEnabled()) {
            if (StringUtils.isNotBlank(originalStr)) {
                LOGGER.debug("DataHandleUtils#unZip(String) Original Length = {}", originalStr.length());
            }
        }

        return originalStr;
    }

}
