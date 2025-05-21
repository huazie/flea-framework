package com.huazie.fleaframework.common.util;

import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.json.GsonUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Http工具类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class HttpUtils {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(HttpUtils.class);

    private HttpUtils() {
    }

    /**
     * 获取 Http请求的客户端IP地址;
     * <p> X-Forwarded-For: 简称XFF头，它代表客户端，也就是HTTP的请求端真实的IP，
     * 只有在通过了HTTP代理或者负载均衡服务器时才会添加该项
     *
     * @param request Http请求对象
     * @return IP地址
     * @since 1.0.0
     */
    public static String getIp(HttpServletRequest request) {
        if (ObjectUtils.isEmpty(request)) {
            return "0.0.0.0";
        }

        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        //如果是多级代理，那么取第一个ip为客户ip
        if (StringUtils.isBlank(ip) && ip.contains(",")) {
            ip = ip.substring(ip.lastIndexOf(",") + 1).trim();
        }

        if (StringUtils.isBlank(ip) || "null".equals(ip)) {
            ip = "0.0.0.0";
        }

        LOGGER.debug1(new Object() {}, "IP ={}", ip);

        return ip;
    }

    /**
     * 通过TaoBao的接口获取ip所在地理地址
     *
     * @param ip ip地址
     * @return 较为精确的地理地址
     * @since 1.0.0
     */
    @SuppressWarnings(value = "unchecked")
    public static String getAddressByTaoBao(String ip) {
        StringBuilder sb = new StringBuilder();
        try {
            String ipUrl = URLHelper.getTaobaoIPURL();
            String urlStr = ipUrl + ip;
            String retJson = get(urlStr);

            Map<String, Object> map = GsonUtils.toMap(retJson);

            if (ObjectUtils.isEmpty(map)) {
                return "";
            }

            Map<String, Object> dataMap = (Map<String, Object>) map.get("data");

            if (ObjectUtils.isEmpty(dataMap)) {
                return "";
            }

            Object obj = new Object() {};
            LOGGER.debug1(obj, "Map = {}", map);
            LOGGER.debug1(obj, "Data = {}", dataMap);

            sb.append(dataMap.get(CommonConstants.IPAddressConstants.COUNTRY));
            sb.append(dataMap.get(CommonConstants.IPAddressConstants.REGION));
            sb.append(dataMap.get(CommonConstants.IPAddressConstants.CITY));
            Object isp = dataMap.get(CommonConstants.IPAddressConstants.ISP);
            if (ObjectUtils.isNotEmpty(isp)) {
                sb.append("(").append(dataMap.get(CommonConstants.IPAddressConstants.ISP)).append(")");
            }

            LOGGER.debug1(obj, "Address = {}", sb);

        } catch (Exception e) {
            LOGGER.error1(new Object() {},"Exception = ", e);
        }

        return sb.toString();
    }

    /**
     * Http Get请求
     *
     * @param urlStr url地址
     * @return get请求返回报文
     * @since 1.0.0
     */
    public static String get(String urlStr) {
        StringBuilder sBuilder = new StringBuilder();
        BufferedReader reader = null;
        try {
            URL url = new URL(urlStr);
            URLConnection conn = url.openConnection();
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8.displayName()));
            String line;

            while ((line = reader.readLine()) != null) {
                sBuilder.append(line);
            }
            LOGGER.debug1(new Object() {}, "Output={}", sBuilder);
        } catch (IOException e) {
            LOGGER.error1(new Object() {},"IOException={}", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    LOGGER.error1(new Object() {},"IOException={}", e);
                }
            }
        }
        return sBuilder.toString();
    }

}
