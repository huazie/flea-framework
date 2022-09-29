package com.huazie.fleaframework.tools.code.impl;

import com.huazie.fleaframework.common.util.IOUtils;

/**
 * Flea实体类Lombok版代码建造者实现
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaEntityWithLombokBuilder extends AbstractFleaEntityBuilder {

    @Override
    protected String toEntityCodeFileContent() {
        return IOUtils.toNativeStringFromResource("flea/code/entity/FleaEntityWithLombok.code");
    }
}
