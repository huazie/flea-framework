package com.huazie.fleaframework.tools.code.impl;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.IOUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.tools.code.FleaCodeHelper;
import com.huazie.fleaframework.tools.common.ToolsConstants;

import java.util.Map;

/**
 * <p> Flea SV层接口代码建造者实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaSVInterfacesBuilder extends FleaCodeBuilder {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaSVInterfacesBuilder.class);

    @Override
    protected void combinedFilePath(StringBuilder fleaFilePathStrBuilder, String entityClassName, String separator, Map<String, Object> param) {
        fleaFilePathStrBuilder.append("service").append(separator).append("interfaces").append(separator)
                .append("I").append(entityClassName).append("SV").append(".java");
    }

    @Override
    protected void code(Map<String, Object> param) {
        LOGGER.debug("开始编写SV层接口类代码");

        // SV层接口类代码文件路径
        String iFleaSVFilePathStr = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.CODE_FILE_PATH));
        // 实体类名
        String entityClassName = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.ENTITY_CLASS_NAME));

        // 获取SV层接口类配置模板文件内容
        String content = IOUtils.toNativeStringFromResource("flea/code/service/IFleaSV.code");
        // 新建SV层接口类java文件
        IOUtils.toFileFromNativeString(FleaCodeHelper.convert(content, param), iFleaSVFilePathStr);
        LOGGER.debug("SV层接口类 = {}", entityClassName);
        LOGGER.debug("SV层接口类代码文件路径 = {}", iFleaSVFilePathStr);
        LOGGER.debug("结束编写SV层接口类代码");
    }
}