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
     * 获取 Http 请求的客户端 IPv6 地址;
     * <p>优先从代理头中获取 IPv6 地址，如果没有则尝试从 remoteAddr 获取
     *
     * @param request Http请求对象
     * @return IPv6 地址，如果未获取到则返回空字符串
     * @since 2.0.0
     */
    public static String getIpv6(HttpServletRequest request) {
        if (ObjectUtils.isEmpty(request)) {
            return "";
        }

        String ipv6 = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotBlank(ipv6) && !isIPv4(ipv6)) {
            // 如果是 IPv6 地址，取第一个
            if (ipv6.contains(",")) {
                ipv6 = ipv6.substring(0, ipv6.indexOf(",")).trim();
            }
            if (isIPv6(ipv6)) {
                LOGGER.debug1(new Object() {}, "IPv6 from X-Forwarded-For = {}", ipv6);
                return ipv6;
            }
        }

        ipv6 = request.getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(ipv6) && !isIPv4(ipv6)) {
            if (isIPv6(ipv6)) {
                LOGGER.debug1(new Object() {}, "IPv6 from X-Real-IP = {}", ipv6);
                return ipv6;
            }
        }

        ipv6 = request.getHeader("HTTP_CLIENT_IP");
        if (StringUtils.isNotBlank(ipv6) && !isIPv4(ipv6)) {
            if (isIPv6(ipv6)) {
                LOGGER.debug1(new Object() {}, "IPv6 from HTTP_CLIENT_IP = {}", ipv6);
                return ipv6;
            }
        }

        ipv6 = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (StringUtils.isNotBlank(ipv6) && !isIPv4(ipv6)) {
            if (ipv6.contains(",")) {
                ipv6 = ipv6.substring(0, ipv6.indexOf(",")).trim();
            }
            if (isIPv6(ipv6)) {
                LOGGER.debug1(new Object() {}, "IPv6 from HTTP_X_FORWARDED_FOR = {}", ipv6);
                return ipv6;
            }
        }

        // 尝试从 remoteAddr 获取
        ipv6 = request.getRemoteAddr();
        if (StringUtils.isNotBlank(ipv6) && isIPv6(ipv6)) {
            LOGGER.debug1(new Object() {}, "IPv6 from RemoteAddr = {}", ipv6);
            return ipv6;
        }

        LOGGER.debug1(new Object() {}, "IPv6 not found or not IPv6 format");
        return "";
    }

    /**
     * 判断是否为 IPv4 地址
     *
     * @param ip IP 地址字符串
     * @return true 是 IPv4，false 不是
     * @since 2.0.0
     */
    private static boolean isIPv4(String ip) {
        if (StringUtils.isBlank(ip)) {
            return false;
        }
        // IPv4 格式：xxx.xxx.xxx.xxx
        return ip.matches("\\d{1,3}(\\.\\d{1,3}){3}");
    }

    /**
     * 判断是否为 IPv6 地址
     *
     * @param ip IP 地址字符串
     * @return true 是 IPv6，false 不是
     * @since 2.0.0
     */
    private static boolean isIPv6(String ip) {
        if (StringUtils.isBlank(ip)) {
            return false;
        }
        // 简单的 IPv6 判断：包含冒号
        return ip.contains(":");
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
