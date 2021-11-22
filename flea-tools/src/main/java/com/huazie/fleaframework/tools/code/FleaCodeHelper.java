package com.huazie.fleaframework.tools.code;

import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.MapUtils;
import com.huazie.fleaframework.common.util.StringUtils;

import java.util.Map;
import java.util.Set;

/**
 * <p> Flea代码工具类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaCodeHelper {

    /**
     * <p> 代码模板内容替换 </p>
     *
     * @param content 模板内容
     * @param param   替换参数Map
     * @return 代码文件内容
     * @since 1.0.0
     */
    public static String convert(String content, Map<String, Object> param) {
        String realContent = content;
        if (MapUtils.isEmpty(param)) {
            return realContent;
        }

        Set<String> paramKeySet = param.keySet();
        if (CollectionUtils.isNotEmpty(paramKeySet)) {
            for (String paramKey : paramKeySet) {
                String placeholder = CommonConstants.SymbolConstants.PERCENT + paramKey + CommonConstants.SymbolConstants.PERCENT;
                if (realContent.contains(placeholder)) {
                    String paramValue = StringUtils.valueOf(param.get(paramKey));
                    realContent = realContent.replaceAll(placeholder, paramValue);
                }
            }
        }

        return realContent;
    }

}
