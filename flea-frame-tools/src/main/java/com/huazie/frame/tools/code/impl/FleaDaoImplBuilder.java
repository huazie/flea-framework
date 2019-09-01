package com.huazie.frame.tools.code.impl;

import com.huazie.frame.common.util.IOUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.tools.code.FleaCodeHelper;
import com.huazie.frame.tools.common.ToolsConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * <p> Flea DAO层实现类代码建造者实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaDaoImplBuilder extends FleaCodeBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaDaoImplBuilder.class);

    @Override
    protected void combinedFilePath(StringBuilder fleaFilePathStrBuilder, String entityClassName, String separator) {
        fleaFilePathStrBuilder.append("dao").append(separator).append("impl").append(separator)
                .append(entityClassName).append("DAOImpl").append(".java");
    }

    @Override
    protected void code(Map<String, Object> param) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("开始编写DAO层实现类代码");
        }

        // DAO层实现类代码文件路径
        String fleaDAOImplFilePathStr = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.CODE_FILE_PATH));
        // 实体类名
        String entityClassName = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.ENTITY_CLASS_NAME));

        // 持久化单元DAO层公共类全名
        String fleaPersistenceUnitDaoClassPackage = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_DAO_CLASS_PACKAGE));
        // 获取持久化单元DAO层公共类名
        String fleaPersistenceUnitDaoClassName = fleaPersistenceUnitDaoClassPackage.substring(fleaPersistenceUnitDaoClassPackage.lastIndexOf(".") + 1);
        param.put(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_DAO_CLASS_NAME, fleaPersistenceUnitDaoClassName);

        // 获取DAO层实现类配置模板文件内容
        String content = IOUtils.toNativeStringFromResource("flea/code/dao/FleaDAOImpl.code");
        // 新建DAO层实现类java文件
        IOUtils.toFileFromNativeString(FleaCodeHelper.convert(content, param), fleaDAOImplFilePathStr);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("DAO层实现类 = {}", entityClassName);
            LOGGER.debug("DAO层实现类代码文件路径 = {}", fleaDAOImplFilePathStr);
            LOGGER.debug("结束编写DAO层实现类代码");
        }
    }
}