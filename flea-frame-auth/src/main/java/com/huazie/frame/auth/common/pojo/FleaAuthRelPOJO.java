package com.huazie.frame.auth.common.pojo;

/**
 * <p> Flea关联POJO类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaAuthRelPOJO extends FleaRemarksPOJO {

    private static final long serialVersionUID = -3058250635043572237L;

    private Long relId; // 关联编号

    private String relName; // 关联名称

    private String relType; // 关联类型

    private String relExtA; // 关联扩展字段A

    private String relExtB; // 关联扩展字段B

    private String relExtC; // 关联扩展字段C

    private String relExtX; // 关联扩展字段X

    private String relExtY; // 关联扩展字段Y

    private String relExtZ; // 关联扩展字段Z

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

    public String getRelExtA() {
        return relExtA;
    }

    public void setRelExtA(String relExtA) {
        this.relExtA = relExtA;
    }

    public String getRelExtB() {
        return relExtB;
    }

    public void setRelExtB(String relExtB) {
        this.relExtB = relExtB;
    }

    public String getRelExtC() {
        return relExtC;
    }

    public void setRelExtC(String relExtC) {
        this.relExtC = relExtC;
    }

    public String getRelExtX() {
        return relExtX;
    }

    public void setRelExtX(String relExtX) {
        this.relExtX = relExtX;
    }

    public String getRelExtY() {
        return relExtY;
    }

    public void setRelExtY(String relExtY) {
        this.relExtY = relExtY;
    }

    public String getRelExtZ() {
        return relExtZ;
    }

    public void setRelExtZ(String relExtZ) {
        this.relExtZ = relExtZ;
    }
}
