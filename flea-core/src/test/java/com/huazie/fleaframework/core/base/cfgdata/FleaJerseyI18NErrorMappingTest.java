package com.huazie.fleaframework.core.base.cfgdata;

import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.core.base.cfgdata.bean.FleaConfigDataSpringBean;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyI18nErrorMapping;
import com.huazie.fleaframework.core.base.cfgdata.service.interfaces.IFleaJerseyI18nErrorMappingSV;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * FleaJersey国际码和错误码映射单元测试类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FleaJerseyI18NErrorMappingTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaJerseyI18NErrorMappingTest.class);

    @Autowired
    @Qualifier("i18nErrorMappingSV")
    private IFleaJerseyI18nErrorMappingSV fleaJerseyI18nErrorMappingSV;

    @Autowired
    private FleaConfigDataSpringBean bean;

    @Test
    public void testInsertMappingOne() throws CommonException {
        FleaJerseyI18nErrorMapping mapping = new FleaJerseyI18nErrorMapping();
        mapping.setResourceCode("jersey-filter-resource");
        mapping.setServiceCode("jersey-filter-service");
        mapping.setI18nCode("ERROR-JERSEY-FILTER0000000003");
        mapping.setErrorCode("100001");
        mapping.setReturnMess("请求报文不能为空");
        mapping.setState(EntityStateEnum.IN_USE.getState());
        mapping.setCreateDate(DateUtils.getCurrentTime());
        fleaJerseyI18nErrorMappingSV.save(mapping);
    }

    @Test
    public void testInsertMappingTwo() throws CommonException {
        FleaJerseyI18nErrorMapping mapping = new FleaJerseyI18nErrorMapping();
        mapping.setResourceCode("jersey-filter-resource");
        mapping.setServiceCode("jersey-filter-service");
        mapping.setI18nCode("ERROR-JERSEY-FILTER0000000004");
        mapping.setErrorCode("100002");
        mapping.setReturnMess("请求公共报文不能为空");
        mapping.setState(EntityStateEnum.IN_USE.getState());
        mapping.setCreateDate(DateUtils.getCurrentTime());
        fleaJerseyI18nErrorMappingSV.save(mapping);
    }

    @Test
    public void testInsertMappingThree() throws CommonException {
        FleaJerseyI18nErrorMapping mapping = new FleaJerseyI18nErrorMapping();
        mapping.setResourceCode("jersey-filter-resource");
        mapping.setServiceCode("jersey-filter-service");
        mapping.setI18nCode("ERROR-JERSEY-FILTER0000000005");
        mapping.setErrorCode("100003");
        mapping.setReturnMess("请求业务报文不能为空");
        mapping.setState(EntityStateEnum.IN_USE.getState());
        mapping.setCreateDate(DateUtils.getCurrentTime());
        fleaJerseyI18nErrorMappingSV.save(mapping);
    }

    @Test
    public void testInsertMappingFour() throws CommonException {
        FleaJerseyI18nErrorMapping mapping = new FleaJerseyI18nErrorMapping();
        mapping.setResourceCode("jersey-filter-resource");
        mapping.setServiceCode("jersey-filter-service");
        mapping.setI18nCode("ERROR-JERSEY-FILTER0000000006");
        mapping.setErrorCode("100004");
        mapping.setReturnMess("请求公共报文入参【{0}】不能为空");
        mapping.setState(EntityStateEnum.IN_USE.getState());
        mapping.setCreateDate(DateUtils.getCurrentTime());
        fleaJerseyI18nErrorMappingSV.save(mapping);
    }

    @Test
    public void testInsertMappingFive() throws CommonException {
        FleaJerseyI18nErrorMapping mapping = new FleaJerseyI18nErrorMapping();
        mapping.setResourceCode("jersey-filter-resource");
        mapping.setServiceCode("jersey-filter-service");
        mapping.setI18nCode("ERROR-JERSEY-FILTER0000000008");
        mapping.setErrorCode("100005");
        mapping.setReturnMess("未能找到指定资源服务配置数据【service_code = {0} , resource_code = {1}】");
        mapping.setState(EntityStateEnum.IN_USE.getState());
        mapping.setCreateDate(DateUtils.getCurrentTime());
        fleaJerseyI18nErrorMappingSV.save(mapping);
    }

    @Test
    public void testInsertMappingSix() throws CommonException {
        FleaJerseyI18nErrorMapping mapping = new FleaJerseyI18nErrorMapping();
        mapping.setResourceCode("jersey-filter-resource");
        mapping.setServiceCode("jersey-filter-service");
        mapping.setI18nCode("ERROR-JERSEY-FILTER0000000009");
        mapping.setErrorCode("100006");
        mapping.setReturnMess("资源【{0}】下的服务【{1}】请求异常：配置的出参【{2}】与服务方法【{3}】出参【{4}】类型不一致");
        mapping.setState(EntityStateEnum.IN_USE.getState());
        mapping.setCreateDate(DateUtils.getCurrentTime());
        fleaJerseyI18nErrorMappingSV.save(mapping);
    }

    @Test
    public void testInsertMappingSeven() throws CommonException {
        FleaJerseyI18nErrorMapping mapping = new FleaJerseyI18nErrorMapping();
        mapping.setResourceCode("FLEA_SERVICE_DOWNLOAD_AUTH");
        mapping.setServiceCode("download");
        mapping.setI18nCode("ERROR-SERVICE0000000001");
        mapping.setErrorCode("110001");
        mapping.setReturnMess("入参【{0}】不能为空");
        mapping.setState(EntityStateEnum.IN_USE.getState());
        mapping.setCreateDate(DateUtils.getCurrentTime());
        fleaJerseyI18nErrorMappingSV.save(mapping);
    }

    @Test
    public void testGetMappings() throws CommonException {
        fleaJerseyI18nErrorMappingSV.getMappings("jersey-filter-resource", "jersey-filter-service");
    }

    @Test
    public void testGetMapping() throws CommonException {
        fleaJerseyI18nErrorMappingSV.getMapping("jersey-filter-resource", "jersey-filter-service", "ERROR-JERSEY-FILTER0000000005");
    }

    @Test
    public void testFleaConfigDataSpringBean() throws CommonException {
        bean.getMapping("jersey-filter-resource", "jersey-filter-service", "ERROR-JERSEY-FILTER0000000003");
    }

}
