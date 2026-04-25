package com.huazie.fleaframework.common.util;

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

    // ==================== BCrypt 密码加密测试 ====================

    @Test
    public void testBCryptEncrypt() {
        String password = "123456";
        String encrypted = SecurityUtils.encryptToBCrypt(password);
        // BCrypt 加密结果应以 $2a$ 开头
        assert encrypted != null && encrypted.startsWith("$2a$");
    }

    @Test
    public void testBCryptMatches() {
        String password = "123456";
        String encrypted = SecurityUtils.encryptToBCrypt(password);
        // 验证密码匹配
        assert SecurityUtils.matchesPassword(password, encrypted);
    }

    @Test
    public void testBCryptMatchesWithWrongPassword() {
        String password = "123456";
        String wrongPassword = "654321";
        String encrypted = SecurityUtils.encryptToBCrypt(password);
        // 验证错误密码不匹配
        assert !SecurityUtils.matchesPassword(wrongPassword, encrypted);
    }

    @Test
    public void testBCryptEmptyPassword() {
        // 空密码加密应返回原值
        String encrypted = SecurityUtils.encryptToBCrypt("");
        assert "".equals(encrypted);

        // null 密码加密应返回原值
        String encryptedNull = SecurityUtils.encryptToBCrypt(null);
        assert encryptedNull == null;
    }

    @Test
    public void testBCryptNullMatches() {
        // 空值或 null 应返回 false
        assert !SecurityUtils.matchesPassword(null, "encrypted");
        assert !SecurityUtils.matchesPassword("password", null);
        assert !SecurityUtils.matchesPassword("", "");
    }

    @Test
    public void testIsBCryptEncoded() {
        String password = "123456";
        String bcrypted = SecurityUtils.encryptToBCrypt(password);
        String sha = SecurityUtils.encryptToSHA(password);

        // BCrypt 加密的结果应被识别
        assert SecurityUtils.isBCryptEncoded(bcrypted);
        // SHA 加密的结果不应被识别为 BCrypt
        assert !SecurityUtils.isBCryptEncoded(sha);
        // 空值不应被识别
        assert !SecurityUtils.isBCryptEncoded(null);
        assert !SecurityUtils.isBCryptEncoded("");
    }

    @Test
    public void testSHACompatibility() {
        String password = "123456";
        // 使用 SHA 加密（模拟旧数据）
        String shaEncrypted = SecurityUtils.encryptToSHA(password);

        // 应能通过 matchesPassword 验证（兼容旧数据）
        assert SecurityUtils.matchesPassword(password, shaEncrypted);
    }

    @Test
    public void testUpgradeToBCrypt() {
        String password = "123456";
        String bcrypted = SecurityUtils.upgradeToBCrypt(password);

        // 升级后的密码应能被识别为 BCrypt
        assert SecurityUtils.isBCryptEncoded(bcrypted);
        // 升级后的密码应能验证通过
        assert SecurityUtils.matchesPassword(password, bcrypted);
    }

    @Test
    public void testBCryptDifferentHashesForSamePassword() {
        String password = "123456";
        String encrypted1 = SecurityUtils.encryptToBCrypt(password);
        String encrypted2 = SecurityUtils.encryptToBCrypt(password);

        // 同一密码两次加密结果应不同（因为盐不同）
        assert !encrypted1.equals(encrypted2);

        // 但两者都应能验证通过
        assert SecurityUtils.matchesPassword(password, encrypted1);
        assert SecurityUtils.matchesPassword(password, encrypted2);
    }
}
