package com.huazie.frame.db.jdbc.pojo;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p> Flea 数据库单元 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaDBUnit {

    private String database; // 数据库管理系统名

    private String name; // 数据库名 或 数据库用户名

    private String driver; // 数据库驱动名

    private String url; // 数据库连接地址

    private String user; // 数据库登录用户名

    private String password; // 数据库登录密码

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
