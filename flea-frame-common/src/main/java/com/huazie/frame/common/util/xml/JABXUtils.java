package com.huazie.frame.common.util.xml;

import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * <p> Java Architecture for XML Binding（JAXB） 工具类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class JABXUtils {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(JABXUtils.class);

    /**
     * <p> 将带有JAXB注解的pojo类转换为XML字符串 </p>
     *
     * @param t   pojo类对象
     * @param <T> pojo类的类型
     * @return XML字符串
     * @throws Exception
     * @since 1.0.0
     */
    public static <T> String toXml(T t, boolean isFormat) {
        StringWriter writer = new StringWriter();
        try {
            JAXBContext context = JAXBContext.newInstance(t.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, isFormat);
            marshaller.marshal(t, writer);
        } catch (JAXBException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error1(new Object() {}, "实体转XML，出现异常：\n", e);
            }
        }
        return writer.toString();
    }

    /**
     * <p> 将XML字符串转换成对应的pojo类 </p>
     *
     * @param xml   XML字符串
     * @param clazz pojo类的Clazz类型
     * @param <T>   pojo类的类型
     * @return pojo类
     * @throws Exception
     */
    public static <T> T fromXml(String xml, Class<T> clazz) throws Exception {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object obj = unmarshaller.unmarshal(new StringReader(xml));
        T t = null;
        if (clazz.isInstance(obj)) {
            t = clazz.cast(obj);
        }
        return t;
    }

}
