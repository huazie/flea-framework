package com.huazie.frame.db.common.sql.template.impl;

import com.huazie.frame.common.util.MapUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.DBConstants;
import com.huazie.frame.db.common.exception.SqlTemplateException;
import com.huazie.frame.db.common.sql.template.SqlTemplate;
import com.huazie.frame.db.common.sql.template.SqlTemplateEnum;
import com.huazie.frame.db.common.sql.template.TemplateTypeEnum;
import com.huazie.frame.db.common.sql.template.config.Property;
import com.huazie.frame.db.common.sql.template.config.SqlTemplateConfig;
import com.huazie.frame.db.common.sql.template.config.Template;
import com.huazie.frame.db.common.table.column.Column;
import org.apache.commons.lang.ArrayUtils;

import java.util.Map;

/**
 * <p> 更新SQL模板, 用于使用原生SQL实现分表; </p>
 * <p> 更新模板定义配置在<b>flea-sql-template.xml</b>; </p>
 * <p> 节点<b>{@code <update>}</b>下即为更新SQL模板: </p>
 * <pre>
 * (1) 模板配置信息：
 *  {@code <template id="update" name="更新模板" desc="用于原生SQL中更新语句的使用">
 *     <property key="template" value="UPDATE ##table## SET ##columns## WHERE ##conditions##"/>
 *     <property key="table" value="student"/>
 *     <property key="columns" value="para1 = :para1, para2 = :para2"/>
 *     <property key="conditions" value="para_id = :paraId and para_type = :paraType"/>
 *   </template>}
 * (2)使用示例
 *  示例1：
 *  SqlTemplate<Student> sqlTemplate = new UpdateSqlTemplate<Student>();
 *  // 这个对应{@code <template id="update" >}
 *  sqlTemplate.setId("update");
 *  // 实体类对应的表名
 *  sqlTemplate.setTableName("student");
 *  // 实体类的实例对象
 *  sqlTemplate.setEntity(student);
 *  // 模板初始化
 *  sqlTemplate.initialize();
 *
 *  示例2：(该方式需要在实体类被@Table或者@FleaTable修饰，能获取指定的表名)
 *  ITemplate<Student> sqlTemplate = new UpdateSqlTemplate<Student>("update", student);
 *  // 模板初始化
 *  sqlTemplate.initialize();
 *
 *  示例3：
 *  ITemplate<Student> sqlTemplate = new UpdateSqlTemplate<FleaParaDetail>("update", "student", student);
 *  // 模板初始化
 *  sqlTemplate.initialize();
 *
 *  (3) 模板初始化后，就可以获取原生SQL和相关参数了，如下：
 *  // 获取原生SQL
 *  String sql = sqlTemplate.toNativeSql();
 *  // 获取相关参数
 *  Map<String, Object> paramMap = sqlTemplate.toNativeParams();
 * </pre>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class UpdateSqlTemplate<T> extends SqlTemplate<T> {

    private static final long serialVersionUID = 7689029751124562960L;

    /**
     * <p> UPDATE模板构造方法, 参考示例1 </p>
     *
     * @since 1.0.0
     */
    public UpdateSqlTemplate() {
        templateType = TemplateTypeEnum.UPDATE;
    }

    /**
     * <p> UPDATE模板构造方法，参考示例2 </p>
     *
     * @param id     模板编号
     * @param entity 实体类的实例对象
     * @since 1.0.0
     */
    public UpdateSqlTemplate(String id, T entity) {
        super(id, entity);
        templateType = TemplateTypeEnum.UPDATE;
    }

    /**
     * <p> UPDATE模板构造方法，参考示例3 </p>
     *
     * @param id        模板编号
     * @param tableName 表名
     * @param entity    实体类的实例对象
     * @since 1.0.0
     */
    public UpdateSqlTemplate(String id, String tableName, T entity) {
        super(id, tableName, entity);
        templateType = TemplateTypeEnum.UPDATE;
    }

    @Override
    protected Template getSqlTemplate(String id) {
        return SqlTemplateConfig.getConfig().getUpdateTemplate(id);
    }

    @Override
    protected void initSqlTemplate(StringBuilder sql, Map<String, Object> params, Column[] entityCols, Map<String, Property> propMap) throws Exception {
        // 获取【key=sets】的属性， 存储SET子句的内容（ para1 = :para1, para2 = :para2）
        Property sets = propMap.get(SqlTemplateEnum.SETS.getKey());

        if (ObjectUtils.isEmpty(sets)) {
            throw new SqlTemplateException("ERROR-DB-SQT0000000015", templateType.getUpperKey(), getId());
        }

        String setStr = sets.getValue();

        // 获取【key=conditions】的属性，存储WHERE子句的内容 （para_id = :paraId and para_type = :paraType）
        Property conditions = propMap.get(SqlTemplateEnum.CONDITIONS.getKey());

        if (ObjectUtils.isEmpty(conditions)) {
            throw new SqlTemplateException("ERROR-DB-SQT0000000016", templateType.getUpperKey(), getId());
        }

        String condStr = conditions.getValue();

        Map<String, String> setMap = StringUtils.split(StringUtils.split(setStr, DBConstants.SQLConstants.SQL_COMMA), DBConstants.SQLConstants.SQL_EQUAL);
        Map<String, String> whereMap = createConditionMap(condStr);

        // 校验【key=columns】 和 【key=conditions】的数据是否正确
        Column[] realEntityCols = check(entityCols, setMap, whereMap);
        createParamMap(params, realEntityCols);// 设置SQL参数

        StringUtils.replace(sql, createPlaceHolder(SqlTemplateEnum.SETS.getKey()), setStr);
        StringUtils.replace(sql, createPlaceHolder(SqlTemplateEnum.CONDITIONS.getKey()), condStr);

    }

    /**
     * 校验【key=columns】和【key=conditions】的数据是否正确 <br/>
     * 注意： （1）set子句【key=columns】的属性列 和 where子句【key=columns】的属性列，一般来说应该不一样
     * （2）set子句【key=columns】的属性列和属性变量一一对应，where子句【key=conditions】的属性列和属性变量一一对应
     *
     * @param entityCols 实体类对象的属性数组
     * @param setMap     SET子句的map集合（key：属性列， map：属性列变量）
     * @param whereMap   WHERE子句的map集合（key：属性列， map：属性列变量）
     * @return set 和 where子句对应的实体类对象的属性数组
     * @throws Exception
     * @since 1.0.0
     */
    private Column[] check(final Column[] entityCols, Map<String, String> setMap, Map<String, String> whereMap) throws Exception {
        if (MapUtils.isEmpty(setMap)) {
            throw new SqlTemplateException("ERROR-DB-SQT0000000017", templateType.getUpperKey(), getId());
        }

        if (MapUtils.isEmpty(whereMap)) {
            throw new SqlTemplateException("ERROR-DB-SQT0000000018", templateType.getUpperKey(), getId());
        }

        // 校验SET子句中的属性列和属性变量是否一一对应，并获取SET子句相关的属性列集合
        Column[] setCols = checkOneByOne(entityCols, setMap, SqlTemplateEnum.COLUMNS);
        // 校验WHERE子句中的属性列和属性变量是否一一对应，并获取WHERE子句相关的属性列集合
        Column[] whereCols = checkOneByOne(entityCols, whereMap, SqlTemplateEnum.CONDITIONS);

        // 校验set子句集合的属性列 和 where子句集合的属性列, 两者应该各不相同
        if (!ArrayUtils.isEmpty(setCols) && !ArrayUtils.isEmpty(whereCols)) {
            for (Column setCol : setCols) {
                for (Column whereCol : whereCols) {
                    if (setCol.getTabColumnName().equals(whereCol.getTabColumnName())
                            && setCol.getAttrName().equals(whereCol.getAttrName())) {
                        throw new SqlTemplateException("ERROR-DB-SQT0000000019", templateType.getUpperKey(), getId(), setCol.getTabColumnName());
                    }
                }
            }
        }
        return (Column[]) ArrayUtils.addAll(setCols, whereCols);
    }

}
