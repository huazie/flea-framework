package com.huazie.fleaframework.auth.common.pojo;

import com.huazie.fleaframework.common.pojo.FleaRemarksPOJO;

/**
 * Flea授权关联扩展数据POJO类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaAuthRelExtPOJO extends FleaRemarksPOJO {

    private static final long serialVersionUID = -5336694304744912354L;

    private String relExtA; // 关联扩展字段A

    private String relExtB; // 关联扩展字段B

    private String relExtC; // 关联扩展字段C

    private String relExtX; // 关联扩展字段X

    private String relExtY; // 关联扩展字段Y

    private String relExtZ; // 关联扩展字段Z

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
