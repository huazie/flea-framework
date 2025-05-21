package com.huazie.fleaframework.tools.code.impl;

import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.ArrayUtils;
import com.huazie.fleaframework.common.util.MapUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.tools.code.interfaces.IFleaCodeBuilder;
import com.huazie.fleaframework.tools.common.ToolsConstants;

import java.io.File;
import java.util.Map;

/**
 * <p> Flea代码建造者 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class FleaCodeBuilder implements IFleaCodeBuilder {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaCodeBuilder.class);

    @Override
    public void build(Map<String, Object> param) {
        if (MapUtils.isEmpty(param)) {
            return;
        }

        // 获取持久化单元名
        String fleaPersistenceUnitName = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_NAME));
        String fleaPersistenceUnitAliasName = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_ALIAS_NAME));
        if (StringUtils.isNotBlank(fleaPersistenceUnitName) && StringUtils.isNotBlank(fleaPersistenceUnitAliasName)) {
            param.put(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_DAO_CLASS_NAME, fleaPersistenceUnitAliasName + "DAOImpl");
            param.put(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_ALIAS_NAME_1, StringUtils.toLowerCaseInitial(fleaPersistenceUnitAliasName));
        }

        // 实体类名
        String entityClassName = toEntityClassName(param);
        param.put(ToolsConstants.CodeConstants.ENTITY_CLASS_NAME, entityClassName);
        param.put(ToolsConstants.CodeConstants.ENTITY_CLASS_NAME_1, StringUtils.toLowerCaseInitial(entityClassName));
        param.put(ToolsConstants.CodeConstants.CODE_FILE_PATH, toFleaFilePath(param));

        // 子类实现代码编写
        code(param);
    }

    @Override
    public void destroy(Map<String, Object> param) {
        param.put(ToolsConstants.CodeConstants.ENTITY_CLASS_NAME, toEntityClassName(param));
        String filePath = toFleaFilePath(param);
        File fleaCodeFile = new File(filePath);
        if (fleaCodeFile.exists()) {
            fleaCodeFile.delete();
            LOGGER.debug1(new Object() {}, "删除代码文件：{}", filePath);
        }
    }

    /**
     * <p> 获取实体类名 </p>
     *
     * @param param 参数信息
     * @return 实体类名
     * @since 1.0.0
     */
    private String toEntityClassName(Map<String, Object> param) {
        // 表名
        String tableName = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.TABLE_NAME));

        // 通过表名，转换成实体类名，转换规则，去掉表名下划线，单词首字母大写，其余小写
        String[] tableNameArr = StringUtils.split(tableName, CommonConstants.SymbolConstants.UNDERLINE);
        if (ArrayUtils.isNotEmpty(tableNameArr)) {
            for (int n = 0; n < tableNameArr.length; n++) {
                tableNameArr[n] = StringUtils.toUpperCaseFirstAndLowerCaseLeft(tableNameArr[n]);
            }
            // 实体类名
            return StringUtils.strCat("", tableNameArr);
        } else {
            return "";
        }
    }

    /**
     * <p> 获取Flea代码文件生成路径 </p>
     *
     * @param param 代码模板参数
     * @return Flea代码文件生成路径
     * @since 1.0.0
     */
    private String toFleaFilePath(Map<String, Object> param) {
        // 根目录
        String rootPackage = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.ROOT_PACKAGE));
        // 包目录
        String codePackage = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.CODE_PACKAGE));
        // 实体名
        String entityClassName = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.ENTITY_CLASS_NAME));
        // 获取系统路径连接符
        String separator = File.separator;
        // 获取DAO层接口代码生成路径全名
        StringBuilder fleaFilePathStrBuilder = new StringBuilder();
        fleaFilePathStrBuilder.append(rootPackage).append(separator).append(codePackage.replaceAll("\\.", "\\" + separator)).append(separator);
        // 组合代码文件路径（自定义部分）
        combinedFilePath(fleaFilePathStrBuilder, entityClassName, separator, param);
        return fleaFilePathStrBuilder.toString();
    }

    /**
     * <p> 组合代码文件路径，自定义部分 </p>
     *
     * @param fleaFilePathStrBuilder 代码文件路径
     * @param entityClassName        实体类名
     * @param separator              路径分隔符
     */
    protected abstract void combinedFilePath(StringBuilder fleaFilePathStrBuilder, String entityClassName, String separator, Map<String, Object> param);

    /**
     * <p> 由具体子类实现代码编写 </p>
     *
     * @param param 代码模板参数
     * @since 1.0.0
     */
    protected abstract void code(Map<String, Object> param);

}
