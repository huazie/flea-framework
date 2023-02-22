package com.huazie.fleaframework.jersey.client.resource.pojo.download.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 文件下载业务入参
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
public class InputFileDownloadInfo implements Serializable {

    private static final long serialVersionUID = 8724133691307180834L;

    private String fileId; // 文件编号（非鉴权下载时用到）

    private String token; // 下载鉴权令牌

}
