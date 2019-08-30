package com.huazie.frame.tools.code.impl;

import com.huazie.frame.common.util.IOUtils;
import com.huazie.frame.tools.code.interfaces.IFleaCodeBuilder;

import java.util.Map;

/**
 * <p> Flea DAO层接口代码建造者实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaDaoInterfacesBuilder implements IFleaCodeBuilder {

    @Override
    public void build(Map<String, Object> param) throws Exception {

        // 获取DAO层接口配置模板文件内容
        String content = IOUtils.toNatvieStringFromResource("flea/code/dao/IFleaDAO.code");


    }

}