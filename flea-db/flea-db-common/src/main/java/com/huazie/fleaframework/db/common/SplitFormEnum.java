package com.huazie.fleaframework.db.common;

/**
 * 分库分表转换序列形态枚举，定义转换后的序列键呈现形态
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public enum SplitFormEnum {

    /** 数字形态：返回十进制数字串（从 1 开始） */
    NUMBER,

    /** 小写字母形态：从小写字母 a 开始 */
    LOWERCASE,

    /** 大写字母形态：从大写字母 A 开始 */
    UPPERCASE
}
