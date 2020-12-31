package com.huazie.frame.auth;

import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.db.common.DBSystemEnum;
import com.huazie.frame.db.jdbc.FleaJDBCHelper;
import com.huazie.frame.db.jdbc.config.FleaJDBCConfig;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.GenerationType;
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
    public void testGenerationType() {
        LOGGER.debug("GenerationType = {}", GenerationType.TABLE.name());
    }

    @Test
    public void testIDGeneratorInsert() {
        try {
            FleaJDBCConfig.init(DBSystemEnum.MySQL.getName(), "fleaauth");
            String sql = "insert into flea_id_generator (id_generator_key, id_generator_value) values(?, ?)";
            String pkColumnValue = "pk_flea_login_log_202008";
            List<Object> params = new ArrayList<Object>();
            params.add(pkColumnValue);
            params.add(0L);
            FleaJDBCHelper.insert(sql, params);
        } catch (SQLException e) {
            LOGGER.error("Exception : ", e);
        }
    }
}
