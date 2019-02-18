package com.huazie.frame.common.util;

import com.huazie.frame.common.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private final static Logger LOGGER = LoggerFactory.getLogger(RandomCode.class);

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
        StringBuilder sNumberCode = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sNumberCode.append(random.nextInt(10));
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("RandomCode##toNumberCode(int) NumberCode = {}", sNumberCode.toString());
        }
        return sNumberCode.toString();
    }

    /**
     * <p> 产生随机的字母，位数由len控制。以连续的字符串形式返回 </p>
     *
     * @param len 随机字母长度
     * @return 制定长度的字母字符串
     * @since 1.0.0
     */
    public static String toLetterCode(int len) {
        StringBuilder sLetterCode = new StringBuilder();
        for (int i = 0; i < len; i++) {

            sLetterCode.append(random.nextInt(10) );
        }
        return sLetterCode.toString();
    }

    /**
     * <p> 生成UUID值 </p>
     *
     * @return 去除"-"的UUID值
     * @since 1.0.0
     */
    public static String toUUID() {
        String sUUID = UUID.randomUUID().toString();
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("RandomCode##toUUID() Before UUID = {}", sUUID);
        }
        // 去掉"-"符号
        String sResult = sUUID.replaceAll(CommonConstants.SymbolConstants.HYPHEN, "");
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("RandomCode##toUUID() After UUID = {}", sResult);
        }
        return sResult;
    }
}
