package com.huazie.frame.db.sql;

import com.huazie.frame.common.FleaFrameManager;
import com.huazie.frame.core.base.cfgdata.entity.FleaParaDetail;
import com.huazie.frame.core.base.cfgdata.service.interfaces.IFleaParaDetailSV;
import com.huazie.frame.db.common.sql.template.ITemplate;
import com.huazie.frame.db.common.sql.template.SqlTemplate;
import com.huazie.frame.db.common.sql.template.impl.DeleteSqlTemplate;
import com.huazie.frame.db.common.sql.template.impl.InsertSqlTemplate;
import com.huazie.frame.db.common.sql.template.impl.SelectSqlTemplate;
import com.huazie.frame.db.common.sql.template.impl.UpdateSqlTemplate;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;

public class SqlTemplateTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SqlTemplateTest.class);

    private ApplicationContext applicationContext;
    private FleaParaDetail fleaParaDetail;

    @Before
    public void init() {
        try {
            applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
            LOGGER.debug("ApplicationContext={}", applicationContext);
            IFleaParaDetailSV sv = (IFleaParaDetailSV) applicationContext.getBean("fleaParaDetailSV");
            fleaParaDetail = sv.getParaDetail("FLEAER_CERT_TYPE", "1");
            LOGGER.debug("FleaParaDetail={}", fleaParaDetail);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testDeleteSqlTemplate1() {
        FleaFrameManager.getManager().setLocale(Locale.US);
        try {
            SqlTemplate<FleaParaDetail> sqlTemplate = new DeleteSqlTemplate<FleaParaDetail>();
            // 这个对应<template id="delete">
            sqlTemplate.setId("delete");
            // 实体类对应的表名
            sqlTemplate.setTableName("flea_para_detail");
            // 实体类的实例对象
            sqlTemplate.setEntity((FleaParaDetail) fleaParaDetail);
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
            ITemplate<FleaParaDetail> sqlTemplate = new DeleteSqlTemplate<FleaParaDetail>("delete", fleaParaDetail);
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
            ITemplate<FleaParaDetail> sqlTemplate = new DeleteSqlTemplate<FleaParaDetail>("delete", "flea_para_detail", fleaParaDetail);
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
            SqlTemplate<FleaParaDetail> sqlTemplate = new InsertSqlTemplate<FleaParaDetail>();
            // 这个对应<template id="insert">
            sqlTemplate.setId("insert");
            // 实体类对应的表名
            sqlTemplate.setTableName("flea_para_detail");
            // 实体类的实例对象
            sqlTemplate.setEntity(fleaParaDetail);
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
            ITemplate<FleaParaDetail> sqlTemplate = new InsertSqlTemplate<FleaParaDetail>("insert", fleaParaDetail);
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
            ITemplate<FleaParaDetail> sqlTemplate = new InsertSqlTemplate<FleaParaDetail>("insert", "flea_para_detail", fleaParaDetail);
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
            SqlTemplate<FleaParaDetail> sqlTemplate = new SelectSqlTemplate<FleaParaDetail>();
            // 这个对应<template id="select">
            sqlTemplate.setId("select");
            // 实体类对应的表名
            sqlTemplate.setTableName("flea_para_detail");
            // 实体类的实例对象
            sqlTemplate.setEntity(fleaParaDetail);
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
            ITemplate<FleaParaDetail> sqlTemplate = new SelectSqlTemplate<FleaParaDetail>("select", fleaParaDetail);
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
            ITemplate<FleaParaDetail> sqlTemplate = new SelectSqlTemplate<FleaParaDetail>("select", "flea_para_detail", fleaParaDetail);
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
            SqlTemplate<FleaParaDetail> sqlTemplate = new UpdateSqlTemplate<FleaParaDetail>();
            // 这个对应<template id="update">
            sqlTemplate.setId("update");
            // 实体类对应的表名
            //sqlTemplate.setTableName("flea_para_detail");
            // 实体类的实例对象
            sqlTemplate.setEntity(fleaParaDetail);
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
            ITemplate<FleaParaDetail> sqlTemplate = new UpdateSqlTemplate<FleaParaDetail>("update", fleaParaDetail);
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
            ITemplate<FleaParaDetail> sqlTemplate = new UpdateSqlTemplate<FleaParaDetail>("update", "flea_para_detail", fleaParaDetail);
            // 模板初始化
            sqlTemplate.initialize();
            LOGGER.debug("testUpdateSqlTemplate3 --> NativeSql={}", sqlTemplate.toNativeSql());
            LOGGER.debug("testUpdateSqlTemplate3 --> NativeParams={}", sqlTemplate.toNativeParams());

        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

}