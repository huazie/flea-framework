package com.huazie.fleaframework.jersey.client.resource.pojo.upload.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 文件上传入参定义
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
public class InputFileUploadInfo implements Serializable {

    private static final long serialVersionUID = 8328668851713705342L;

    private String token; // 上传鉴权令牌

}
