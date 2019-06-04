package com.huazie.frame.jersey;

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
public class JerseyTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(JerseyTest.class);


    @Test
    public void TestJersey() {

        Client client = ClientBuilder.newClient();

        WebTarget target = client.target("http://localhost:8080/fleafs");

        String responseMsg = target.path("resource").request().get(String.class);

        LOGGER.debug("result = {}", responseMsg);

        assertEquals("Hello Wrold !!!", responseMsg);
    }

}
