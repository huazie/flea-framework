package com.huazie.fleaframework.tools.code;

import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.MapUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.tools.common.ToolsConstants;

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

    private FleaCodeHelper() {
    }

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

    /**
     * 是否选中 "实体类使用Lombok"
     *
     * @param param 参数
     * @return true：选中 false：未选中
     */
    public static boolean isEntityClassWithLombokSelected(Map<String, Object> param) {
        return MapUtils.getBooleanValue(param, ToolsConstants.CodeConstants.IS_SELECTED_LOMBOK);
    }

    /**
     * 是否选中 "DAO层实现类"
     *
     * @param param 参数
     * @return true：选中 false：未选中
     */
    public static boolean isDAOImplSelected(Map<String, Object> param) {
        return MapUtils.getBooleanValue(param, ToolsConstants.CodeConstants.IS_SELECTED_DAO_IMPL);
    }

    /**
     * 是否选中 "DAO层接口类"
     *
     * @param param 参数
     * @return true：选中 false：未选中
     */
    public static boolean isDAOInterfaceSelected(Map<String, Object> param) {
        return MapUtils.getBooleanValue(param, ToolsConstants.CodeConstants.IS_SELECTED_DAO_INTERFACE);
    }

    /**
     * 是否选中 "实体类"
     *
     * @param param 参数
     * @return true：选中 false：未选中
     */
    public static boolean isEntitySelected(Map<String, Object> param) {
        return MapUtils.getBooleanValue(param, ToolsConstants.CodeConstants.IS_SELECTED_ENTITY);
    }

    /**
     * 是否选中 "SV层实现类"
     *
     * @param param 参数
     * @return true：选中 false：未选中
     */
    public static boolean isSVImplSelected(Map<String, Object> param) {
        return MapUtils.getBooleanValue(param, ToolsConstants.CodeConstants.IS_SELECTED_SV_IMPL);
    }

    /**
     * 是否选中 "SV层接口类"
     *
     * @param param 参数
     * @return true：选中 false：未选中
     */
    public static boolean isSVInterfaceSelected(Map<String, Object> param) {
        return MapUtils.getBooleanValue(param, ToolsConstants.CodeConstants.IS_SELECTED_SV_INTERFACE);
    }

    /**
     * 是否选中 "使用自定义事物注解"
     *
     * @param param 参数
     * @return true：选中 false：未选中
     */
    public static boolean isCustomTransactionalSelected(Map<String, Object> param) {
        return MapUtils.getBooleanValue(param, ToolsConstants.CodeConstants.IS_SELECTED_CUSTOM_TRANSACTIONAL);
    }
}
