package com.huazie.frame.common.util;

import com.huazie.frame.common.EncryptionAlgorithmEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p> 加密解密工具类 </p>
 *
 * @author huazie
 * @version v1.0.0
 * @since 1.0.0
 */
public class SecurityUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(SecurityUtils.class);

    /**
     * <p> 进行MD5加密, 单向 </p>
     *
     * @param info 待加密的信息
     * @return 加密后的信息
     * @since 1.0.0
     */
    public static String encryptToMD5(String info) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SecurityUtils#encryptToMD5(String) Info = {}", info);
        }
        byte[] digestInfo = null;
        try {
            // 得到一个md5的消息摘要
            MessageDigest messageDigest = MessageDigest.getInstance(EncryptionAlgorithmEnum.MD5.getAlgorithm());
            // 添加要进行计算摘要的信息
            messageDigest.update(info.getBytes());
            // 得到该摘要
            digestInfo = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("SecurityUtils#encryptToMD5(String) Exception = ", e);
            }
        }
        // 将摘要转为字符串
        String result = DataConvert.byte2hex(digestInfo);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SecurityUtils#encryptToMD5(String) Result = {}", result);
        }
        return result;
    }

    /**
     * <p> 进行SHA加密 </p>
     *
     * @param info 待加密的信息
     * @return 加密后的信息
     * @since 1.0.0
     */
    public static String encryptToSHA(String info) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SecurityUtils#encryptToSHA(String) info = {}", info);
        }
        byte[] digestInfo = null;
        try {
            // 得到一个SHA-1的消息摘要
            MessageDigest messageDigest = MessageDigest.getInstance(EncryptionAlgorithmEnum.SHA_1.getAlgorithm());
            // 添加要进行计算摘要的信息
            messageDigest.update(info.getBytes());
            // 得到该摘要
            digestInfo = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("SecurityUtils#encryptToSHA(String) Exception = ", e);
            }
        }
        // 将摘要转为字符串
        String result = DataConvert.byte2hex(digestInfo);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SecurityUtils#encryptToSHA(String) Result = {}", result);
        }
        return result;
    }

    /**
     * <p> 得到AES加密的密钥 </p>
     *
     * @param src 密钥原始串
     * @return AES加密密钥
     * @since 1.0.0
     */
    public static String getAESKey(String src) {
        return getKey(EncryptionAlgorithmEnum.AES, src);
    }

    /**
     * <p> 得到DES加密的密钥 </p>
     *
     * @param src 密钥原始串
     * @return DES加密密钥
     * @since 1.0.0
     */
    public static String getDESKey(String src) {
        return getKey(EncryptionAlgorithmEnum.DES, src);
    }

    /**
     * <p> 根据一定的算法得到相应的密钥 </p>
     *
     * @param algorithm 加密算法枚举
     * @param src       密钥原始串
     * @return 加密key
     * @since 1.0.0
     */
    private static String getKey(EncryptionAlgorithmEnum algorithm, String src) {

        String key = null;
        if (null != algorithm) {
            String algorithmName = algorithm.getAlgorithm();
            if (EncryptionAlgorithmEnum.AES.getAlgorithm().equals(algorithmName)) {
                key = src.substring(0, 16);
            } else if (EncryptionAlgorithmEnum.DES.getAlgorithm().equals(algorithmName)) {
                key = src.substring(0, 8);
            }
        }
        return key;
    }

    /**
     * <p> 创建一个AES的密钥 </p>
     *
     * @return AES密钥
     * @since 1.0.0
     */
    public static SecretKey createSecretAESKey() {
        return createSecretKey(EncryptionAlgorithmEnum.AES);
    }

    /**
     * <p> 创建一个DES的密钥 </p>
     *
     * @return DES密钥
     * @since 1.0.0
     */
    public static SecretKey createSecretDESKey() {
        return createSecretKey(EncryptionAlgorithmEnum.DES);
    }

    /**
     * <p> 创建密匙 </p>
     *
     * @param algorithm 加密算法,可用 AES, DES
     * @return 密钥
     * @since 1.0.0
     */
    private static SecretKey createSecretKey(EncryptionAlgorithmEnum algorithm) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SecurityUtils#createSecretKey(EncryptionAlgorithmEnum) Algorithm = {}", algorithm.getAlgorithm());
        }
        // KeyGenerator对象
        KeyGenerator keygen;
        // 密钥对象
        SecretKey key = null;
        try {
            // 返回生成指定算法的秘密密钥的 KeyGenerator 对象
            keygen = KeyGenerator.getInstance(algorithm.getAlgorithm());
            // 生成一个密钥
            key = keygen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("SecurityUtils#createSecretKey(EncryptionAlgorithmEnum) Exception = ", e);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SecurityUtils#createSecretKey(EncryptionAlgorithmEnum) SecretKey = {}", DataConvert.byte2hex(key.getEncoded()));
        }
        return key;
    }

    /**
     * <p>根据相应的加密算法、密钥、源信息进行加密，返回加密后的文件</p>
     *
     * @param algorithm 加密算法:DES,AES
     * @param key       密钥
     * @param info      待加密的信息
     * @return 加密后的信息
     * @since 1.0.0
     */
    private static String encrypt(String algorithm, SecretKey key, String info) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SecurityUtils#encrypt(String, SecretKey, String) Algorithm = {}", algorithm);
            LOGGER.debug("SecurityUtils#encrypt(String, SecretKey, String) SecretKey = {}", DataConvert.byte2hex(key.getEncoded()));
            LOGGER.debug("SecurityUtils#encrypt(String, SecretKey, String) Info = {}", info);
        }
        String result = null;
        try {
            // 得到加密/解密器
            Cipher c1 = Cipher.getInstance(algorithm);
            // 用指定的密钥和模式初始化Cipher对象
            // 参数:(ENCRYPT_MODE, DECRYPT_MODE, WRAP_MODE,UNWRAP_MODE)
            c1.init(Cipher.ENCRYPT_MODE, key);
            // 对要加密的内容进行编码处理,
            byte[] cipherByte = c1.doFinal(info.getBytes());
            // 返回密文的十六进制形式
            result = DataConvert.byte2hex(cipherByte);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("SecurityUtils#encrypt(String, SecretKey, String) Exception = ", e);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SecurityUtils#encrypt(String, SecretKey, String) Encrypt Result = {}", result);
        }
        return result;
    }

    /**
     * <p> 根据相应的加密算法、指定的密钥、原始信息进行加密，返回加密后的文件 </p>
     *
     * @param algorithm 加密算法:DES,AES
     * @param key       这个key可以由用户自己指定 注意AES的长度为16位,DES的长度为8位
     * @param info      待加密的信息
     * @return 加密后的信息
     * @since 1.0.0
     */
    private static String encrypt(String algorithm, String key, String info) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SecurityUtils#encrypt(String, String, String) Algorithm = {}", algorithm);
            LOGGER.debug("SecurityUtils#encrypt(String, String, String) Key = {}", key);
            LOGGER.debug("SecurityUtils#encrypt(String, String, String) Info = {}", info);
        }
        String result = null;
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
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(info.getBytes());
            // 返回密文的十六进制形式
            result = DataConvert.byte2hex(encrypted);
        } catch (Exception e) {
            if(LOGGER.isErrorEnabled()){
                LOGGER.error("SecurityUtils#encrypt(String, String, String) Exception = ", e);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SecurityUtils#encrypt(String, String, String) Encrypt Result = {}", result);
        }
        return result;
    }

    /**
     * <p>根据相应的解密算法、密钥和需要解密的文本进行解密，返回解密后的信息</p>
     *
     * @param algorithm 加密算法:DES,AES
     * @param key       密钥
     * @param info      待加密的信息
     * @return 解密后的信息
     * @since 1.0.0
     */
    private static String decrypt(String algorithm, SecretKey key, String info) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SecurityUtils#decrypt(String, SecretKey, String) Algorithm = {}", algorithm);
            LOGGER.debug("SecurityUtils#decrypt(String, SecretKey, String) SecretKey = {}", DataConvert.byte2hex(key.getEncoded()));
            LOGGER.debug("SecurityUtils#decrypt(String, SecretKey, String) Info = {}", info);
        }
        String result = null;
        try {
            // 得到加密/解密器
            Cipher c1 = Cipher.getInstance(algorithm);
            // 用指定的密钥和模式初始化Cipher对象
            c1.init(Cipher.DECRYPT_MODE, key);
            // 对要解密的内容进行编码处理
            byte[] cipherByte = c1.doFinal(DataConvert.hex2byte(info));
            result = new String(cipherByte);
        } catch (Exception e) {
            if(LOGGER.isErrorEnabled()){
                LOGGER.error("SecurityUtils#decrypt(String, SecretKey, String) Exception = ", e);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SecurityUtils#decrypt(String, SecretKey, String) Decrypt Result = {}", result);
        }
        return result;
    }

    /**
     * <p> 根据相应的解密算法、指定的密钥和需要解密的文本进行解密，返回解密后的文本内容 </p>
     *
     * @param algorithm 加密算法:DES,AES
     * @param key       这个key可以由用户自己指定(注意AES的长度为16位,DES的长度为8位)
     * @param info      待解密的信息
     * @return 解密后的信息
     * @since 1.0.0
     */
    private static String decrypt(String algorithm, String key, String info) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SecurityUtils#decrypt(String, String, String) Algorithm = {}", algorithm);
            LOGGER.debug("SecurityUtils#decrypt(String, String, String) Key = {}", key);
            LOGGER.debug("SecurityUtils#decrypt(String, String, String) Info = {}", info);
        }
        String result = null;
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
            byte[] encrypted1 = DataConvert.hex2byte(info);

            byte[] original = cipher.doFinal(encrypted1);
            result = new String(original);

        } catch (Exception e) {
            if(LOGGER.isErrorEnabled()){
                LOGGER.error("SecurityUtils#decrypt(String, String, String) Exception = ", e);
            }
        }
        // 返回密文的十六进制形式
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SecurityUtils#decrypt(String, String, String) Decrypt Result = {}", result);
        }
        return result;
    }

    /**
     * <p> 采用DES随机生成的密钥进行加密 </p>
     *
     * @param key  密钥对象
     * @param info 待加密的信息
     * @return 加密后的信息
     * @since 1.0.0
     */
    public static String encryptToDES(SecretKey key, String info) {
        return encrypt(EncryptionAlgorithmEnum.DES.getAlgorithm(), key, info);
    }

    /**
     * <p> 采用DES指定密钥的方式进行加密 </p>
     *
     * @param key  密钥对象
     * @param info 待加密的信息
     * @return 加密后的信息
     * @since 1.0.0
     */
    public static String encryptToDES(String key, String info) {
        return encrypt(EncryptionAlgorithmEnum.DES.getAlgorithm(), key, info);
    }

    /**
     * <p> 采用DES随机生成密钥的方式进行解密，密钥需要与加密的生成的密钥一样 </p>
     *
     * @param key  密钥对象
     * @param info 待解密密的信息
     * @return 解密后的信息
     * @since 1.0.0
     */
    public static String decryptByDES(SecretKey key, String info) {
        return decrypt(EncryptionAlgorithmEnum.DES.getAlgorithm(), key, info);
    }

    /**
     * <p> 采用DES用户指定密钥的方式进行解密，密钥需要与加密时指定的密钥一样 </p>
     *
     * @param key  密钥
     * @param info 待解密密的信息
     * @return 解密后的信息
     * @since 1.0.0
     */
    public static String decryptByDES(String key, String info) {
        return decrypt(EncryptionAlgorithmEnum.DES.getAlgorithm(), key, info);
    }

    /**
     * <p> 采用AES随机生成的密钥进行加密 </p>
     *
     * @param key  密钥对象
     * @param info 待加密的信息
     * @return 加密后的信息
     * @since 1.0.0
     */
    public static String encryptToAES(SecretKey key, String info) {
        return encrypt(EncryptionAlgorithmEnum.AES.getAlgorithm(), key, info);
    }

    /**
     * <p> 采用AES指定密钥的方式进行加密 </p>
     *
     * @param key  密钥
     * @param info 待加密的信息
     * @return 加密后的信息
     * @since 1.0.0
     */
    public static String encryptToAES(String key, String info) {
        return encrypt(EncryptionAlgorithmEnum.AES.getAlgorithm(), key, info);
    }

    /**
     * <p> 采用AES随机生成密钥的方式进行解密，密钥需要与加密的生成的密钥一样 </p>
     *
     * @param key  密钥对象
     * @param info 待解密密的信息
     * @return 解密后的信息
     * @since 1.0.0
     */
    public static String decryptByAES(SecretKey key, String info) {
        return decrypt(EncryptionAlgorithmEnum.AES.getAlgorithm(), key, info);
    }

    /**
     * <p> 采用AES用户指定密钥的方式进行解密，密钥需要与加密时指定的密钥一样 </p>
     *
     * @param key  密钥
     * @param info 待解密密的信息
     * @return 解密后的信息
     * @since 1.0.0
     */
    public static String decryptByAES(String key, String info) {
        return decrypt(EncryptionAlgorithmEnum.AES.getAlgorithm(), key, info);
    }

}
