package com.huazie.fleaframework.jersey.common.data;

import java.io.File;

/**
 * Flea文件对象
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaFileObject {

    private String fileName; // 文件名

    private File file; // 文件

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
