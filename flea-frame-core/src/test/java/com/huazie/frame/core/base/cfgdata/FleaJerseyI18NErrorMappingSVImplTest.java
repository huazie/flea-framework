package com.huazie.frame.core.base.cfgdata;

import com.huazie.frame.common.util.DateUtils;
import com.huazie.frame.core.base.cfgdata.bean.FleaConfigDataSpringBean;
import com.huazie.frame.core.base.cfgdata.entity.FleaJerseyI18nErrorMapping;
import com.huazie.frame.core.base.cfgdata.service.interfaces.IFleaJerseyI18nErrorMappingSV;
import com.huazie.frame.core.common.EntityStateEnum;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJerseyI18NErrorMappingSVImplTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(FleaJerseyI18NErrorMappingSVImplTest.class);

    private ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        LOGGER.debug("ApplicationContext={}", applicationContext);
    }

    @Test
    public void testInsertMapping() {
        IFleaJerseyI18nErrorMappingSV fleaJerseyI18nErrorMappingSV = (IFleaJerseyI18nErrorMappingSV) applicationContext.getBean("fleaJerseyI18nErrorMappingSVImpl");
        FleaJerseyI18nErrorMapping mapping = new FleaJerseyI18nErrorMapping();
        mapping.setResourceCode("jersey-filter-resource");
        mapping.setServiceCode("jersey-filter-service");
        mapping.setI18nCode("ERROR-JERSEY-FILTER0000000005");
        mapping.setErrorCode("100003");
        mapping.setReturnMess("请求业务报文不能为空");
        mapping.setState(EntityStateEnum.IN_USE.getValue());
        mapping.setCreateDate(DateUtils.getCurrentTime());
        try {
            fleaJerseyI18nErrorMappingSV.save(mapping);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testGetMappings() {
        IFleaJerseyI18nErrorMappingSV fleaJerseyI18nErrorMappingSV = (IFleaJerseyI18nErrorMappingSV) applicationContext.getBean("fleaJerseyI18nErrorMappingSVImpl");

        try {
            fleaJerseyI18nErrorMappingSV.getMappings("jersey-filter-resource", "jersey-filter-service");
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testGetMapping() {
        IFleaJerseyI18nErrorMappingSV fleaJerseyI18nErrorMappingSV = (IFleaJerseyI18nErrorMappingSV) applicationContext.getBean("fleaJerseyI18nErrorMappingSVImpl");

        try {
            fleaJerseyI18nErrorMappingSV.getMapping("jersey-filter-resource", "jersey-filter-service", "ERROR-JERSEY-FILTER0000000005");
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testFleaConfigDataSpringBean() {
        FleaConfigDataSpringBean bean = (FleaConfigDataSpringBean) applicationContext.getBean("fleaConfigDataSpringBean");
        try {
            bean.getMapping("jersey-filter-resource", "jersey-filter-service", "ERROR-JERSEY-FILTER0000000005");
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

}
