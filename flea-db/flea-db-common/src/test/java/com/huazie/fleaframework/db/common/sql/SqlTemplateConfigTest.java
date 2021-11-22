package com.huazie.fleaframework.db.common.sql;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.PatternMatcherUtils;
import com.huazie.fleaframework.db.common.sql.template.SqlTemplateEnum;
import com.huazie.fleaframework.db.common.sql.template.config.Property;
import com.huazie.fleaframework.db.common.sql.template.config.Rule;
import com.huazie.fleaframework.db.common.sql.template.config.SqlTemplateConfig;
import org.junit.Test;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p> SQL模板配置自测 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class SqlTemplateConfigTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(SqlTemplateConfigTest.class);

    @Test
    public void testSqlTemplate() {
        LOGGER.debug(SqlTemplateConfig.getConfig().getSql().toString());
        LOGGER.debug(SqlTemplateConfig.getConfig().getSql().getTemplates().toString());
        LOGGER.debug(SqlTemplateConfig.getConfig().getSql().getParams().toString());
        LOGGER.debug(SqlTemplateConfig.getConfig().getSql().getRelations().toString());
    }

    @Test
    public void testRules() {
        LOGGER.debug(SqlTemplateConfig.getConfig().getRule("insert").toString());
        LOGGER.debug(SqlTemplateConfig.getConfig().getRule("update").toString());
        LOGGER.debug(SqlTemplateConfig.getConfig().getRule("delete").toString());
        LOGGER.debug(SqlTemplateConfig.getConfig().getRule("select").toString());
    }

    @Test
    public void testInsertTemplateRule() {

//		String template = " INSERT INTO ##table## ( ##columns## ) VALUES ( ##values## )";
        String template = " Insert into ##table## ( ##columns## ) values ( ##values## )";

        LOGGER.debug("======INSERT规则校验开始======");

        Rule rule = SqlTemplateConfig.getConfig().getRule("insert");

        Map<String, Property> propMap = rule.toPropMap();// 获取其属性值
        Property prop = propMap.get(SqlTemplateEnum.SQL.getKey());// 获取指定的校验规则配置
        String regExp = prop.getValue();

        Pattern p = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(template);
        if (matcher.matches()) {// SQL模板满足校验规则配置
            LOGGER.debug("INSERT规则校验通过");
        } else {
            LOGGER.debug("INSERT规则校验失败");
        }

        LOGGER.debug("======INSERT规则校验结束======");
    }

    @Test
    public void testUpdateTemplateRule() {

//		String template = " UPDATE ##table## SET ##sets## WHERE ##conditions## ";
        String template = " update ##table## set ##sets## where ##conditions## ";

        LOGGER.debug("======UPDATE规则校验开始======");

        Rule rule = SqlTemplateConfig.getConfig().getRule("update");

        Map<String, Property> propMap = rule.toPropMap();// 获取其属性值
        Property prop = propMap.get(SqlTemplateEnum.SQL.getKey());// 获取指定的校验规则配置
        String regExp = prop.getValue();

        Pattern p = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(template);
        if (matcher.matches()) {// SQL模板满足校验规则配置
            LOGGER.debug("UPDATE规则校验通过");
        } else {
            LOGGER.debug("UPDATE规则校验失败");
        }

        LOGGER.debug("======UPDATE规则校验结束======");
    }

    @Test
    public void testSelectTemplateRule() {

//		String template = " SELECT ##columns## FROM ##table## WHERE ##conditions## ";
        String template = " select ##columns## from ##table## where ##conditions## ";

        LOGGER.debug("======SELECT规则校验开始======");

        Rule rule = SqlTemplateConfig.getConfig().getRule("select");

        Map<String, Property> propMap = rule.toPropMap();// 获取其属性值
        Property prop = propMap.get(SqlTemplateEnum.SQL.getKey());// 获取指定的校验规则配置
        String regExp = prop.getValue();

        Pattern p = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(template);
        if (matcher.matches()) {// SQL模板满足校验规则配置
            LOGGER.debug("SELECT规则校验通过");
        } else {
            LOGGER.debug("SELECT规则校验失败");
        }

        LOGGER.debug("======SELECT规则校验结束======");
    }

    @Test
    public void testDeleteTemplateRule() {

        String template = " DELETE FROM ##table## WHERE ##conditions## ";
//		String template = " delete from ##table## where ##conditions## ";

        LOGGER.debug("======DELETE规则校验开始======");

        Rule rule = SqlTemplateConfig.getConfig().getRule("delete");

        Map<String, Property> propMap = rule.toPropMap();// 获取其属性值
        Property prop = propMap.get(SqlTemplateEnum.SQL.getKey());// 获取指定的校验规则配置
        String regExp = prop.getValue();

        Pattern p = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(template);
        if (matcher.matches()) {// SQL模板满足校验规则配置
            LOGGER.debug("DELETE规则校验通过");
        } else {
            LOGGER.debug("DELETE规则校验失败");
        }

        LOGGER.debug("======DELETE规则校验结束======");
    }

    @Test
    public void testPattern() {
        String regExp = "AVG\\([([\\s\\S]*)]*\\)|COUNT\\([([\\s\\S]*)]*\\)|SUM\\([([\\s\\S]*)]*\\)|MAX\\([([\\s\\S]*)]*\\)|MIN\\([([\\s\\S]*)]*\\)";
		String input = "avg(stu_id)";
        LOGGER.debug("RESULT = {}", PatternMatcherUtils.matches(regExp, input, Pattern.CASE_INSENSITIVE));
    }

    @Test
    public void testPattern1() {
        String regExp = "[ ]*1[ ]*=[ ]*1[ ]*";
        String input = " 1= 1 ";
        LOGGER.debug("RESULT = {}", PatternMatcherUtils.matches(regExp, input, Pattern.CASE_INSENSITIVE));
    }

}
