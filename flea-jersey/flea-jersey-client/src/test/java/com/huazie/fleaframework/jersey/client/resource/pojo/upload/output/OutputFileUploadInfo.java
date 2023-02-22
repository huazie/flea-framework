package com.huazie.fleaframework.jersey.client.resource.pojo.upload.output;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 文件上传出参定义
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
public class OutputFileUploadInfo implements Serializable {

    private static final long serialVersionUID = -4807723256322765142L;

    private String fileId; // 文件编号

}
