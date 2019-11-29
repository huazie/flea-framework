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

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaJerseyI18NErrorMappingSVImplTest.class);

    private ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        LOGGER.debug("ApplicationContext={}", applicationContext);
    }

    @Test
    public void testInsertMappingOne() {
        IFleaJerseyI18nErrorMappingSV fleaJerseyI18nErrorMappingSV = (IFleaJerseyI18nErrorMappingSV) applicationContext.getBean("i18nErrorMappingSV");
        FleaJerseyI18nErrorMapping mapping = new FleaJerseyI18nErrorMapping();
        mapping.setResourceCode("jersey-filter-resource");
        mapping.setServiceCode("jersey-filter-service");
        mapping.setI18nCode("ERROR-JERSEY-FILTER0000000003");
        mapping.setErrorCode("100001");
        mapping.setReturnMess("请求报文不能为空");
        mapping.setState(EntityStateEnum.IN_USE.getValue());
        mapping.setCreateDate(DateUtils.getCurrentTime());
        try {
            fleaJerseyI18nErrorMappingSV.save(mapping);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testInsertMappingTwo() {
        IFleaJerseyI18nErrorMappingSV fleaJerseyI18nErrorMappingSV = (IFleaJerseyI18nErrorMappingSV) applicationContext.getBean("i18nErrorMappingSV");
        FleaJerseyI18nErrorMapping mapping = new FleaJerseyI18nErrorMapping();
        mapping.setResourceCode("jersey-filter-resource");
        mapping.setServiceCode("jersey-filter-service");
        mapping.setI18nCode("ERROR-JERSEY-FILTER0000000004");
        mapping.setErrorCode("100002");
        mapping.setReturnMess("请求公共报文不能为空");
        mapping.setState(EntityStateEnum.IN_USE.getValue());
        mapping.setCreateDate(DateUtils.getCurrentTime());
        try {
            fleaJerseyI18nErrorMappingSV.save(mapping);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testInsertMappingThree() {
        IFleaJerseyI18nErrorMappingSV fleaJerseyI18nErrorMappingSV = (IFleaJerseyI18nErrorMappingSV) applicationContext.getBean("i18nErrorMappingSV");
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
    public void testInsertMappingFour() {
        IFleaJerseyI18nErrorMappingSV fleaJerseyI18nErrorMappingSV = (IFleaJerseyI18nErrorMappingSV) applicationContext.getBean("i18nErrorMappingSV");
        FleaJerseyI18nErrorMapping mapping = new FleaJerseyI18nErrorMapping();
        mapping.setResourceCode("jersey-filter-resource");
        mapping.setServiceCode("jersey-filter-service");
        mapping.setI18nCode("ERROR-JERSEY-FILTER0000000006");
        mapping.setErrorCode("100004");
        mapping.setReturnMess("请求公共报文入参【{0}】不能为空");
        mapping.setState(EntityStateEnum.IN_USE.getValue());
        mapping.setCreateDate(DateUtils.getCurrentTime());
        try {
            fleaJerseyI18nErrorMappingSV.save(mapping);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testInsertMappingFive() {
        IFleaJerseyI18nErrorMappingSV fleaJerseyI18nErrorMappingSV = (IFleaJerseyI18nErrorMappingSV) applicationContext.getBean("i18nErrorMappingSV");
        FleaJerseyI18nErrorMapping mapping = new FleaJerseyI18nErrorMapping();
        mapping.setResourceCode("jersey-filter-resource");
        mapping.setServiceCode("jersey-filter-service");
        mapping.setI18nCode("ERROR-JERSEY-FILTER0000000008");
        mapping.setErrorCode("100005");
        mapping.setReturnMess("未能找到指定资源服务配置数据【service_code = {0} , resource_code = {1}】");
        mapping.setState(EntityStateEnum.IN_USE.getValue());
        mapping.setCreateDate(DateUtils.getCurrentTime());
        try {
            fleaJerseyI18nErrorMappingSV.save(mapping);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testInsertMappingSix() {
        IFleaJerseyI18nErrorMappingSV fleaJerseyI18nErrorMappingSV = (IFleaJerseyI18nErrorMappingSV) applicationContext.getBean("i18nErrorMappingSV");
        FleaJerseyI18nErrorMapping mapping = new FleaJerseyI18nErrorMapping();
        mapping.setResourceCode("jersey-filter-resource");
        mapping.setServiceCode("jersey-filter-service");
        mapping.setI18nCode("ERROR-JERSEY-FILTER0000000009");
        mapping.setErrorCode("100006");
        mapping.setReturnMess("资源【{0}】下的服务【{1}】请求异常：配置的出参【{2}】与服务方法【{3}】出参【{4}】类型不一致");
        mapping.setState(EntityStateEnum.IN_USE.getValue());
        mapping.setCreateDate(DateUtils.getCurrentTime());
        try {
            fleaJerseyI18nErrorMappingSV.save(mapping);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testInsertMappingSeven() {
        IFleaJerseyI18nErrorMappingSV fleaJerseyI18nErrorMappingSV = (IFleaJerseyI18nErrorMappingSV) applicationContext.getBean("i18nErrorMappingSV");
        FleaJerseyI18nErrorMapping mapping = new FleaJerseyI18nErrorMapping();
        mapping.setResourceCode("FLEA_SERVICE_DOWNLOAD_AUTH");
        mapping.setServiceCode("download");
        mapping.setI18nCode("ERROR-SERVICE0000000001");
        mapping.setErrorCode("110001");
        mapping.setReturnMess("入参【{0}】不能为空");
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
        IFleaJerseyI18nErrorMappingSV fleaJerseyI18nErrorMappingSV = (IFleaJerseyI18nErrorMappingSV) applicationContext.getBean("i18nErrorMappingSV");

        try {
            fleaJerseyI18nErrorMappingSV.getMappings("jersey-filter-resource", "jersey-filter-service");
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testGetMapping() {
        IFleaJerseyI18nErrorMappingSV fleaJerseyI18nErrorMappingSV = (IFleaJerseyI18nErrorMappingSV) applicationContext.getBean("i18nErrorMappingSV");

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
