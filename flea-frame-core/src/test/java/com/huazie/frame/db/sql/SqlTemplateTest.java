package com.huazie.frame.db.sql;

import com.huazie.frame.common.FleaFrameManager;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.core.base.cfgdata.entity.FleaConfigData;
import com.huazie.frame.core.base.cfgdata.service.interfaces.IFleaConfigDataSV;
import com.huazie.frame.db.common.sql.template.ITemplate;
import com.huazie.frame.db.common.sql.template.SqlTemplate;
import com.huazie.frame.db.common.sql.template.impl.DeleteSqlTemplate;
import com.huazie.frame.db.common.sql.template.impl.InsertSqlTemplate;
import com.huazie.frame.db.common.sql.template.impl.SelectSqlTemplate;
import com.huazie.frame.db.common.sql.template.impl.UpdateSqlTemplate;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;

public class SqlTemplateTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(SqlTemplateTest.class);

    private ApplicationContext applicationContext;
    private FleaConfigData fleaConfigData;

    @Before
    public void init() {
        try {
            applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
            LOGGER.debug("ApplicationContext={}", applicationContext);
            IFleaConfigDataSV sv = (IFleaConfigDataSV) applicationContext.getBean("fleaConfigDataSV");
            fleaConfigData = sv.getConfigData("huazie", "huazie");
            LOGGER.debug("FleaConfigData={}", fleaConfigData);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testDeleteSqlTemplate1() {
        FleaFrameManager.getManager().setLocale(Locale.US);
        try {
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

        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testDeleteSqlTemplate2() {
        FleaFrameManager.getManager().setLocale(Locale.US);
        try {
            ITemplate<FleaConfigData> sqlTemplate = new DeleteSqlTemplate<>("delete", fleaConfigData);
            // 模板初始化
            sqlTemplate.initialize();
            LOGGER.debug("testDeleteSqlTemplate2 --> NativeSql={}", sqlTemplate.toNativeSql());
            LOGGER.debug("testDeleteSqlTemplate2 --> NativeParams={}", sqlTemplate.toNativeParams());

        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testDeleteSqlTemplate3() {
        FleaFrameManager.getManager().setLocale(Locale.US);
        try {
            ITemplate<FleaConfigData> sqlTemplate = new DeleteSqlTemplate<>("delete", "flea_config_data", fleaConfigData);
            // 模板初始化
            sqlTemplate.initialize();
            LOGGER.debug("testDeleteSqlTemplate3 --> NativeSql={}", sqlTemplate.toNativeSql());
            LOGGER.debug("testDeleteSqlTemplate3 --> NativeParams={}", sqlTemplate.toNativeParams());

        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testInsertSqlTemplate1() {
        FleaFrameManager.getManager().setLocale(Locale.US);
        try {
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

        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testInsertSqlTemplate2() {
        FleaFrameManager.getManager().setLocale(Locale.US);
        try {
            ITemplate<FleaConfigData> sqlTemplate = new InsertSqlTemplate<>("insert", fleaConfigData);
            // 模板初始化
            sqlTemplate.initialize();
            LOGGER.debug("testInsertSqlTemplate2 --> NativeSql={}", sqlTemplate.toNativeSql());
            LOGGER.debug("testInsertSqlTemplate2 --> NativeParams={}", sqlTemplate.toNativeParams());

        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testInsertSqlTemplate3() {
//        FleaFrameManager.getManager().setLocale(Locale.US);
        try {
            ITemplate<FleaConfigData> sqlTemplate = new InsertSqlTemplate<>("insert", "flea_config_data", fleaConfigData);
            // 模板初始化
            sqlTemplate.initialize();
            LOGGER.debug("testInsertSqlTemplate3 --> NativeSql={}", sqlTemplate.toNativeSql());
            LOGGER.debug("testInsertSqlTemplate3 --> NativeParams={}", sqlTemplate.toNativeParams());

        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testSelectSqlTemplate1() {
        FleaFrameManager.getManager().setLocale(Locale.US);
        try {
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

        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testSelectSqlTemplate2() {
        FleaFrameManager.getManager().setLocale(Locale.US);
        try {
            ITemplate<FleaConfigData> sqlTemplate = new SelectSqlTemplate<>("select", fleaConfigData);
            // 模板初始化
            sqlTemplate.initialize();
            LOGGER.debug("testSelectSqlTemplate2 --> NativeSql={}", sqlTemplate.toNativeSql());
            LOGGER.debug("testSelectSqlTemplate2 --> NativeParams={}", sqlTemplate.toNativeParams());

        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testSelectSqlTemplate3() {
        FleaFrameManager.getManager().setLocale(Locale.US);
        try {
            ITemplate<FleaConfigData> sqlTemplate = new SelectSqlTemplate<>("select", "flea_config_data", fleaConfigData);
            // 模板初始化
            sqlTemplate.initialize();
            LOGGER.debug("testSelectSqlTemplate3 --> NativeSql={}", sqlTemplate.toNativeSql());
            LOGGER.debug("testSelectSqlTemplate3 --> NativeParams={}", sqlTemplate.toNativeParams());

        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testUpdateSqlTemplate1() {
        FleaFrameManager.getManager().setLocale(Locale.US);
        try {
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

        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testUpdateSqlTemplate2() {
        FleaFrameManager.getManager().setLocale(Locale.US);
        try {
            ITemplate<FleaConfigData> sqlTemplate = new UpdateSqlTemplate<>("update", fleaConfigData);
            // 模板初始化
            sqlTemplate.initialize();
            LOGGER.debug("testUpdateSqlTemplate2 --> NativeSql={}", sqlTemplate.toNativeSql());
            LOGGER.debug("testUpdateSqlTemplate2 --> NativeParams={}", sqlTemplate.toNativeParams());

        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testUpdateSqlTemplate3() {
        FleaFrameManager.getManager().setLocale(Locale.US);
        try {
            ITemplate<FleaConfigData> sqlTemplate = new UpdateSqlTemplate<>("update", "flea_config_data", fleaConfigData);
            // 模板初始化
            sqlTemplate.initialize();
            LOGGER.debug("testUpdateSqlTemplate3 --> NativeSql={}", sqlTemplate.toNativeSql());
            LOGGER.debug("testUpdateSqlTemplate3 --> NativeParams={}", sqlTemplate.toNativeParams());

        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

}