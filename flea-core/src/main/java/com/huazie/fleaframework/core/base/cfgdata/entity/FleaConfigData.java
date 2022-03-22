package com.huazie.fleaframework.core.base.cfgdata.entity;

import com.huazie.fleaframework.common.FleaEntity;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Flea 配置数据表对应的实体类
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
@Entity
@Table(name = "flea_config_data")
public class FleaConfigData extends FleaEntity {

    private static final long serialVersionUID = 4036225722111295306L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "config_id", unique = true, nullable = false)
    private Long configId; // 配置编号

    @Column(name = "config_type", nullable = false)
    private String configType; // 配置类型

    @Column(name = "config_code", nullable = false)
    private String configCode; // 配置编码

    @Column(name = "config_name", nullable = false)
    private String configName; // 配置名称

    @Column(name = "config_desc")
    private String configDesc; // 配置描述

    @Column(name = "config_state", nullable = false)
    private Integer configState; // 配置状态

    @Column(name = "data1")
    private String data1; // 数据1

    @Column(name = "data2")
    private String data2; // 数据2

    @Column(name = "data3")
    private String data3; // 数据3

    @Column(name = "data4")
    private String data4; // 数据4

    @Column(name = "data5")
    private String data5; // 数据5

    @Column(name = "data6")
    private String data6; // 数据6

    @Column(name = "data7")
    private String data7; // 数据7

    @Column(name = "data8")
    private String data8; // 数据8

    @Column(name = "data9")
    private String data9; // 数据9

    @Column(name = "data10")
    private String data10; // 数据10

    public Long getConfigId() {
        return configId;
    }

    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getConfigCode() {
        return configCode;
    }

    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigDesc() {
        return configDesc;
    }

    public void setConfigDesc(String configDesc) {
        this.configDesc = configDesc;
    }

    public Integer getConfigState() {
        return configState;
    }

    public void setConfigState(Integer configState) {
        this.configState = configState;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    public String getData3() {
        return data3;
    }

    public void setData3(String data3) {
        this.data3 = data3;
    }

    public String getData4() {
        return data4;
    }

    public void setData4(String data4) {
        this.data4 = data4;
    }

    public String getData5() {
        return data5;
    }

    public void setData5(String data5) {
        this.data5 = data5;
    }

    public String getData6() {
        return data6;
    }

    public void setData6(String data6) {
        this.data6 = data6;
    }

    public String getData7() {
        return data7;
    }

    public void setData7(String data7) {
        this.data7 = data7;
    }

    public String getData8() {
        return data8;
    }

    public void setData8(String data8) {
        this.data8 = data8;
    }

    public String getData9() {
        return data9;
    }

    public void setData9(String data9) {
        this.data9 = data9;
    }

    public String getData10() {
        return data10;
    }

    public void setData10(String data10) {
        this.data10 = data10;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}