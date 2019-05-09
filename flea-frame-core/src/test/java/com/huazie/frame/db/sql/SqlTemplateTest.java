package com.huazie.frame.db.sql;

import com.huazie.frame.core.base.cfgdata.entity.FleaParaDetail;
import com.huazie.frame.core.base.cfgdata.ivalues.IFleaParaDetailValue;
import com.huazie.frame.core.base.cfgdata.service.interfaces.IFleaParaDetailSV;
import com.huazie.frame.db.common.sql.template.ITemplate;
import com.huazie.frame.db.common.sql.template.SqlTemplate;
import com.huazie.frame.db.common.sql.template.impl.DeleteSqlTemplate;
import com.huazie.frame.db.common.sql.template.impl.InsertSqlTemplate;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SqlTemplateTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(SqlTemplateTest.class);

    private ApplicationContext applicationContext;
    private IFleaParaDetailValue fleaParaDetail;

    @Before
    public void init() {
        try {
            applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
            LOGGER.debug("ApplicationContext={}", applicationContext);
            IFleaParaDetailSV sv = (IFleaParaDetailSV) applicationContext.getBean("fleaParaDetailSVImpl");
            fleaParaDetail = sv.getParaDetail("FLEAER_CERT_TYPE", "1");
            LOGGER.debug("FleaParaDetail={}", fleaParaDetail);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testDeleteSqlTemplate1() {
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
        try {
            ITemplate<FleaParaDetail> sqlTemplate = new DeleteSqlTemplate<FleaParaDetail>("delete", (FleaParaDetail) fleaParaDetail);
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
        try {
            ITemplate<FleaParaDetail> sqlTemplate = new DeleteSqlTemplate<FleaParaDetail>("delete", "flea_para_detail", (FleaParaDetail) fleaParaDetail);
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
        try {
            SqlTemplate<FleaParaDetail> sqlTemplate = new InsertSqlTemplate<FleaParaDetail>();
            // 这个对应<template id="insert">
            sqlTemplate.setId("insert");
            // 实体类对应的表名
            sqlTemplate.setTableName("flea_para_detail");
            // 实体类的实例对象
            sqlTemplate.setEntity((FleaParaDetail) fleaParaDetail);
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
        try {
            ITemplate<FleaParaDetail> sqlTemplate = new InsertSqlTemplate<FleaParaDetail>("insert", (FleaParaDetail) fleaParaDetail);
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
            ITemplate<FleaParaDetail> sqlTemplate = new InsertSqlTemplate<FleaParaDetail>("insert", "flea_para_detail", (FleaParaDetail) fleaParaDetail);
            // 模板初始化
            sqlTemplate.initialize();
            LOGGER.debug("testInsertSqlTemplate3 --> NativeSql={}", sqlTemplate.toNativeSql());
            LOGGER.debug("testInsertSqlTemplate3 --> NativeParams={}", sqlTemplate.toNativeParams());

        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testSelectSqlTemplate() {

    }

    @Test
    public void testUpdateSqlTemplate() {

    }

}