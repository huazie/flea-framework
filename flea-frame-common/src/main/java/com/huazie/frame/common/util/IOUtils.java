package com.huazie.frame.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * <p> JAVA输入和输出处理工具类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class IOUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(IOUtils.class);

    private final static BASE64Encoder encoder = new BASE64Encoder();

    private final static BASE64Decoder decoder = new BASE64Decoder();

    /**
     * <p> 根据文件路径获取文件输入流对象 </p>
     *
     * @param path 文件路径
     * @return 指定path文件的输入流对象
     * @since 1.0.0
     */
    public static InputStream getInputStreamFromClassPath(String path) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    }

    /**
     * <p> 将输入流转换为字符串 </p>
     *
     * @param inputStream 输入流对象
     * @return 字符串
     * @since 1.0.0
     */
    public static String toString(InputStream inputStream) {
        String input = "";
        byte[] bytes = toByteArray(inputStream);
        if (ArrayUtils.isNotEmpty(bytes)) {
            input = encoder.encodeBuffer(bytes);
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IOUtils##toString(File) input : \n{}", input);
        }
        return input;
    }

    /**
     * <p> 将文件对象转换成字符串 </p>
     *
     * @param file 文件对象
     * @return 字符串
     * @since 1.0.0
     */
    public static String toString(File file) {
        String input = "";
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            input = toString(inputStream);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("文件转字符串出现异常：", e);
            }
        } finally {
            close(inputStream);
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IOUtils##toString(File) input : \n{}", input);
        }
        return input;
    }

    /**
     * <p> 将字符串转成文件对象 </p>
     *
     * @param input 字符串
     * @return 文件对象
     * @since 1.0.0
     */
    public static File toFile(String input, String filePath) {
        File file = null;
        FileOutputStream outputStream = null;
        try {
            file = new File(filePath);
            byte[] bytes = toByteArray(toInputStream(input));
            outputStream = new FileOutputStream(file);
            outputStream.write(bytes, 0, bytes.length);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("字符串转文件出现异常：", e);
            }
        } finally {
            close(outputStream);
        }
        return file;
    }

    /**
     * <p> 将字符串转换为输入流 </p>
     *
     * @param input 字符串
     * @return 输入流对象
     * @since 1.0.0
     */
    public static InputStream toInputStream(String input) {
        InputStream inputStream = null;
        try {
            byte[] bytes = decoder.decodeBuffer(input);
            inputStream = new ByteArrayInputStream(bytes);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("字符串转输入流出现异常：", e);
            }
        }
        return inputStream;
    }

    /**
     * <p> 将输入流转换为 字节数组 </p>
     *
     * @param inputStream 输入流对象
     * @return 字节数组
     * @since 1.0.0
     */
    private static byte[] toByteArray(InputStream inputStream) {

        byte[] result = null;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 8];

        try {
            int n;
            while ((n = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, n);
            }
            result = outputStream.toByteArray();
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("输入流转字节数组出现异常：", e);
            }
        } finally {
            close(outputStream);
            close(inputStream);
        }

        return result;
    }

    /**
     * <p> 关闭输入流 </p>
     *
     * @param inputStream 输入流对象
     * @since 1.0.0
     */
    public static void close(InputStream inputStream) {
        if (ObjectUtils.isNotEmpty(inputStream)) {
            try {
                inputStream.close();
            } catch (Exception e) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error("关闭输入流出现异常：", e);
                }
            }
        }
    }

    /**
     * <p> 关闭输出流 </p>
     *
     * @param outputStream 输出流
     * @since 1.0.0
     */
    public static void close(OutputStream outputStream) {
        if (ObjectUtils.isNotEmpty(outputStream)) {
            try {
                outputStream.close();
            } catch (Exception e) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error("关闭输出流出现异常：", e);
                }
            }
        }
    }

}
