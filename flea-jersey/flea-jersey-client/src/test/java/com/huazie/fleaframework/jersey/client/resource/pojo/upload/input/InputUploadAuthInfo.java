package com.huazie.fleaframework.jersey.client.resource.pojo.upload.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 上传鉴权业务入参定义
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
public class InputUploadAuthInfo implements Serializable {

    private static final long serialVersionUID = 5573932231714644551L;

    private Integer categoryId; // 文件类目编号（两者必传一个）

    private String categoryCode; // 文件类目编码（两者必传一个）

    private String fileName; // 文件名

}
