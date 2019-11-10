package com.huazie.frame.common.util;

import org.junit.Test;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

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
    public void testCustomKeyAES(){

        String source = "123asd";
        String DESKey = SecurityUtils.getDESKey(SecurityUtils.encryptToSHA(source));
        String str11 = SecurityUtils.encryptToDES(DESKey, source);
        SecurityUtils.decryptByDES(DESKey, str11);
    }

    @Test
    public void testCustomKeyDES(){

        String source = "123asd";
        String AESKey = SecurityUtils.getAESKey(SecurityUtils.encryptToSHA(source));
        String strc = SecurityUtils.encryptToAES(AESKey, source);
        SecurityUtils.decryptByAES(AESKey, strc);
    }

    @Test
    public void testOther(){
        String hexKey = "1A2025C4044F4508";
        String result = "AC235D321317B49E";
        byte[] bytes = DataHandleUtils.hex2byte(hexKey);
        SecretKeySpec skeySpec = new SecretKeySpec(bytes, "DES");
        SecurityUtils.decryptByDES(skeySpec, result);
    }
}
