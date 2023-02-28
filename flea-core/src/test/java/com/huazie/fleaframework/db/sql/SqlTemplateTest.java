package com.huazie.fleaframework.db.sql;

import com.huazie.fleaframework.common.FleaFrameManager;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaConfigData;
import com.huazie.fleaframework.core.base.cfgdata.service.interfaces.IFleaConfigDataSV;
import com.huazie.fleaframework.db.common.sql.template.ITemplate;
import com.huazie.fleaframework.db.common.sql.template.SqlTemplate;
import com.huazie.fleaframework.db.common.sql.template.impl.DeleteSqlTemplate;
import com.huazie.fleaframework.db.common.sql.template.impl.InsertSqlTemplate;
import com.huazie.fleaframework.db.common.sql.template.impl.SelectSqlTemplate;
import com.huazie.fleaframework.db.common.sql.template.impl.UpdateSqlTemplate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Locale;

/**
 * SQL模板单元测试类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SqlTemplateTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(SqlTemplateTest.class);

    @Autowired
    @Qualifier("fleaConfigDataSV")
    private IFleaConfigDataSV fleaConfigDataSV;

    private FleaConfigData fleaConfigData;

    @Before
    public void init() throws CommonException {
        fleaConfigData = fleaConfigDataSV.getConfigData("huazie", "huazie");
        LOGGER.debug("FleaConfigData={}", fleaConfigData);
    }

    @Test
    public void testDeleteSqlTemplate1() throws CommonException {
        FleaFrameManager.getManager().setLocale(Locale.US);
        SqlTemplate<FleaConfigData> sqlTemplate = new DeleteSqlTemplate<>();
        // 这个对应<template id="delete">
        sqlTemplate.setId("delete");
        // 实体类对应的表名
        sqlTemplate.setTableName("flea_config_data");
        // 实体类的实例对象
        sqlTemplate.setEntity(fleaConfigData);
        // 模板初始化
        sqlTemplate.initialize();
        LOGGER.debug("testDeleteSqlTemplate1 --> NativeSql={}", sqlTemplate.toNativeSql());
        LOGGER.debug("testDeleteSqlTemplate1 --> NativeParams={}", sqlTemplate.toNativeParams());
    }

    @Test
    public void testDeleteSqlTemplate2() throws CommonException {
        FleaFrameManager.getManager().setLocale(Locale.US);
        ITemplate<FleaConfigData> sqlTemplate = new DeleteSqlTemplate<>("delete", fleaConfigData);
        // 模板初始化
        sqlTemplate.initialize();
        LOGGER.debug("testDeleteSqlTemplate2 --> NativeSql={}", sqlTemplate.toNativeSql());
        LOGGER.debug("testDeleteSqlTemplate2 --> NativeParams={}", sqlTemplate.toNativeParams());
    }

    @Test
    public void testDeleteSqlTemplate3() throws CommonException {
        FleaFrameManager.getManager().setLocale(Locale.US);
        ITemplate<FleaConfigData> sqlTemplate = new DeleteSqlTemplate<>("delete", "flea_config_data", fleaConfigData);
        // 模板初始化
        sqlTemplate.initialize();
        LOGGER.debug("testDeleteSqlTemplate3 --> NativeSql={}", sqlTemplate.toNativeSql());
        LOGGER.debug("testDeleteSqlTemplate3 --> NativeParams={}", sqlTemplate.toNativeParams());
    }

    @Test
    public void testInsertSqlTemplate1() throws CommonException {
        FleaFrameManager.getManager().setLocale(Locale.US);
        SqlTemplate<FleaConfigData> sqlTemplate = new InsertSqlTemplate<>();
        // 这个对应<template id="insert">
        sqlTemplate.setId("insert");
        // 实体类对应的表名
        sqlTemplate.setTableName("flea_config_data");
        // 实体类的实例对象
        sqlTemplate.setEntity(fleaConfigData);
        // 模板初始化
        sqlTemplate.initialize();
        LOGGER.debug("testInsertSqlTemplate1 --> NativeSql={}", sqlTemplate.toNativeSql());
        LOGGER.debug("testInsertSqlTemplate1 --> NativeParams={}", sqlTemplate.toNativeParams());
    }

    @Test
    public void testInsertSqlTemplate2() throws CommonException {
        FleaFrameManager.getManager().setLocale(Locale.US);
        ITemplate<FleaConfigData> sqlTemplate = new InsertSqlTemplate<>("insert", fleaConfigData);
        // 模板初始化
        sqlTemplate.initialize();
        LOGGER.debug("testInsertSqlTemplate2 --> NativeSql={}", sqlTemplate.toNativeSql());
        LOGGER.debug("testInsertSqlTemplate2 --> NativeParams={}", sqlTemplate.toNativeParams());
    }

    @Test
    public void testInsertSqlTemplate3() throws CommonException {
//        FleaFrameManager.getManager().setLocale(Locale.US);
        ITemplate<FleaConfigData> sqlTemplate = new InsertSqlTemplate<>("insert", "flea_config_data", fleaConfigData);
        // 模板初始化
        sqlTemplate.initialize();
        LOGGER.debug("testInsertSqlTemplate3 --> NativeSql={}", sqlTemplate.toNativeSql());
        LOGGER.debug("testInsertSqlTemplate3 --> NativeParams={}", sqlTemplate.toNativeParams());
    }

    @Test
    public void testSelectSqlTemplate1() throws CommonException {
        FleaFrameManager.getManager().setLocale(Locale.US);
        SqlTemplate<FleaConfigData> sqlTemplate = new SelectSqlTemplate<>();
        // 这个对应<template id="select">
        sqlTemplate.setId("select");
        // 实体类对应的表名
        sqlTemplate.setTableName("flea_config_data");
        // 实体类的实例对象
        sqlTemplate.setEntity(fleaConfigData);
        // 模板初始化
        sqlTemplate.initialize();
        LOGGER.debug("testSelectSqlTemplate1 --> NativeSql={}", sqlTemplate.toNativeSql());
        LOGGER.debug("testSelectSqlTemplate1 --> NativeParams={}", sqlTemplate.toNativeParams());
    }

    @Test
    public void testSelectSqlTemplate2() throws CommonException {
        FleaFrameManager.getManager().setLocale(Locale.US);
        ITemplate<FleaConfigData> sqlTemplate = new SelectSqlTemplate<>("select", fleaConfigData);
        // 模板初始化
        sqlTemplate.initialize();
        LOGGER.debug("testSelectSqlTemplate2 --> NativeSql={}", sqlTemplate.toNativeSql());
        LOGGER.debug("testSelectSqlTemplate2 --> NativeParams={}", sqlTemplate.toNativeParams());
    }

    @Test
    public void testSelectSqlTemplate3() throws CommonException {
        FleaFrameManager.getManager().setLocale(Locale.US);
        ITemplate<FleaConfigData> sqlTemplate = new SelectSqlTemplate<>("select", "flea_config_data", fleaConfigData);
        // 模板初始化
        sqlTemplate.initialize();
        LOGGER.debug("testSelectSqlTemplate3 --> NativeSql={}", sqlTemplate.toNativeSql());
        LOGGER.debug("testSelectSqlTemplate3 --> NativeParams={}", sqlTemplate.toNativeParams());
    }

    @Test
    public void testUpdateSqlTemplate1() throws CommonException {
        FleaFrameManager.getManager().setLocale(Locale.US);
        SqlTemplate<FleaConfigData> sqlTemplate = new UpdateSqlTemplate<>();
        // 这个对应<template id="update">
        sqlTemplate.setId("update");
        // 实体类对应的表名
        //sqlTemplate.setTableName("flea_config_data");
        // 实体类的实例对象
        sqlTemplate.setEntity(fleaConfigData);
        // 模板初始化
        sqlTemplate.initialize();
        LOGGER.debug("testUpdateSqlTemplate1 --> NativeSql={}", sqlTemplate.toNativeSql());
        LOGGER.debug("testUpdateSqlTemplate1 --> NativeParams={}", sqlTemplate.toNativeParams());
    }

    @Test
    public void testUpdateSqlTemplate2() throws CommonException {
        FleaFrameManager.getManager().setLocale(Locale.US);
        ITemplate<FleaConfigData> sqlTemplate = new UpdateSqlTemplate<>("update", fleaConfigData);
        // 模板初始化
        sqlTemplate.initialize();
        LOGGER.debug("testUpdateSqlTemplate2 --> NativeSql={}", sqlTemplate.toNativeSql());
        LOGGER.debug("testUpdateSqlTemplate2 --> NativeParams={}", sqlTemplate.toNativeParams());
    }

    @Test
    public void testUpdateSqlTemplate3() throws CommonException {
        FleaFrameManager.getManager().setLocale(Locale.US);
        ITemplate<FleaConfigData> sqlTemplate = new UpdateSqlTemplate<>("update", "flea_config_data", fleaConfigData);
        // 模板初始化
        sqlTemplate.initialize();
        LOGGER.debug("testUpdateSqlTemplate3 --> NativeSql={}", sqlTemplate.toNativeSql());
        LOGGER.debug("testUpdateSqlTemplate3 --> NativeParams={}", sqlTemplate.toNativeParams());
    }

}