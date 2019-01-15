package com.huazie.frame.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 		加密解密工具类
 * </p>
 * @author huazie
 * @version v1.0.0
 * @date 2016年9月29日
 * 
 */
public class EncryptUtils {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(EncryptUtils.class);
	
	private final static String AES = "AES";
	
	private final static String DES = "DES";
	
	private final static String MD5 = "MD5";
	
	private final static String SHA_1 = "SHA-1";
	
	/**
	 * <p>进行MD5加密</p>
	 * 
	 * @version v1.0.0
	 * @date 2016年9月29日
	 * 
	 * @param info
	 *            要加密的信息
	 * @return String 加密后的字符串
	 */
    public static String encryptToMD5(String info) {
        byte[] digestInfo = null;
        try {
            // 得到一个md5的消息摘要
            MessageDigest messageDigest = MessageDigest.getInstance(MD5);
            // 添加要进行计算摘要的信息
            messageDigest.update(info.getBytes());
            // 得到该摘要
            digestInfo = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
        	LOGGER.error("EncryptUtils##encryptToMD5 Exception={}", e);
        }
        // 将摘要转为字符串
        String result = byte2hex(digestInfo);
        return result;
    }

    /**
     * 
     * @Description 进行SHA加密
     * 
     * @version v1.0.0
     * @date 2016年9月29日
     *
     * @param info 要加密的信息
     * @return String 加密后的字符串
     */
    public static String encryptToSHA(String info) {
        byte[] digestInfo = null;
        try {
            // 得到一个SHA-1的消息摘要
            MessageDigest alga = MessageDigest.getInstance(SHA_1);
            // 添加要进行计算摘要的信息
            alga.update(info.getBytes());
            // 得到该摘要
            digestInfo = alga.digest();
        } catch (NoSuchAlgorithmException e) {
        	LOGGER.error("EncryptUtils##encryptToSHA(info)=", e);
        }
        // 将摘要转为字符串
        String result = byte2hex(digestInfo);
        return result;
    }
    
    /**
     * 
     * @Description 得到AES加密的key
     * 
     * @version v1.0.0
     * @date 2016年9月29日
     *
     * @param src
     * 	
     * @return
     */
    public static String getAESKey(String src){
        return getKey(AES, src);
    }
    
    /**
     * 
     * @Description 得到DES加密的key
     * 
     * @version v1.0.0
     * @date 2016年9月29日
     *
     * @param src
     * @return
     */
    public static String getDESKey(String src){
        return getKey(DES, src);
    }
    
    /**
	 * 
	 * @Description 根据一定的算法得到相应的key
	 * 
	 * @version v1.0.0
	 * @date 2016年9月29日
	 *
	 * @param algorithm
	 *            加密算法的字符串表示
	 * @param src
	 * @return
	 */
    private static String getKey(String algorithm, String src){
        if(algorithm.equals(AES)){
            return src.substring(0, 16);
        }else if(algorithm.equals(DES)){
            return src.substring(0, 8);
        }else{
            return null;
        }
    }
    
    /**
     * 
     * @Description 创建一个AES的密钥
     * 
     * @version v1.0.0
     * @date 2016年9月29日
     *
     * @return
     */
    public static SecretKey createSecretAESKey() {
        return createSecretKey(AES);
    }

    /**
     * 
     * @Description 创建一个DES的密钥
     * 
     * @version v1.0.0
     * @date 2016年9月29日
     *
     * @return
     */
    public static SecretKey createSecretDESKey() {
        return createSecretKey(DES);
    }
    
	/**
	 * 
	 * @Description 创建密匙
	 * 
	 * @version v1.0.0
	 * @date 2016年9月29日
	 * 
	 * @param algorithm
	 *            加密算法,可用 AES, DES, DESede, Blowfish
	 * @return SecretKey 秘密（对称）密钥
	 */
    public static SecretKey createSecretKey(String algorithm) {
        // 声明KeyGenerator对象
        KeyGenerator keygen;
        // 声明 密钥对象
        SecretKey deskey = null;
        try {
            // 返回生成指定算法的秘密密钥的 KeyGenerator 对象
            keygen = KeyGenerator.getInstance(algorithm);
            // 生成一个密钥
            deskey = keygen.generateKey();
        } catch (NoSuchAlgorithmException e) {
        	LOGGER.error("EncryptUtils##createSecretKey(algorithm)=", e);
        }
        // 返回密匙
        return deskey;
    }

	/**
	 * 
	 * @Description 根据相应的加密算法、密钥、源文件进行加密，返回加密后的文件
	 * 
	 * @version v1.0.0
	 * @date 2016年9月29日
	 *
	 * @param algorithm
	 *            加密算法:DES,AES
	 * @param key
	 * @param info
	 * @return
	 */
    public static String encrypt(String algorithm, SecretKey key, String info) {
        // 定义要生成的密文
        byte[] cipherByte = null;
        try {
            // 得到加密/解密器
            Cipher c1 = Cipher.getInstance(algorithm);
            // 用指定的密钥和模式初始化Cipher对象
            // 参数:(ENCRYPT_MODE, DECRYPT_MODE, WRAP_MODE,UNWRAP_MODE)
            c1.init(Cipher.ENCRYPT_MODE, key);
            // 对要加密的内容进行编码处理,
            cipherByte = c1.doFinal(info.getBytes());
        } catch (Exception e) {
        	LOGGER.error("EncryptUtils##encrypt(algorithm,key,info)=", e);
        }
        // 返回密文的十六进制形式
        return byte2hex(cipherByte);
    }

    /**
     * 
     * @Description 根据相应的解密算法、密钥和需要解密的文本进行解密，返回解密后的文本内容
     * 
     * @version v1.0.0
     * @date 2016年9月29日
     *
     * @param algorithm
     *  		加密算法:DES,AES
     * @param key
     * @param info
     * @return
     */
    public static String decrypt(String algorithm, SecretKey key, String info) {
        byte[] cipherByte = null;
        try {
            // 得到加密/解密器
            Cipher c1 = Cipher.getInstance(algorithm);
            // 用指定的密钥和模式初始化Cipher对象
            c1.init(Cipher.DECRYPT_MODE, key);
            // 对要解密的内容进行编码处理
            cipherByte = c1.doFinal(hex2byte(info));
        } catch (Exception e) {
        	LOGGER.error("EncryptUtils##decrypt(algorithm,key,info)=", e);
        }
        return new String(cipherByte);
    }

    /**
     * @Description 根据相应的解密算法、指定的密钥和需要解密的文本进行解密，返回解密后的文本内容
     * @param algorithm 加密算法:DES,AES
     * @param key 这个key可以由用户自己指定 注意AES的长度为16位,DES的长度为8位
     * @param info
     * @return
     */
    public static String decrypt(String algorithm, String key, String info) throws Exception {
        try {
            // 判断Key是否正确
            if (key == null) {
                throw new Exception("Key为空null");
            }
            // 判断采用AES加解密方式的Key是否为16位
            if (algorithm.equals("AES") && key.length() != 16) {
                throw new Exception("Key长度不是16位");
            }
            // 判断采用DES加解密方式的Key是否为8位
            if (algorithm.equals("DES") && key.length() != 8) {
                throw new Exception("Key长度不是8位");
            }
            byte[] raw = key.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = hex2byte(info);

            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return originalString;

        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * @Description 根据相应的加密算法、指定的密钥、源文件进行加密，返回加密后的文件
     * @param algorithm 加密算法:DES,AES
     * @param key 这个key可以由用户自己指定 注意AES的长度为16位,DES的长度为8位
     * @param info
     * @return
     */
    public static String encrypt(String algorithm, String key, String info) throws Exception {
        // 判断Key是否正确
        if (key == null) {
            throw new Exception("Key为空null");
        }
        // 判断采用AES加解密方式的Key是否为16位
        if (algorithm.equals("AES") && key.length() != 16) {
            throw new Exception("Key长度不是16位");
        }
        // 判断采用DES加解密方式的Key是否为8位
        if (algorithm.equals("DES") && key.length() != 8) {
            throw new Exception("Key长度不是8位");
        }
        byte[] raw = key.getBytes("ASCII");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(info.getBytes());
        return byte2hex(encrypted);
    }

    /**
     * @Description 采用DES随机生成的密钥进行加密
     * @param key
     * @param info
     * @return
     */
    public static String encryptToDES(SecretKey key, String info) {
        return encrypt("DES", key, info);
    }

    /**
     * @Description 采用DES指定密钥的方式进行加密
     * @param key
     * @param info
     * @return
     * @throws Exception
     */
    public static String encryptToDES(String key, String info) throws Exception {
        return encrypt("DES", key, info);
    }

    /**
     * @Description 采用DES随机生成密钥的方式进行解密，密钥需要与加密的生成的密钥一样
     * @param key
     * @param info
     * @return
     */
    public static String decryptByDES(SecretKey key, String info) {
        return decrypt("DES", key, info);
    }

    /**
     * @Description 采用DES用户指定密钥的方式进行解密，密钥需要与加密时指定的密钥一样
     * @param key
     * @param info
     * @return
     */
    public static String decryptByDES(String key, String info) throws Exception {
        return decrypt("DES", key, info);
    }

    /**
     * @Description 采用AES随机生成的密钥进行加密
     * @param key
     * @param info
     * @return
     */
    public static String encryptToAES(SecretKey key, String info) {
        return encrypt("AES", key, info);
    }
    
    /**
     * @Description 采用AES指定密钥的方式进行加密
     * @param key
     * @param info
     * @return
     * @throws Exception
     */
    public static String encryptToAES(String key, String info) throws Exception {
        return encrypt("AES", key, info);
    }

	/**
	 * @Description 采用AES随机生成密钥的方式进行解密，密钥需要与加密的生成的密钥一样
	 * @param key
	 * @param info
	 * @return
	 */
    public static String decryptByAES(SecretKey key, String info) {
        return decrypt("AES", key, info);
    }
    
    /**
     * @Description 采用AES用户指定密钥的方式进行解密，密钥需要与加密时指定的密钥一样
     * @param key
     * @param info
     * @return
     */
    public static String decryptByAES(String key, String info) throws Exception {
        return decrypt("AES", key, info);
    }

    /**
     * @Description 十六进制字符串转化为2进制
     * 
     * @param hex
     * @return
     */
    public static byte[] hex2byte(String hex) {
        if (hex == null) {
            return null;
        }
        int l = hex.length();
        if (l % 2 == 1) {
            return null;
        }
        byte[] b = new byte[l / 2];
        for (int i = 0; i != l / 2; i++) {
            b[i] = (byte) Integer.parseInt(hex.substring(i * 2, i * 2 + 2), 16);
        }
        return b;
    }

    /**
     * @Description 将二进制转化为16进制字符串
     * 
     * @param b 二进制字节数组
     * @return String
     */
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }
}
