package com.huazie.fleaframework.jersey.client.resource.pojo.download.output;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 下载鉴权业务出参定义
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
public class OutputDownloadAuthInfo implements Serializable {

    private static final long serialVersionUID = 5689920399219551237L;

    private String token; // 下载鉴权令牌

}
