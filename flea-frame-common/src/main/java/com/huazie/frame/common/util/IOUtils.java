package com.huazie.frame.common.util;

import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * <p> JAVA输入和输出处理工具类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class IOUtils {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(IOUtils.class);

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
    public static String toString(InputStream inputStream, boolean base64Flag) {
        String input = "";
        byte[] bytes = toByteArray(inputStream);
        if (ArrayUtils.isNotEmpty(bytes)) {
            if (base64Flag) {
                input = Base64Helper.getInstance().encodeToString(bytes);
            } else {
                input = new String(bytes);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(new Object() {}, "INPUT = \n{}", input);
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
    public static String toString(File file, boolean base64Flag) {
        String input = "";
        try (InputStream inputStream = new FileInputStream(file)) {
            input = toString(inputStream, base64Flag);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error1(new Object() {},"文件转字符串出现异常：", e);
            }
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
    public static File toFile(String input, String filePath, boolean base64Flag) {
        File file = null;
        try (InputStream inputStream = toInputStream(input, base64Flag)) {
            file = toFile(inputStream, filePath);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error1(new Object() {},"字符串转文件出现异常：", e);
            }
        }
        return file;
    }

    /**
     * <p> 将输入流转成文件对象 </p>
     *
     * @param inputStream 文件输入流
     * @param filePath    文件路径
     * @return文件对象
     * @since 1.0.0
     */
    public static File toFile(InputStream inputStream, String filePath) {
        File file = new File(filePath);
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            byte[] bytes = toByteArray(inputStream);
            outputStream.write(bytes, 0, bytes.length);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error1(new Object() {},"文件输入流转文件出现异常：", e);
            }
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
    public static InputStream toInputStream(String input, boolean base64Flag) throws IOException {
        byte[] bytes;
        if (base64Flag) {
            bytes = Base64Helper.getInstance().decode(input);
        } else {
            bytes = input.getBytes();
        }
        return new ByteArrayInputStream(bytes);
    }

    /**
     * <p> 将输入流转换为 字节数组 </p>
     *
     * @param inputStream 输入流对象
     * @return 字节数组
     * @since 1.0.0
     */
    public static byte[] toByteArray(InputStream inputStream) {

        byte[] result = null;

        byte[] buffer = new byte[1024 << 8];

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            int n;
            while ((n = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, n);
            }
            result = outputStream.toByteArray();
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error1(new Object() {},"输入流转字节数组出现异常：", e);
            }
        }

        return result;
    }

    /**
     * <p> 获取文件内容 </p>
     *
     * @param resourceName 文件路径
     * @return 文件内容
     * @since 1.0.0
     */
    public static String toNativeStringFromResource(String resourceName) {
        String result = "";
        URL url = IOUtils.class.getClassLoader().getResource(resourceName);

        if (ObjectUtils.isNotEmpty(url)) {
            File file = new File(url.getFile());

            try (FileReader fileReader = new FileReader(file);
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String lineStr;
                StringBuilder content = new StringBuilder();
                while (null != (lineStr = bufferedReader.readLine())) {
                    content.append(lineStr).append("\n");
                }
                content.deleteCharAt(content.length() - 1); // 删除最后一个换行符
                result = content.toString();
            } catch (Exception e) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error1(new Object() {},"获取资源文件内容出现异常：", e);
                }
            }
        }

        return result;
    }

    /**
     * <p> 将文件内容写入指定文件 </p>
     *
     * @param content  文件内容
     * @param filePath 文件路径
     * @return
     */
    public static File toFileFromNativeString(String content, String filePath) {
        // 获取系统路径连接符
        String separator = File.separator;
        // 获取父目录
        String parentPathStr = filePath.substring(0, filePath.lastIndexOf(separator));
        File parentPath = new File(parentPathStr);
        if (!parentPath.exists()) {
            parentPath.mkdirs();
        }
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error1(new Object() {},"文件创建出现异常：", e);
                }
            }
        }

        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(content);
            bufferedWriter.flush();
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error1(new Object() {},"指定文件写内容出现异常：", e);
            }
        }
        return file;
    }

}
