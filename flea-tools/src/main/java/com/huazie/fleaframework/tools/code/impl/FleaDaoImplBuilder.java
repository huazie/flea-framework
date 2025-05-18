package com.huazie.fleaframework.tools.code.impl;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.IOUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.tools.code.FleaCodeHelper;
import com.huazie.fleaframework.tools.common.ToolsConstants;

import java.util.Map;

/**
 * <p> Flea DAO层实现类代码建造者实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaDaoImplBuilder extends FleaCodeBuilder {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaDaoImplBuilder.class);

    @Override
    protected void combinedFilePath(StringBuilder fleaFilePathStrBuilder, String entityClassName, String separator, Map<String, Object> param) {
        fleaFilePathStrBuilder.append("dao").append(separator).append("impl").append(separator)
                .append(entityClassName).append("DAOImpl").append(".java");
    }

    @Override
    protected void code(Map<String, Object> param) {
        LOGGER.debug("开始编写DAO层实现类代码");

        // DAO层实现类代码文件路径
        String fleaDAOImplFilePathStr = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.CODE_FILE_PATH));
        // 实体类名
        String entityClassName = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.ENTITY_CLASS_NAME));

        // 获取DAO层实现类配置模板文件内容
        String content = IOUtils.toNativeStringFromResource("flea/code/dao/FleaDAOImpl.code");
        // 新建DAO层实现类java文件
        IOUtils.toFileFromNativeString(FleaCodeHelper.convert(content, param), fleaDAOImplFilePathStr);

        LOGGER.debug("DAO层实现类 = {}", entityClassName + "DAOImpl");
        LOGGER.debug("DAO层实现类代码文件路径 = {}", fleaDAOImplFilePathStr);
        LOGGER.debug("结束编写DAO层实现类代码");
    }
}