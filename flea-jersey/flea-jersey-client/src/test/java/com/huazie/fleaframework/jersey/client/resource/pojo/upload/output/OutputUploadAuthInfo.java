package com.huazie.fleaframework.jersey.client.resource.pojo.upload.output;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 上传鉴权业务出参定义
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
public class OutputUploadAuthInfo implements Serializable {

    private static final long serialVersionUID = -8413595899070514907L;

    private String token; // 上传鉴权令牌

}
