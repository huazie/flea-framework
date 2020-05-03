package com.huazie.frame.core.request.config;

import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.MapUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p> flea-request.xml 配置 {@code <flea-url> </flea-url>} 节点 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaUrl {

    public static final String UNCHECK_URL = "UNCHECK_URL";
    public static final String CHECK_URL = "CHECK_URL";

    public static final String REDIRECT_URL_LOGIN_KEY = "login"; // 重定向登录页面请求配置KEY
    public static final String REDIRECT_URL_ERROR_KEY = "error"; // 重定向错误页面请求配置KEY

    public static final String URL_PREFIX_BUSINESS_KEY = "business"; // 业务请求URL前缀配置

    private RedirectUrl redirectUrl; // 重定向URL

    private List<String> unCheckUrls = new ArrayList<String>(); // 不需要校验的URL列表

    private List<String> checkUrls = new ArrayList<String>(); // 需要校验的URL列表

    private UrlPrefix urlPrefix; // URL前缀

    private String urlIllegalChar; // URL非法字符

    public RedirectUrl getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(RedirectUrl redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    /**
     * <p> 获取指定属性key的重定向URL </p>
     *
     * @param key 属性key
     * @return 重定向URL
     * @since 1.0.0
     */
    public Property getRedirectUrlProperty(String key) {
        return getProperty(redirectUrl.getRedirectUrlList(), key);
    }

    public List<String> getUnCheckUrls() {
        return unCheckUrls;
    }

    /**
     * <p> 添加一个不需要校验的URL </p>
     *
     * @param unCheckUrl 不需要校验的URL
     * @since 1.0.0
     */
    public void addUnCheckUrl(String unCheckUrl) {
        if (!unCheckUrls.contains(unCheckUrl)) {
            unCheckUrls.add(unCheckUrl);
        }
    }

    public List<String> getCheckUrls() {
        return checkUrls;
    }

    /**
     * <p> 添加一个需要校验的URL </p>
     *
     * @param checkUrl 需要校验的URL
     * @since 1.0.0
     */
    public void addCheckUrl(String checkUrl) {
        if (!checkUrls.contains(checkUrl)) {
            checkUrls.add(checkUrl);
        }
    }

    /**
     * <p> 判断当前URL是否包含指定类型的URL </p>
     *
     * @param url  待判断的URL字符串
     * @param type 指定类型(UNCHECK_URL: 不需要校验的URL CHECK_URL: 需要校验的URL)
     * @return true : 包含, false : 不包含
     * @since 1.0.0
     */
    public boolean contains(String url, String type) {
        if (UNCHECK_URL.equals(type)) {
            return contains(url, unCheckUrls);
        } else if (CHECK_URL.equals(type)) {
            return contains(url, checkUrls);
        } else {
            throw new RuntimeException("类型非法【type=" + type + "】");
        }
    }

    /**
     * <p> 判断当前URL字符串是否包含指定URL列表中的URL </p>
     *
     * @param url      待判断的URL字符串
     * @param mUrlList 指定的URL列表
     * @return true : 包含, false : 不包含
     * @since 1.0.0
     */
    private boolean contains(String url, List<String> mUrlList) {
        boolean isExist = false;
        if (CollectionUtils.isNotEmpty(mUrlList)) {
            for (String mUrl : mUrlList) {
                if (StringUtils.isNotBlank(url) && url.contains(mUrl)) {
                    isExist = true;
                    break;
                }
            }
        }
        return isExist;
    }

    public UrlPrefix getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(UrlPrefix urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    /**
     * <p> URL是否包含指定类型的URL前缀</p>
     *
     * @param url  URL字符串
     * @param type 指定类型的URL前缀
     * @return true : 包含, false : 不包含
     * @since 1.0.0
     */
    public boolean containsUrlPrefix(String url, String type) {
        boolean isContains = false;
        Property urlPrefixProperty = getUrlPrefixProperty(type);
        if (ObjectUtils.isNotEmpty(urlPrefixProperty) && StringUtils.isNotBlank(url)) {
            isContains = url.contains(urlPrefixProperty.getValue());
        }
        return isContains;
    }

    /**
     * <p> 获取指定属性key对应的URL前缀 </p>
     *
     * @param key 属性key
     * @return URL前缀
     * @since 1.0.0
     */
    private Property getUrlPrefixProperty(String key) {
        return getProperty(urlPrefix.getUrlPrefixList(), key);
    }

    public String getUrlIllegalChar() {
        return urlIllegalChar;
    }

    public void setUrlIllegalChar(String urlIllegalChar) {
        this.urlIllegalChar = urlIllegalChar;
    }

    /**
     * <p> 根据属性Key获取指定的属性 </p>
     *
     * @param propertyList 属性列表
     * @param key          属性key
     * @return 属性
     * @since 1.0.0
     */
    private Property getProperty(List<Property> propertyList, String key) {
        Property property = null;
        Map<String, Property> propertyMap = toPropertyMap(propertyList);
        if (MapUtils.isNotEmpty(propertyMap)) {
            property = propertyMap.get(key);
        }
        return property;
    }

    /**
     * <p> 获取属性列表Map， 便于根据属性key查找 </p>
     *
     * @return 属性列表Map
     * @since 1.0.0
     */
    private Map<String, Property> toPropertyMap(List<Property> propertyList) {
        Map<String, Property> propertyMap = new HashMap<String, Property>();
        Iterator<Property> propertyIt = propertyList.iterator();
        while (propertyIt.hasNext()) {
            Property property = propertyIt.next();
            if (ObjectUtils.isNotEmpty(property)) {
                propertyMap.put(property.getKey(), property);
            }
        }
        return propertyMap;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
