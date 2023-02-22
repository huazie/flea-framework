package com.huazie.fleaframework.jersey.client.resource.pojo.download.output;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 文件下载业务出参
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
public class OutputFileDownloadInfo implements Serializable {

    private static final long serialVersionUID = 9049515424324327639L;

    private String uploadAcctId; // 文件上传账户编号

    private String uploadSystemAcctId; // 文件上传系统账户编号

    private String uploadDate; // 文件上传日期

}
