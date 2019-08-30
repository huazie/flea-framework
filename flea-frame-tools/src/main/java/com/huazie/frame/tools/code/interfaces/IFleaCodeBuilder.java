package com.huazie.frame.tools.code.interfaces;

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
     * @param param
     * @throws Exception
     */
    void build(Map<String, Object> param) throws Exception;

}
