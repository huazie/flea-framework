package com.huazie.frame.jersey;

import com.huazie.frame.jersey.api.data.FleaJerseyRequest;
import com.huazie.frame.jersey.api.data.FleaJerseyRequestData;
import com.huazie.frame.jersey.api.data.FleaJerseyResponse;
import com.huazie.frame.jersey.api.data.RequestPublicData;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

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

        FleaJerseyRequest request = new FleaJerseyRequest();
        FleaJerseyRequestData requestData = new FleaJerseyRequestData();

        RequestPublicData publicData = new RequestPublicData();
        publicData.setSystemUserId("1000");
        publicData.setSystemUserPassword("asd123");
        publicData.setResourcePath("upload");

        requestData.setPublicData(publicData);
        //requestData.setBusinessData(new RequestBusinessData());

        request.setRequestData(requestData);

        Entity<FleaJerseyRequest> entity = Entity.entity(request, MediaType.APPLICATION_XML_TYPE);
        FleaJerseyResponse response = target.path("upload").request().post(entity, FleaJerseyResponse.class);

        LOGGER.debug("result = {}", response);
    }


}
