package com.huazie.fleaframework.tools.code.impl;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.IOUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.tools.code.FleaCodeHelper;
import com.huazie.fleaframework.tools.common.ToolsConstants;

import java.util.Map;

/**
 * <p> 持久化单元DAO层实现构建者，所有DAO层实现均继承指定的持久化单元DAO层实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class AbstractPersistenceUnitDaoImplBuilder extends FleaCodeBuilder {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(AbstractPersistenceUnitDaoImplBuilder.class);

    @Override
    protected void combinedFilePath(StringBuilder fleaFilePathStrBuilder, String entityClassName, String separator, Map<String, Object> param) {
        // 根目录
        String rootPackage = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.ROOT_PACKAGE));
        // 持久化单元DAO层实现类所在包目录名
        String fleaPersistenceUnitDaoClassPackage = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_DAO_CLASS_PACKAGE));
        // 持久化单元DAO层实现类名
        String fleaPersistenceUnitDaoClassName = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_DAO_CLASS_NAME));
        StringUtils.clear(fleaFilePathStrBuilder);
        fleaFilePathStrBuilder.append(rootPackage).append(separator).append(fleaPersistenceUnitDaoClassPackage.replaceAll("\\.", "\\" + separator)).append(separator).append(fleaPersistenceUnitDaoClassName).append(".java");

    }

    @Override
    protected void code(Map<String, Object> param) {
        LOGGER.debug("开始编写持久化单元DAO层实现类代码");

        // 持久化单元DAO层实现类代码文件路径
        String fleaPersistenceUnitDAOImplFilePathStr = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.CODE_FILE_PATH));
        // 持久化单元别名 （每个英文单词首字母大写）
        String fleaPUAliasName = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_ALIAS_NAME));
        // 持久化单元名
        String fleaPUName = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_NAME));
        // 持久化单元DAO层实现类名
        String fleaPersistenceUnitDaoClassName = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_DAO_CLASS_NAME));
        if (StringUtils.isBlank(fleaPUName) || StringUtils.isBlank(fleaPUAliasName)) {
            LOGGER.debug("采用现有的持久化单元DAO层实现类 = {}", fleaPersistenceUnitDaoClassName);
            LOGGER.debug("现有持久化单元DAO层实现类代码文件路径 = {}", fleaPersistenceUnitDAOImplFilePathStr);
            return;
        }

        // 获取DAO层实现类配置模板文件内容
        String content = toEntityCodeFileContent();
        // 新建DAO层实现类java文件
        IOUtils.toFileFromNativeString(FleaCodeHelper.convert(content, param), fleaPersistenceUnitDAOImplFilePathStr);
        LOGGER.debug("持久化单元DAO层实现类 = {}", fleaPersistenceUnitDaoClassName);
        LOGGER.debug("持久化单元DAO层实现类代码文件路径 = {}", fleaPersistenceUnitDAOImplFilePathStr);
        LOGGER.debug("结束编写持久化单元DAO层实现类代码");
    }

    /**
     * 获取实体代码文件配置内容
     *
     * @return 实体代码文件配置内容
     * @since 2.0.0
     */
    protected abstract String toEntityCodeFileContent();

    @Override
    public void destroy(Map<String, Object> param) {
        // 持久化单元名
        String fleaPersistenceUnitName = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_NAME));
        // 持久化单元别名 （每个英文单词首字母大写）
        String fleaPersistenceUnitAliasName = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_ALIAS_NAME));
        // 持久化单元DAO层实现类名
        String fleaPersistenceUnitDaoClassName = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_DAO_CLASS_NAME));
        if (StringUtils.isBlank(fleaPersistenceUnitName) || StringUtils.isBlank(fleaPersistenceUnitAliasName)) {
            LOGGER.debug("不需要销毁，采用现有的持久化单元DAO层实现类 = {}", fleaPersistenceUnitDaoClassName);
            return;
        } else {
            param.put(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_DAO_CLASS_NAME, fleaPersistenceUnitAliasName + "DAOImpl");
        }
        super.destroy(param);
    }
}
