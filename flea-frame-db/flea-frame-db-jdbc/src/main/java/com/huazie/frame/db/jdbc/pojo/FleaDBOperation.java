package com.huazie.frame.db.jdbc.pojo;

import com.huazie.frame.db.jdbc.config.FleaJDBCConfig;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * <p> Flea数据库操作 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaDBOperation implements Closeable {

    private Connection connection;

    private PreparedStatement preparedStatement;

    private ResultSet resultSet;

    @Override
    public void close() {
        FleaJDBCConfig.close(connection, preparedStatement, resultSet);
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public void setPreparedStatement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }
}
