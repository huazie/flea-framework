package com.huazie.frame.common.util;

import java.util.Random;
import java.util.UUID;

/**
 * <p> 随机码生成公共类 </p>
 *
 * @author huazie
 * @version v1.0.0
 * @since 1.0.0
 */
public class RandomCode {

    // 创建随机数对象
    private static Random random = new Random();

    /**
     * <p> 产生随机的数字，位数由len控制,以字符串形式返回 </p>
     *
     * @param len 随机数字长度
     * @return 指定长度的随机数字
     * @since 1.0.0
     */
    public static String toNumberCode(int len) {
        // 随机产生认证码(len位数字)
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    /**
     * <p> 产生随机的字母，位数由len控制。以连续的字符串形式返回 </p>
     *
     * @param len 随机字母长度
     * @return 制定长度的字母字符串
     * @since 1.0.0
     */
    public static String toLetterCode(int len) {
        String sNum = "";
        for (int i = 0; i < len; i++) {

        }

        return sNum;
    }

    /**
     * <p> 生成UUID值 </p>
     *
     * @return 去除"-"的UUID值
     * @since 1.0.0
     */
    public static String toUUID() {
        String s = UUID.randomUUID().toString();
        // 去掉"-"符号
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }
}
