package com.huazie.frame.common.util;

import com.huazie.frame.common.util.EncryptUtils;
import org.junit.Test;

import javax.crypto.SecretKey;

public class EncryptUtilsTest {

    @Test
    public void testEncrypt(){
        String source = "123asd";
        System.out.println("123asd经过MD5:" + EncryptUtils.encryptToMD5(source));
        System.out.println("123asd经过SHA:" + EncryptUtils.encryptToSHA(source));
        System.out.println("========随机生成Key进行加解密==============");
        // 生成一个DES算法的密匙
        SecretKey key = EncryptUtils.createSecretDESKey();
        String str1 = EncryptUtils.encryptToDES(key, source);
        System.out.println("DES加密后为:" + str1);
        // 使用这个密匙解密
        String str2 = EncryptUtils.decryptByDES(key, str1);
        System.out.println("DES解密后为：" + str2);

//         生成一个AES算法的密匙
        SecretKey key1 = EncryptUtils.createSecretAESKey();
        String stra = EncryptUtils.encryptToAES(key1, source);
        System.out.println("AES加密后为:" + stra);
//         使用这个密匙解密
        String strb = EncryptUtils.decryptByAES(key1, stra);
        System.out.println("AES解密后为：" + strb);
        System.out.println("========指定Key进行加解密==============");
        try {
            String AESKey = EncryptUtils.getAESKey(EncryptUtils.encryptToSHA(source));
            String DESKey = EncryptUtils.getDESKey(EncryptUtils.encryptToSHA(source));
            System.out.println(AESKey);
            System.out.println(DESKey);
            String str11 = EncryptUtils.encryptToDES(DESKey, source);
            System.out.println("DES加密后为:" + str11);
            // 使用这个密匙解密
            String str12 = EncryptUtils.decryptByDES(DESKey, str11);
            System.out.println("DES解密后为：" + str12);

            // 生成一个AES算法的密匙
            String strc = EncryptUtils.encryptToAES(AESKey, source);
            System.out.println("AES加密后为:" + strc);
            // 使用这个密匙解密
            String strd = EncryptUtils.decryptByAES(AESKey, strc);
            System.out.println("AES解密后为：" + strd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
