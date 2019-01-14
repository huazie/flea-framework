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
	 * <p>
	 * 进行MD5加密
	 * 
	 * @version v1.0.0
	 * @date 2016年9月29日
	 * 
	 * @param info
	 *            要加密的信息
	 * @return String 加密后的字符串
	 */
    public String encryptToMD5(String info) {
        byte[] digestInfo = null;
        try {
            // 得到一个md5的消息摘要
            MessageDigest messageDigest = MessageDigest.getInstance(EncryptUtils.MD5);
            // 添加要进行计算摘要的信息
            messageDigest.update(info.getBytes());
            // 得到该摘要
            digestInfo = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
        	EncryptUtils.LOGGER.error("EncryptUtils##encryptToMD5(info)=", e);
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
    public String encryptToSHA(String info) {
        byte[] digestInfo = null;
        try {
            // 得到一个SHA-1的消息摘要
            MessageDigest alga = MessageDigest.getInstance(EncryptUtils.SHA_1);
            // 添加要进行计算摘要的信息
            alga.update(info.getBytes());
            // 得到该摘要
            digestInfo = alga.digest();
        } catch (NoSuchAlgorithmException e) {
        	EncryptUtils.LOGGER.error("EncryptUtils##encryptToSHA(info)=", e);
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
    public String getAESKey(String src){
        return this.getKey(EncryptUtils.AES, src);
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
    public String getDESKey(String src){
        return this.getKey(EncryptUtils.DES, src);
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
    private String getKey(String algorithm, String src){
        if(algorithm.equals(EncryptUtils.AES)){
            return src.substring(0, 16);
        }else if(algorithm.equals(EncryptUtils.DES)){
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
    public SecretKey createSecretAESKey() {
        return createSecretKey(EncryptUtils.AES);
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
    public SecretKey createSecretDESKey() {
        return createSecretKey(EncryptUtils.DES);
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
    public SecretKey createSecretKey(String algorithm) {
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
        	EncryptUtils.LOGGER.error("EncryptUtils##createSecretKey(algorithm)=", e);
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
    public String encrypt(String algorithm, SecretKey key, String info) {
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
        	EncryptUtils.LOGGER.error("EncryptUtils##encrypt(algorithm,key,info)=", e);
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
    public String decrypt(String algorithm, SecretKey key, String info) {
        byte[] cipherByte = null;
        try {
            // 得到加密/解密器
            Cipher c1 = Cipher.getInstance(algorithm);
            // 用指定的密钥和模式初始化Cipher对象
            c1.init(Cipher.DECRYPT_MODE, key);
            // 对要解密的内容进行编码处理
            cipherByte = c1.doFinal(hex2byte(info));
        } catch (Exception e) {
        	EncryptUtils.LOGGER.error("EncryptUtils##decrypt(algorithm,key,info)=", e);
        }
        return new String(cipherByte);
    }

    /**
     * @Description 根据相应的解密算法、指定的密钥和需要解密的文本进行解密，返回解密后的文本内容
     * @param algorithm 加密算法:DES,AES
     * @param key 这个key可以由用户自己指定 注意AES的长度为16位,DES的长度为8位
     * @param sInfo
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
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original);
                return originalString;
            } catch (Exception e) {
                throw e;
            }
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
    public String encryptToDES(SecretKey key, String info) {
        return encrypt("DES", key, info);
    }

    /**
     * @Description 采用DES指定密钥的方式进行加密
     * @param key
     * @param info
     * @return
     * @throws Exception
     */
    public String encryptToDES(String key, String info) throws Exception {
        return encrypt("DES", key, info);
    }

    /**
     * @Description 采用DES随机生成密钥的方式进行解密，密钥需要与加密的生成的密钥一样
     * @param key
     * @param info
     * @return
     */
    public String decryptByDES(SecretKey key, String info) {
        return decrypt("DES", key, info);
    }

    /**
     * @Description 采用DES用户指定密钥的方式进行解密，密钥需要与加密时指定的密钥一样
     * @param key
     * @param info
     * @return
     */
    public String decryptByDES(String key, String info) throws Exception {
        return decrypt("DES", key, info);
    }

    /**
     * @Description 采用AES随机生成的密钥进行加密
     * @param key
     * @param info
     * @return
     */
    public String encryptToAES(SecretKey key, String info) {
        return encrypt("AES", key, info);
    }
    
    /**
     * @Description 采用AES指定密钥的方式进行加密
     * @param key
     * @param info
     * @return
     * @throws Exception
     */
    public String encryptToAES(String key, String info) throws Exception {
        return encrypt("AES", key, info);
    }

	/**
	 * @Description 采用AES随机生成密钥的方式进行解密，密钥需要与加密的生成的密钥一样
	 * @param key
	 * @param info
	 * @return
	 */
    public String decryptByAES(SecretKey key, String info) {
        return decrypt("AES", key, info);
    }
    
    /**
     * @Description 采用AES用户指定密钥的方式进行解密，密钥需要与加密时指定的密钥一样
     * @param key
     * @param info
     * @return
     */
    public String decryptByAES(String key, String info) throws Exception {
        return decrypt("AES", key, info);
    }

    /**
     * @Description 十六进制字符串转化为2进制
     * 
     * @param hex
     * @return
     */
    public static byte[] hex2byte(String strhex) {
        if (strhex == null) {
            return null;
        }
        int l = strhex.length();
        if (l % 2 == 1) {
            return null;
        }
        byte[] b = new byte[l / 2];
        for (int i = 0; i != l / 2; i++) {
            b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2), 16);
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

    /**
     * 测试
     * 
     * @param args
     */
    public static void main(String[] args) {
        EncryptUtils encryptUtils = new EncryptUtils();
        String source = "123asd";
        System.out.println("123asd经过MD5:" + encryptUtils.encryptToMD5(source));
        System.out.println("123asd经过SHA:" + encryptUtils.encryptToSHA(source));
        System.out.println("========随机生成Key进行加解密==============");
        // 生成一个DES算法的密匙
        SecretKey key = encryptUtils.createSecretDESKey();
        String str1 = encryptUtils.encryptToDES(key, source);
        System.out.println("DES加密后为:" + str1);
        // 使用这个密匙解密
        String str2 = encryptUtils.decryptByDES(key, str1);
        System.out.println("DES解密后为：" + str2);

//         生成一个AES算法的密匙
        SecretKey key1 = encryptUtils.createSecretAESKey();
        String stra = encryptUtils.encryptToAES(key1, source);
        System.out.println("AES加密后为:" + stra);
//         使用这个密匙解密
        String strb = encryptUtils.decryptByAES(key1, stra);
        System.out.println("AES解密后为：" + strb);
        System.out.println("========指定Key进行加解密==============");
        try {
            String AESKey = encryptUtils.getAESKey(encryptUtils.encryptToSHA(source));
            String DESKey = encryptUtils.getDESKey(encryptUtils.encryptToSHA(source));
            System.out.println(AESKey);
            System.out.println(DESKey);
            String str11 = encryptUtils.encryptToDES(DESKey, source);
            System.out.println("DES加密后为:" + str11);
            // 使用这个密匙解密
            String str12 = encryptUtils.decryptByDES(DESKey, str11);
            System.out.println("DES解密后为：" + str12);

            // 生成一个AES算法的密匙
            String strc = encryptUtils.encryptToAES(AESKey, source);
            System.out.println("AES加密后为:" + strc);
            // 使用这个密匙解密
            String strd = encryptUtils.decryptByAES(AESKey, strc);
            System.out.println("AES解密后为：" + strd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
