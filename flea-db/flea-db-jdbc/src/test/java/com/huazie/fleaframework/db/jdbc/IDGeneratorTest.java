package com.huazie.fleaframework.db.jdbc;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.db.common.DBSystemEnum;
import com.huazie.fleaframework.db.jdbc.config.FleaJDBCConfig;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class IDGeneratorTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(IDGeneratorTest.class);

    @Test
    public void testIDGeneratorInsert() {
        try {
            FleaJDBCConfig.init(DBSystemEnum.MySQL.getName(), "fleaauth");
            String sql = "insert into flea_id_generator (id_generator_key, id_generator_value) values(?, ?)";
            String pkColumnValue = "pk_flea_login_log_202008";
            List<Object> params = new ArrayList<>();
            params.add(pkColumnValue);
            params.add(0L);
            FleaJDBCHelper.insert(sql, params);
        } catch (SQLException e) {
            LOGGER.error("Exception : ", e);
        }
    }
}
