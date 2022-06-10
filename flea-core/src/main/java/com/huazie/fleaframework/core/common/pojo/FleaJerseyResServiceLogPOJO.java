package com.huazie.fleaframework.core.common.pojo;

import com.huazie.fleaframework.common.pojo.FleaRemarksPOJO;

/**
 * Flea 资源服务调用日志POJO类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaJerseyResServiceLogPOJO extends FleaRemarksPOJO {

    private static final long serialVersionUID = -8643974708056368881L;

    private String resourceCode; // 资源编码

    private String serviceCode; // 服务编码

    private String input; // 业务入参

    private String output; // 业务出参

    private String resultCode; // 操作结果码

    private String resultMess; // 操作结果信息

    private Long accountId; // 操作账户编号

    private Long systemAccountId; // 系统账户编号

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMess() {
        return resultMess;
    }

    public void setResultMess(String resultMess) {
        this.resultMess = resultMess;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getSystemAccountId() {
        return systemAccountId;
    }

    public void setSystemAccountId(Long systemAccountId) {
        this.systemAccountId = systemAccountId;
    }
}
