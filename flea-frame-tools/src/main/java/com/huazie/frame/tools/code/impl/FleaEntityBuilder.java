package com.huazie.frame.tools.code.impl;

import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.IOUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.table.pojo.Column;
import com.huazie.frame.db.jdbc.FleaJDBCHelper;
import com.huazie.frame.db.jdbc.config.FleaJDBCConfig;
import com.huazie.frame.tools.code.FleaCodeHelper;
import com.huazie.frame.tools.common.ToolsConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> Flea实体类代码建造者实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaEntityBuilder extends FleaCodeBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaEntityBuilder.class);

    @Override
    protected void combinedFilePath(StringBuilder fleaFilePathStrBuilder, String entityClassName, String separator) {
        fleaFilePathStrBuilder.append("entity").append(separator).append(entityClassName).append(".java");
    }

    @Override
    protected void code(Map<String, Object> param) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("开始编写实体类代码");
        }

        // 实体类代码文件路径
        String fleaEntityFilePathStr = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.CODE_FILE_PATH));
        // 表名
        String tableName = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.TABLE_NAME));
        // 实体类名
        String entityClassName = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.ENTITY_CLASS_NAME));
        // 数据库系统名
        String dbSystemName = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.DB_SYSTEM_NAME));
        // 数据库名
        String dbName = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.DB_NAME));
        // 获取表列信息
        FleaJDBCConfig.init(dbSystemName, dbName);
        List<Column> columnList = FleaJDBCHelper.queryTableStructure(tableName);
        StringBuilder variableStrBuilder = new StringBuilder();
        StringBuilder methodStrBuilder = new StringBuilder();
        if (CollectionUtils.isEmpty(columnList)) {
            return;
        }
        for (Column column : columnList) {
            variableStrBuilder.append(toVariable(column, tableName)).append("\n\n");
            methodStrBuilder.append(toMethod(column)).append("\n\n");
        }
        variableStrBuilder.delete(variableStrBuilder.length() - 2, variableStrBuilder.length()); // 删除最后两个换行符
        methodStrBuilder.delete(methodStrBuilder.length() - 2, methodStrBuilder.length()); // 删除最后两个换行符

        param.put(ToolsConstants.CodeConstants.VARIABLE_CODE, variableStrBuilder.toString());
        param.put(ToolsConstants.CodeConstants.METHOD_CODE, methodStrBuilder.toString());

        // 获取实体类配置模板文件内容
        String content = IOUtils.toNativeStringFromResource("flea/code/entity/FleaEntity.code");
        // 新建实体类java文件
        IOUtils.toFileFromNativeString(FleaCodeHelper.convert(content, param), fleaEntityFilePathStr);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("实体类 = {}", entityClassName);
            LOGGER.debug("实体类代码文件路径 = {}", fleaEntityFilePathStr);
            LOGGER.debug("结束编写实体类代码");
        }
    }

    /**
     * <p> 获取变量定义代码 </p>
     *
     * @param column    属性列
     * @param tableName 表名
     * @return 变量定义代码
     * @since 1.0.0
     */
    private String toVariable(Column column, String tableName) {
        String variableContent = "";
        if (ObjectUtils.isNotEmpty(column)) {
            Class<?> attrType = column.getAttrType();
            boolean isNullable = column.isNullable();
            boolean isPrimaryKey = column.isPrimaryKey();
            if (Date.class.equals(attrType)) {
                if (isNullable) {
                    variableContent = IOUtils.toNativeStringFromResource("flea/code/entity/VaiableNullDate.code");
                } else {
                    variableContent = IOUtils.toNativeStringFromResource("flea/code/entity/VaiableNotNullDate.code");
                }
            } else {
                if (isPrimaryKey) {
                    if (String.class.equals(attrType)) {
                        variableContent = IOUtils.toNativeStringFromResource("flea/code/entity/VariablePrimaryKeyStr.code");
                    } else {
                        variableContent = IOUtils.toNativeStringFromResource("flea/code/entity/VariablePrimaryKeyNum.code");
                    }
                } else {
                    if (isNullable) {
                        variableContent = IOUtils.toNativeStringFromResource("flea/code/entity/VaiableNull.code");
                    } else {
                        variableContent = IOUtils.toNativeStringFromResource("flea/code/entity/VaiableNotNull.code");
                    }
                }
            }
            Map<String, Object> param = new HashMap<String, Object>();
            param.put(ToolsConstants.CodeConstants.COLUMN_NAME, column.getTabColumnName());
            param.put(ToolsConstants.CodeConstants.COLUMN_DESC, column.getTabColumnDesc());
            param.put(ToolsConstants.CodeConstants.VAR_TYPE, attrType.getSimpleName());
            param.put(ToolsConstants.CodeConstants.VARIABLE, column.getAttrName());
            param.put(ToolsConstants.CodeConstants.TABLE_NAME_1, tableName.toUpperCase());

            variableContent = FleaCodeHelper.convert(variableContent, param);

        }
        return variableContent;
    }

    /**
     * <p> 获取变量GET和SET代码 </p>
     *
     * @param column 属性列
     * @return 变量GET和SET代码
     * @since 1.0.0
     */
    private String toMethod(Column column) {
        String methodContent = "";
        if (ObjectUtils.isNotEmpty(column)) {
            methodContent = IOUtils.toNativeStringFromResource("flea/code/entity/MethodGetAndSet.code");
            Map<String, Object> param = new HashMap<String, Object>();
            param.put(ToolsConstants.CodeConstants.VAR_TYPE, column.getAttrType().getSimpleName());
            param.put(ToolsConstants.CodeConstants.VARIABLE, column.getAttrName());
            param.put(ToolsConstants.CodeConstants.VARIABLE_1, StringUtils.toUpperCaseInitial(column.getAttrName()));
            methodContent = FleaCodeHelper.convert(methodContent, param);
        }
        return methodContent;
    }
}
