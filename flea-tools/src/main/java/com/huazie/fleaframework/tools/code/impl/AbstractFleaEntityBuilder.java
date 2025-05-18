package com.huazie.fleaframework.tools.code.impl;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.IOUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.db.common.table.pojo.Column;
import com.huazie.fleaframework.db.jdbc.FleaJDBCHelper;
import com.huazie.fleaframework.db.jdbc.config.FleaJDBCConfig;
import com.huazie.fleaframework.tools.code.FleaCodeHelper;
import com.huazie.fleaframework.tools.common.ToolsConstants;

import javax.persistence.GenerationType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 抽象Flea实体类代码建造者实现
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class AbstractFleaEntityBuilder extends FleaCodeBuilder {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(AbstractFleaEntityBuilder.class);

    @Override
    protected void combinedFilePath(StringBuilder fleaFilePathStrBuilder, String entityClassName, String separator, Map<String, Object> param) {
        fleaFilePathStrBuilder.append("entity").append(separator).append(entityClassName).append(".java");
    }

    @Override
    protected void code(Map<String, Object> param) {
        LOGGER.debug("开始编写实体类代码");

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
            variableStrBuilder.append(toVariable(column, tableName, param)).append("\n\n");
            methodStrBuilder.append(toMethod(column)).append("\n\n");
        }
        variableStrBuilder.delete(variableStrBuilder.length() - 2, variableStrBuilder.length()); // 删除最后两个换行符
        methodStrBuilder.delete(methodStrBuilder.length() - 2, methodStrBuilder.length()); // 删除最后两个换行符

        param.put(ToolsConstants.CodeConstants.VARIABLE_CODE, variableStrBuilder.toString());
        param.put(ToolsConstants.CodeConstants.METHOD_CODE, methodStrBuilder.toString());

        // 获取实体类配置模板文件内容
        String content = toEntityCodeFileContent();
        // 新建实体类java文件
        IOUtils.toFileFromNativeString(FleaCodeHelper.convert(content, param), fleaEntityFilePathStr);

        LOGGER.debug("实体类 = {}", entityClassName);
        LOGGER.debug("实体类代码文件路径 = {}", fleaEntityFilePathStr);
        LOGGER.debug("结束编写实体类代码");

        String idGeneratorStrategy = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.ID_GENERATOR_STRATEGY));
        if (GenerationType.TABLE.name().equals(idGeneratorStrategy)) {
            try {
                // 添加ID生成器表数据
                FleaJDBCConfig.init(dbSystemName, dbName);
                String sql = "INSERT INTO %ID_GENERATOR_TABLE% (%PK_COLUMN_NAME%, %VALUE_COLUMN_NAME%) VALUES (?, ?)";
                sql = FleaCodeHelper.convert(sql, param);
                List<Object> params = new ArrayList<>();
                params.add("pk_" + tableName);
                params.add(0L);
                FleaJDBCHelper.insert(sql, params);
            } catch (SQLException e) {
                LOGGER.error("Exception = ", e);
            }
        }
    }

    /**
     * 获取实体代码文件配置内容
     *
     * @return 实体代码文件配置内容
     * @since 2.0.0
     */
    protected abstract String toEntityCodeFileContent();

    /**
     * <p> 获取变量定义代码 </p>
     *
     * @param column    属性列
     * @param tableName 表名
     * @return 变量定义代码
     * @since 1.0.0
     */
    private String toVariable(Column column, String tableName, Map<String, Object> pubParam) {
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
                        variableContent = IOUtils.toNativeStringFromResource("flea/code/entity/VariablePrimaryKey4NoneStrategy.code");
                    } else {
                        variableContent = toVariablePrimaryKeyNum(pubParam);
                    }
                } else {
                    if (isNullable) {
                        variableContent = IOUtils.toNativeStringFromResource("flea/code/entity/VaiableNull.code");
                    } else {
                        variableContent = IOUtils.toNativeStringFromResource("flea/code/entity/VaiableNotNull.code");
                    }
                }
            }
            Map<String, Object> param = new HashMap<>();
            param.put(ToolsConstants.CodeConstants.COLUMN_NAME, column.getTabColumnName());
            param.put(ToolsConstants.CodeConstants.COLUMN_DESC, column.getTabColumnDesc());
            param.put(ToolsConstants.CodeConstants.VAR_TYPE, attrType.getSimpleName());
            param.put(ToolsConstants.CodeConstants.VARIABLE, column.getAttrName());
            param.put(ToolsConstants.CodeConstants.TABLE_NAME_1, tableName.toUpperCase());
            param.putAll(pubParam);

            variableContent = FleaCodeHelper.convert(variableContent, param);

        }
        return variableContent;
    }

    /**
     * <p> 主键变量代码内容 </p>
     *
     * @return 主键变量代码内容
     * @since 1.0.0
     */
    private String toVariablePrimaryKeyNum(Map<String, Object> pubParam) {
        String pkVariableContent = "";

        String idGeneratorStrategy = StringUtils.valueOf(pubParam.get(ToolsConstants.CodeConstants.ID_GENERATOR_STRATEGY));
        if (ToolsConstants.CodeConstants.NONE.equals(idGeneratorStrategy)) {
            pkVariableContent = IOUtils.toNativeStringFromResource("flea/code/entity/VariablePrimaryKey4NoneStrategy.code");
        } else if (GenerationType.TABLE.name().equals(idGeneratorStrategy)) {
            pkVariableContent = IOUtils.toNativeStringFromResource("flea/code/entity/VariablePrimaryKeyNum4Table.code");
        } else if (GenerationType.IDENTITY.name().equals(idGeneratorStrategy)){
            pkVariableContent = IOUtils.toNativeStringFromResource("flea/code/entity/VariablePrimaryKeyNum4Identity.code");
        }

        return pkVariableContent;
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
            Map<String, Object> param = new HashMap<>();
            param.put(ToolsConstants.CodeConstants.VAR_TYPE, column.getAttrType().getSimpleName());
            param.put(ToolsConstants.CodeConstants.VARIABLE, column.getAttrName());
            param.put(ToolsConstants.CodeConstants.VARIABLE_1, StringUtils.toUpperCaseInitial(column.getAttrName()));
            methodContent = FleaCodeHelper.convert(methodContent, param);
        }
        return methodContent;
    }

    @Override
    public void destroy(Map<String, Object> param) {
        super.destroy(param);

        String idGeneratorStrategy = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.ID_GENERATOR_STRATEGY));
        // 数据库系统名
        String dbSystemName = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.DB_SYSTEM_NAME));
        // 数据库名
        String dbName = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.DB_NAME));
        // 表名
        String tableName = StringUtils.valueOf(param.get(ToolsConstants.CodeConstants.TABLE_NAME));
        if (GenerationType.TABLE.name().equals(idGeneratorStrategy)) {
            try {
                // 添加ID生成器表数据
                FleaJDBCConfig.init(dbSystemName, dbName);
                String sql = "DELETE FROM %ID_GENERATOR_TABLE% WHERE %PK_COLUMN_NAME% = ?";
                sql = FleaCodeHelper.convert(sql, param);
                List<Object> params = new ArrayList<>();
                params.add("pk_" + tableName);
                FleaJDBCHelper.delete(sql, params);
            } catch (SQLException e) {
                LOGGER.error("Exception = ", e);
            }
        }
    }
}
