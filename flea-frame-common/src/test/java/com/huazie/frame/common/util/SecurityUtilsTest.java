package com.huazie.frame.common.util;

import org.junit.Test;

import javax.crypto.SecretKey;

public class SecurityUtilsTest {

    @Test
    public void testMD5(){
        String source = "123asd";
        SecurityUtils.encryptToMD5(source);
    }

    @Test
    public void testSHA1(){
        String source = "123asd";
        SecurityUtils.encryptToSHA(source);
    }

    @Test
    public void testAES(){
        String source = "123asd";
        // 生成一个AES算法的密匙
        SecretKey key1 = SecurityUtils.createSecretAESKey();
        String result = SecurityUtils.encryptToAES(key1, source);
        // 使用这个密匙解密
        SecurityUtils.decryptByAES(key1, result);
    }

    @Test
    public void testDES(){
        String source = "123asd";
        // 生成一个DES算法的密匙
        SecretKey key = SecurityUtils.createSecretDESKey();
        String result = SecurityUtils.encryptToDES(key, source);
        // 使用这个密匙解密
        SecurityUtils.decryptByDES(key, result);
    }

    @Test
    public void testEncrypt(){

        String source = "123asd";

        System.out.println("========指定Key进行加解密==============");
        try {
            String AESKey = SecurityUtils.getAESKey(SecurityUtils.encryptToSHA(source));
            String DESKey = SecurityUtils.getDESKey(SecurityUtils.encryptToSHA(source));
            System.out.println(AESKey);
            System.out.println(DESKey);
            String str11 = SecurityUtils.encryptToDES(DESKey, source);
            System.out.println("DES加密后为:" + str11);
            // 使用这个密匙解密
            String str12 = SecurityUtils.decryptByDES(DESKey, str11);
            System.out.println("DES解密后为：" + str12);

            // 生成一个AES算法的密匙
            String strc = SecurityUtils.encryptToAES(AESKey, source);
            System.out.println("AES加密后为:" + strc);
            // 使用这个密匙解密
            String strd = SecurityUtils.decryptByAES(AESKey, strc);
            System.out.println("AES解密后为：" + strd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
