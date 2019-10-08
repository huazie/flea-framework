package com.huazie.frame.core.base.cfgdata.entity;

import com.huazie.frame.common.FleaEntity;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * <p> 参数配置数据表对应实体类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "flea_para_detail")
public class FleaParaDetail extends FleaEntity {

    private static final long serialVersionUID = -1711370793670889694L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "PARA_DETAIL_SEQ")
    @SequenceGenerator(name = "PARA_DETAIL_SEQ")
    @Column(name = "para_id", unique = true, nullable = false)
    private Long paraId;        // 参数编号

    @Column(name = "para_type", nullable = false)
    private String paraType;    // 参数类型

    @Column(name = "para_code", nullable = false)
    private String paraCode;    // 参数编码

    @Column(name = "para_name", nullable = false)
    private String paraName;    // 参数名称

    @Column(name = "para1")
    private String para1;       // 参数1

    @Column(name = "para2")
    private String para2;       // 参数2

    @Column(name = "para3")
    private String para3;       // 参数3

    @Column(name = "para4")
    private String para4;       // 参数4

    @Column(name = "para5")
    private String para5;       // 参数5

    @Column(name = "para_state", nullable = false)
    private Integer paraState;  // 参数状态

    @Column(name = "para_desc")
    private String paraDesc;    // 参数描述

    public Long getParaId() {
        return paraId;
    }

    public void setParaId(Long paraId) {
        this.paraId = paraId;
    }

    public String getParaType() {
        return paraType;
    }

    public void setParaType(String paraType) {
        this.paraType = paraType;
    }

    public String getParaCode() {
        return paraCode;
    }

    public void setParaCode(String paraCode) {
        this.paraCode = paraCode;
    }

    public String getParaName() {
        return paraName;
    }

    public void setParaName(String paraName) {
        this.paraName = paraName;
    }

    public String getPara1() {
        return para1;
    }

    public void setPara1(String para1) {
        this.para1 = para1;
    }

    public String getPara2() {
        return para2;
    }

    public void setPara2(String para2) {
        this.para2 = para2;
    }

    public String getPara3() {
        return para3;
    }

    public void setPara3(String para3) {
        this.para3 = para3;
    }

    public String getPara4() {
        return para4;
    }

    public void setPara4(String para4) {
        this.para4 = para4;
    }

    public String getPara5() {
        return para5;
    }

    public void setPara5(String para5) {
        this.para5 = para5;
    }

    public Integer getParaState() {
        return paraState;
    }

    public void setParaState(Integer paraState) {
        this.paraState = paraState;
    }

    public String getParaDesc() {
        return paraDesc;
    }

    public void setParaDesc(String paraDesc) {
        this.paraDesc = paraDesc;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
