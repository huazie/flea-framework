package com.huazie.frame.tools.code;

import com.huazie.frame.tools.code.impl.FleaDaoImplBuilder;
import com.huazie.frame.tools.code.impl.FleaDaoInterfacesBuilder;
import com.huazie.frame.tools.code.impl.FleaEntityBuilder;
import com.huazie.frame.tools.code.impl.FleaSVImplBuilder;
import com.huazie.frame.tools.code.impl.FleaSVInterfacesBuilder;
import com.huazie.frame.tools.code.interfaces.IFleaCodeBuilder;

import java.util.Map;

/**
 * <p> Flea代码编写程序员 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaCodeProgramer {

    /**
     * <p> 指导代码编写 </p>
     *
     * @throws Exception
     */
    public static void code(Map<String, Object> param) throws Exception {

        // 编写实体类
        IFleaCodeBuilder codeBuilder = new FleaEntityBuilder();
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

}
