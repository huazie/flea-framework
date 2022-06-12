package com.huazie.fleaframework.common;

/**
 * 加密算法枚举
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public enum EncryptionAlgorithmEnum {

    AES("AES", "高级加密标准(Advanced Encryption Standard)"),

    DES("DES", "数据加密标准(Data Encryption Standard)"),

    MD5("MD5", "MD5消息摘要算法(MD5 Message-Digest Algorithm), 一种被广泛使用的密码散列函数，可以产生出一个128位（16字节）的散列值（hash value），用于确保信息传输完整一致"),

    SHA_1("SHA-1", "安全散列算法1(Secure Hash Algorithm 1), 一种密码散列函数, 可以生成一个被称为消息摘要的160位（20字节）散列值，散列值通常的呈现形式为40个十六进制数");

    private String algorithm; // 加密算法

    private String desc;      // 描述信息

    EncryptionAlgorithmEnum(String algorithm, String desc) {
        this.algorithm = algorithm;
        this.desc = desc;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getDesc() {
        return desc;
    }
}
