package com.huazie.fleaframework.auth.common.pojo;

/**
 * Flea授权关联POJO类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public class FleaAuthRelPOJO extends FleaAuthRelExtPOJO {

    private static final long serialVersionUID = -694733599111487548L;

    private Long relId; // 关联编号

    private String relName; // 关联名称

    private String relType; // 关联类型

    public Long getRelId() {
        return relId;
    }

    public void setRelId(Long relId) {
        this.relId = relId;
    }

    public String getRelName() {
        return relName;
    }

    public void setRelName(String relName) {
        this.relName = relName;
    }

    public String getRelType() {
        return relType;
    }

    public void setRelType(String relType) {
        this.relType = relType;
    }
}
