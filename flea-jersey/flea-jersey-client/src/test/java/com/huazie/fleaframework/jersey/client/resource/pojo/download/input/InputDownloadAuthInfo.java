package com.huazie.fleaframework.jersey.client.resource.pojo.download.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 下载鉴权业务入参定义
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
public class InputDownloadAuthInfo implements Serializable {

    private static final long serialVersionUID = 6849188299874561970L;

    private String fileId; // 文件编号

}
