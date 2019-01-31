package com.huazie.frame.common.util;

import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.util.json.FastJsonUtils;
import com.huazie.frame.common.util.json.GsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * <p> Http工具类 </p>
 *
 * @author huazie
 * @version v1.0.0
 * @since 1.0.0
 */
public class HttpUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);

    private final static String TAOBAO_IP_URL = "http://ip.taobao.com/service/getIpInfo.php?ip=";

    private final static String SINA_IP_URL = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=";

    /**
     * <p> 获取Http请求的客户端IP地址 </p>
     * <p>X-Forwarded-For: 简称XFF头，它代表客户端，也就是HTTP的请求端真实的IP，只有在通过了HTTP代理或者负载均衡服务器时才会添加该项</p>
     *
     * @param request Http请求对象
     * @return ip地址
     * @since 1.0.0
     */
    public static String getIp(HttpServletRequest request) throws Exception {

        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isNotBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isNotBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (StringUtils.isNotBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isNotBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        //如果是多级代理，那么取第一个ip为客户ip
        if (StringUtils.isNotBlank(ip) && ip.indexOf(",") != -1) {
            ip = ip.substring(ip.lastIndexOf(",") + 1).trim();
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("HttpUtils#getIp(HttpServletRequest) IP ={}", ip);
        }

        return ip;
    }

    /**
     * <p> 通过TaoBao的接口获取ip的地理地址 </p>
     *
     * @param ip ip地址
     * @return 较为精确的地理地址
     * @since 1.0.0
     */
    public static String getAddressByTaoBao(String ip) {
        StringBuilder sb = new StringBuilder();
        try {
            String urlStr = TAOBAO_IP_URL + ip;
            String retJson = getAddress(urlStr);

            Map<String, Object> map = GsonUtils.toMap(retJson);

            if (ObjectUtils.isEmpty(map)) {
                return "";
            }

            Map<String, Object> dataMap = (Map<String, Object>) map.get("data");

            if (ObjectUtils.isEmpty(dataMap)) {
                return "";
            }

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("HttpUtils#getAddressByTaoBao(String) Map = {}", map);
                LOGGER.debug("HttpUtils#getAddressByTaoBao(String) Data = {}", dataMap);
            }

            sb.append(dataMap.get(CommonConstants.IPAddressConstants.COUNTRY));
            sb.append(dataMap.get(CommonConstants.IPAddressConstants.REGION));
            sb.append(dataMap.get(CommonConstants.IPAddressConstants.CITY));
            Object isp = dataMap.get(CommonConstants.IPAddressConstants.ISP);
            if (!ObjectUtils.isEmpty(isp)) {
                sb.append("(" + dataMap.get(CommonConstants.IPAddressConstants.ISP) + ")");
            }

        } catch (Exception e) {
            if(LOGGER.isErrorEnabled()){
                LOGGER.error("HttpUtils##getAddressByTaoBao(String) Exception = ", e);
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("HttpUtils##getAddressByTaoBao(String) Address = {}", sb);
        }

        return sb.toString();
    }

    /**
     * @param ip ip地址
     * @return
     * @Description 通过Sina的接口获取ip的精确地址
     * @version v1.0.0
     * @date 2017年3月2日
     */
    public static String getAddressBySina(String ip) {
        StringBuilder sb = new StringBuilder();
        try {
            String urlStr = HttpUtils.SINA_IP_URL + ip;
            String retJson = getAddress(urlStr);

            Map<String, Object> map = FastJsonUtils.toMap(retJson);

            if (ObjectUtils.isEmpty(map)) {
                return "";
            }

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("HttpUtils##getAddressBySina() Map={}", map);
            }

            sb.append(map.get(CommonConstants.IPAddressConstants.COUNTRY));
            sb.append(map.get(CommonConstants.IPAddressConstants.PROVINCE) + "\u7701");
            sb.append(map.get(CommonConstants.IPAddressConstants.CITY));

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("HttpUtils##getAddressBySina() Address={}", sb);
            }
        } catch (Exception e) {
            LOGGER.error("HttpUtils##getAddressBySina() Exception=", e);
        }

        return sb.toString();
    }

    /**
     * @param urlStr 获取ip精确地址的url
     * @return
     * @Description 获取ip精确地址
     * @version v1.0.0
     * @date 2017年3月2日
     */
    private static String getAddress(String urlStr) {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlStr);
            URLConnection conn = url.openConnection();
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GBK"));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            if (reader != null) {
                reader.close();
                reader = null;
            }
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("HttpUtils##getAddress() Address={}", sb.toString());
            }
            return sb.toString();
        } catch (IOException e) {
            LOGGER.error("HttpUtils##getAddress() IOException={}", e);
        }
        return "";
    }

}
