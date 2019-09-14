package com.huazie.frame.tools.code.impl;

import com.huazie.frame.common.util.IOUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.tools.code.FleaCodeHelper;
import com.huazie.frame.tools.common.ToolsConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * <p> 持久化单元DAO层实现构建者，所有DAO层实现均继承指定的持久化单元DAO层实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaPersistenceUnitDaoImplBuilder extends FleaCodeBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaPersistenceUnitDaoImplBuilder.class);

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
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("开始编写持久化单元DAO层实现类代码");
        }

        // 持久化单元DAO层实现类代码文件路径
        String fleaPersistenceUnitDAOImplFilePathStr = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.CODE_FILE_PATH));
        // 持久化单元别名 （每个英文单词首字母大写）
        String fleaPUAliasName = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_ALIAS_NAME));
        // 持久化单元名
        String fleaPUName = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_NAME));
        // 持久化单元DAO层实现类名
        String fleaPersistenceUnitDaoClassName = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_DAO_CLASS_NAME));
        if (StringUtils.isBlank(fleaPUName) || StringUtils.isBlank(fleaPUAliasName)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("采用现有的持久化单元DAO层实现类 = {}", fleaPersistenceUnitDaoClassName);
                LOGGER.debug("现有持久化单元DAO层实现类代码文件路径 = {}", fleaPersistenceUnitDAOImplFilePathStr);
            }
            return;
        }

        // 获取DAO层实现类配置模板文件内容
        String content = IOUtils.toNativeStringFromResource("flea/code/dao/FleaPersistenceUnitDAOImpl.code");
        // 新建DAO层实现类java文件
        IOUtils.toFileFromNativeString(FleaCodeHelper.convert(content, param), fleaPersistenceUnitDAOImplFilePathStr);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("持久化单元DAO层实现类 = {}", fleaPersistenceUnitDaoClassName);
            LOGGER.debug("持久化单元DAO层实现类代码文件路径 = {}", fleaPersistenceUnitDAOImplFilePathStr);
            LOGGER.debug("结束编写持久化单元DAO层实现类代码");
        }
    }

    @Override
    public void destroy(Map<String, Object> param) {
        // 持久化单元名
        String fleaPersistenceUnitName = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_NAME));
        // 持久化单元别名 （每个英文单词首字母大写）
        String fleaPersistenceUnitAliasName = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_ALIAS_NAME));
        // 持久化单元DAO层实现类名
        String fleaPersistenceUnitDaoClassName = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_DAO_CLASS_NAME));
        if (StringUtils.isBlank(fleaPersistenceUnitName) || StringUtils.isBlank(fleaPersistenceUnitAliasName)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("不需要销毁，采用现有的持久化单元DAO层实现类 = {}", fleaPersistenceUnitDaoClassName);
            }
            return;
        } else {
            param.put(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_DAO_CLASS_NAME, fleaPersistenceUnitAliasName + "DAOImpl");
        }
        super.destroy(param);
    }
}
