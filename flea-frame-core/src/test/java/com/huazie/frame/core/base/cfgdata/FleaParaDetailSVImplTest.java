package com.huazie.frame.core.base.cfgdata;

import com.huazie.frame.core.base.cfgdata.service.interfaces.IFleaParaDetailSV;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FleaParaDetailSVImplTest {

    private ApplicationContext applicationContext;

    @Before
    public void init(){
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void getParaDetails() {
        IFleaParaDetailSV sv = (IFleaParaDetailSV) applicationContext.getBean("fleaParaDetailSVImpl");
        try {
            sv.getParaDetails("FLEA_RES_STATE","");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getParaDetail() {
        IFleaParaDetailSV sv = (IFleaParaDetailSV) applicationContext.getBean("fleaParaDetailSVImpl");
        try {
            sv.getParaDetail("FLEAER_CERT_TYPE","1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}