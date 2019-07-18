package com.huazie.frame.common.util.xml;

import javax.xml.bind.JAXBContext;
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

    /**
     * <p> 将带有JAXB注解的pojo类转换为XML字符串 </p>
     *
     * @param t   pojo类对象
     * @param <T> pojo类的类型
     * @return XML字符串
     * @throws Exception
     * @since 1.0.0
     */
    public static <T> String toXml(T t, boolean isFormat) throws Exception {
        JAXBContext context = JAXBContext.newInstance(t.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, isFormat);
        StringWriter writer = new StringWriter();
        marshaller.marshal(t, writer);
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
