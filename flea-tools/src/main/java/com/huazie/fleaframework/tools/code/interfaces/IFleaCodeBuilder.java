package com.huazie.fleaframework.tools.code.interfaces;

import java.util.Map;

/**
 * <p> Flea代码建造者接口类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaCodeBuilder {

    /**
     * <p> 生成Flea相关代码java类 </p>
     *
     * @param param 代码模板参数
     * @since 1.0.0
     */
    void build(Map<String, Object> param);

    /**
     * <p> 销毁Flea相关代码java类文件 </p>
     *
     * @param param 代码模板参数
     * @since 1.0.0
     */
    void destroy(Map<String, Object> param);

}
