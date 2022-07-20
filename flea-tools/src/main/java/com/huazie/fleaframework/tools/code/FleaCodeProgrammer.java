package com.huazie.fleaframework.tools.code;

import com.huazie.fleaframework.tools.code.impl.FleaDaoImplBuilder;
import com.huazie.fleaframework.tools.code.impl.FleaDaoInterfacesBuilder;
import com.huazie.fleaframework.tools.code.impl.FleaEntityBuilder;
import com.huazie.fleaframework.tools.code.impl.FleaEntityWithLombokBuilder;
import com.huazie.fleaframework.tools.code.impl.FleaPersistenceUnitDaoImplBuilder;
import com.huazie.fleaframework.tools.code.impl.FleaSVImplBuilder;
import com.huazie.fleaframework.tools.code.impl.FleaSVInterfacesBuilder;
import com.huazie.fleaframework.tools.code.interfaces.IFleaCodeBuilder;

import java.util.Map;

/**
 * <p> Flea代码编写程序员 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaCodeProgrammer {

    /**
     * <p> 指导代码编写 </p>
     *
     * @param param 代码模板参数
     * @since 1.0.0
     */
    public static void code(Map<String, Object> param) {

        // 编写持久化单元DAO层实现类
        IFleaCodeBuilder codeBuilder = new FleaPersistenceUnitDaoImplBuilder();
        codeBuilder.build(param);

        // 编写实体类
        if (FleaCodeHelper.isEntityClassWithLombok(param)) {
            codeBuilder = new FleaEntityWithLombokBuilder();
        } else {
            codeBuilder = new FleaEntityBuilder();
        }
        codeBuilder.build(param);

        // 编写 DAO层接口
        codeBuilder = new FleaDaoInterfacesBuilder();
        codeBuilder.build(param);

        // 编写 DAO层实现
        codeBuilder = new FleaDaoImplBuilder();
        codeBuilder.build(param);

        // 编写 SV层接口
        codeBuilder = new FleaSVInterfacesBuilder();
        codeBuilder.build(param);

        // 编写 SV层实现
        codeBuilder = new FleaSVImplBuilder();
        codeBuilder.build(param);
    }

    /**
     * <p> 批量清除Flea代码 </p>
     *
     * @param param 代码模板参数
     * @since 1.0.0
     */
    public static void clean(Map<String, Object> param) {
        // 销毁持久化单元DAO层实现类 (只销毁新增持久化单元，利用现有持久化单元的不销毁 )
        IFleaCodeBuilder codeBuilder = new FleaPersistenceUnitDaoImplBuilder();
        codeBuilder.destroy(param);

        // 销毁实体类
        if (FleaCodeHelper.isEntityClassWithLombok(param)) {
            codeBuilder = new FleaEntityWithLombokBuilder();
        } else {
            codeBuilder = new FleaEntityBuilder();
        }
        codeBuilder.destroy(param);

        // 销毁 DAO层接口
        codeBuilder = new FleaDaoInterfacesBuilder();
        codeBuilder.destroy(param);

        // 销毁 DAO层实现
        codeBuilder = new FleaDaoImplBuilder();
        codeBuilder.destroy(param);

        // 销毁 SV层接口
        codeBuilder = new FleaSVInterfacesBuilder();
        codeBuilder.destroy(param);

        // 销毁 SV层实现
        codeBuilder = new FleaSVImplBuilder();
        codeBuilder.destroy(param);
    }

}
