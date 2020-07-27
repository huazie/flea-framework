package com.huazie.frame.db.common.sql.template.impl;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.ExceptionUtils;
import com.huazie.frame.common.util.MapUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.DBConstants;
import com.huazie.frame.db.common.sql.template.SqlTemplate;
import com.huazie.frame.db.common.sql.template.SqlTemplateEnum;
import com.huazie.frame.db.common.sql.template.TemplateTypeEnum;
import com.huazie.frame.db.common.sql.template.config.Property;
import com.huazie.frame.db.common.table.pojo.Column;
import org.apache.commons.lang.ArrayUtils;

import java.util.Map;

/**
 * <p> 更新SQL模板, 用于使用原生SQL实现分表; </p>
 * <p> 更新SQL模板定义配置在<b>flea-sql-template.xml</b>; </p>
 * <pre>
 * (1) 模板配置信息：
 * {@code
 *   <!-- SQL模板配置 -->
 *   <templates>
 *     <template id="update" ruleId="update" name="UPDATE SQL模板" desc="用于原生SQL中UPDATE语句的使用">
 *       <property key="template" value="UPDATE ##table## SET ##sets## WHERE ##conditions##" />
 *       <property key="type" value="update"/>
 *     </template>
 *   </templates>
 *   <!-- SQL模板参数配置 -->
 *   <params>
 *     <param id="update" name="SQL模板參數" desc="用于定义SQL模板中的替换参数">
 *       <property key="table" value="flea_para_detail" />
 *       <property key="sets" value="para1 = :para1, para2 = :para2" />
 *       <property key="conditions" value="para_id = :paraId and ( para_type = :paraType or para_type = 'nihao') " />
 *     </param>
 *   </params>
 *   <!-- SQL模板和模板参数关联关系配置（简称 SQL关系配置）-->
 *   <relations>
 *     <relation id="update" templateId="update" paramId="update" name="SQL关系"/>
 *   </relations>
 * 	}
 * (2)使用示例
 *  示例1：
 *  SqlTemplate<Student> sqlTemplate = new UpdateSqlTemplate<Student>();
 *  // 这个对应{@code <relation id="update" .../>}
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
     * @param id     关系编号
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
     * @param id        关系编号
     * @param tableName 表名
     * @param entity    实体类的实例对象
     * @since 1.0.0
     */
    public UpdateSqlTemplate(String id, String tableName, T entity) {
        super(id, tableName, entity);
        templateType = TemplateTypeEnum.UPDATE;
    }

    @Override
    protected void initSqlTemplate(StringBuilder sql, Map<String, Object> params, Column[] entityCols, Map<String, Property> propMap) throws CommonException {
        // 获取【key=sets】的属性， 存储SET子句的内容（ para1 = :para1, para2 = :para2）
        String setStr = checkProperty(propMap, SqlTemplateEnum.SETS);

        // 获取【key=conditions】的属性，存储WHERE子句的内容 （para_id = :paraId and para_type = :paraType）
        String condStr = checkProperty(propMap, SqlTemplateEnum.CONDITIONS);

        Map<String, String> setMap = StringUtils.split(StringUtils.split(setStr, DBConstants.SQLConstants.SQL_COMMA), DBConstants.SQLConstants.SQL_EQUAL);
        Map<String, String> whereMap = createConditionMap(condStr);

        // 校验【key=columns】 和 【key=conditions】的数据是否正确
        Column[] realEntityCols = check(entityCols, setMap, whereMap);
        // 设置SQL参数
        createParamMap(params, realEntityCols);

        // 替换SQL模板中的sets关键字内容
        StringUtils.replace(sql, createPlaceHolder(SqlTemplateEnum.SETS.getKey()), setStr);
        // 替换SQL模板中的conditions关键字内容
        StringUtils.replace(sql, createPlaceHolder(SqlTemplateEnum.CONDITIONS.getKey()), condStr);

    }

    /**
     * <p> 校验【key=columns】和【key=conditions】的数据是否正确 </p>
     * <p> 注意：(1) set子句【key=columns】的属性列 和 where子句【key=columns】的属性列，一般来说应该不一样;
     * (2) set子句【key=columns】的属性列和属性变量一一对应，where子句【key=conditions】的属性列和属性变量一一对应 </p>
     *
     * @param entityCols 实体类对象的属性数组
     * @param setMap     SET子句的map集合（key：属性列， map：属性列变量）
     * @param whereMap   WHERE子句的map集合（key：属性列， map：属性列变量）
     * @return set 和 where子句对应的实体类对象的属性数组
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private Column[] check(final Column[] entityCols, Map<String, String> setMap, Map<String, String> whereMap) throws CommonException {

        // 请检查SQL模板参数【id="{0}"】配置(属性【key="{1}"】中的【value】不能为空)
        MapUtils.checkEmpty(setMap, SQT_CLASS, "ERROR-DB-SQT0000000013", paramId, SqlTemplateEnum.SETS.getKey());

        // 请检查SQL模板参数【id="{0}"】配置(属性【key="{1}"】中的【value】不能为空)
        MapUtils.checkEmpty(whereMap, SQT_CLASS, "ERROR-DB-SQT0000000013", paramId, SqlTemplateEnum.CONDITIONS.getKey());

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
                        // 请检查SQL模板参数【id="{0}"】配置（属性【key="columns"】 和属性【key="conditions"】存在相同的项【{1}】）
                        ExceptionUtils.throwCommonException(SQT_CLASS, "ERROR-DB-SQT0000000029", paramId, setCol.getTabColumnName());
                    }
                }
            }
        }
        return (Column[]) ArrayUtils.addAll(setCols, whereCols);
    }

}
