package com.huazie.frame.jersey;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import static org.junit.Assert.assertEquals;

/**
 * <p>  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class JerseyCoreTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(JerseyCoreTest.class);

    private WebTarget target;

    @Before
    public void init() {
        Client client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/fleafs");
    }

    @Test
    public void testJerseyByParam() {

        String responseMsg = target.queryParam("id", "huazie1").path("resource").request().get(String.class);

        LOGGER.debug("result = {}", responseMsg);

        assertEquals("This is huazie1 !!!", responseMsg);

    }

    @Test
    public void testUpload() {

    }


}
