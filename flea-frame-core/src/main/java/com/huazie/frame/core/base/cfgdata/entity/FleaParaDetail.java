package com.huazie.frame.core.base.cfgdata.entity;

import com.huazie.frame.common.pojo.FleaCommonEntity;
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
public class FleaParaDetail extends FleaCommonEntity {

    private static final long serialVersionUID = -1711370793670889694L;

    private long paraId;        // 参数编号
    private String paraType;    // 参数类型
    private String paraCode;    // 参数编码
    private String paraName;    // 参数名称
    private String para1;       // 参数1
    private String para2;       // 参数2
    private String para3;       // 参数3
    private String para4;       // 参数4
    private String para5;       // 参数5
    private int paraState;      // 参数状态
    private String paraDesc;    // 参数描述

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "PARA_DETAIL_SEQ")
    @SequenceGenerator(name = "PARA_DETAIL_SEQ")
    @Column(name = "para_id", unique = true, nullable = false)
    public long getParaId() {
        return paraId;
    }

    public void setParaId(long paraId) {
        this.paraId = paraId;
    }

    @Column(name = "para_type", nullable = false)
    public String getParaType() {
        return paraType;
    }

    public void setParaType(String paraType) {
        this.paraType = paraType;
    }

    @Column(name = "para_code", nullable = false)
    public String getParaCode() {
        return paraCode;
    }

    public void setParaCode(String paraCode) {
        this.paraCode = paraCode;
    }

    @Column(name = "para_name", nullable = false)
    public String getParaName() {
        return paraName;
    }

    public void setParaName(String paraName) {
        this.paraName = paraName;
    }

    @Column(name = "para1")
    public String getPara1() {
        return para1;
    }

    public void setPara1(String para1) {
        this.para1 = para1;
    }

    @Column(name = "para2")
    public String getPara2() {
        return para2;
    }

    public void setPara2(String para2) {
        this.para2 = para2;
    }

    @Column(name = "para3")
    public String getPara3() {
        return para3;
    }

    public void setPara3(String para3) {
        this.para3 = para3;
    }

    @Column(name = "para4")
    public String getPara4() {
        return para4;
    }

    public void setPara4(String para4) {
        this.para4 = para4;
    }

    @Column(name = "para5")
    public String getPara5() {
        return para5;
    }

    public void setPara5(String para5) {
        this.para5 = para5;
    }

    @Column(name = "para_state", nullable = false)
    public int getParaState() {
        return paraState;
    }

    public void setParaState(int paraState) {
        this.paraState = paraState;
    }

    @Column(name = "para_desc")
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
