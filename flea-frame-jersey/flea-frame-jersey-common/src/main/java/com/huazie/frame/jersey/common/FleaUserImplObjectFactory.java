package com.huazie.frame.jersey.common;

import com.huazie.frame.common.IFleaUser;
import com.huazie.frame.common.object.DefaultFleaObject;
import com.huazie.frame.common.object.FleaObject;
import com.huazie.frame.common.object.FleaObjectFactory;

/**
 * <p> Flea用户实现对象工厂类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaUserImplObjectFactory implements FleaObjectFactory<IFleaUser> {

    @Override
    public FleaObject<IFleaUser> newObject() {
        IFleaUser fleaUser = new FleaUserImpl();
        return new DefaultFleaObject<>(fleaUser);
    }

    @Override
    public void initObject() {
        // 空实现
    }
}
