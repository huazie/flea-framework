package com.huazie.fleaframework.db.jpa.aspect;

import com.huazie.fleaframework.common.FleaApplicationContext;
import com.huazie.fleaframework.common.FleaEntity;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.exception.FleaException;
import com.huazie.fleaframework.common.util.ArrayUtils;
import com.huazie.fleaframework.common.util.ExceptionUtils;
import com.huazie.fleaframework.common.util.FleaAspectUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.ReflectUtils;
import com.huazie.fleaframework.db.common.DBConstants;
import com.huazie.fleaframework.db.common.exception.DaoException;
import com.huazie.fleaframework.db.common.exception.FleaDBException;
import com.huazie.fleaframework.db.common.lib.pojo.SplitLib;
import com.huazie.fleaframework.db.common.table.pojo.SplitTable;
import com.huazie.fleaframework.db.jpa.persistence.FleaEntityManager;
import com.huazie.fleaframework.db.jpa.transaction.FleaTransactionTemplate;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import javax.persistence.EntityManager;
import java.lang.reflect.Method;

/**
 * Flea自定义事物切面
 *
 * @author huazie
 * @version 1.2.0
 * @since 1.2.0
 */
@Aspect
@Component
public class FleaTransactionalAspect {

    private static final String METHOD_NAME_GET_ENTITY_MANAGER = "getEntityManager";

    @Around("@annotation(com.huazie.fleaframework.db.jpa.transaction.FleaTransactional)")
    public Object invokeWithinTransaction(final ProceedingJoinPoint joinPoint) throws CommonException, FleaException, NoSuchMethodException {
        // 获取当前连接点上的方法
        Method method = FleaAspectUtils.getTargetMethod(joinPoint);
        // 获取当前连接点方法上的自定义Flea事物注解上对应的事物名称
        String transactionName = FleaEntityManager.getTransactionName(method);
        EntityManager entityManager = null;
        // 获取连接点方法签名上的参数列表
        Object[] args = joinPoint.getArgs();
        // 统一取最后一个参数，即实体类对象实例
        if (ArrayUtils.isNotEmpty(args) && args[args.length - 1] instanceof FleaEntity) {
            FleaEntity fleaEntity = (FleaEntity) args[args.length - 1];
            Object daoImplObj = joinPoint.getTarget();
            // 获取实体管理器
            entityManager = (EntityManager) ReflectUtils.invoke(daoImplObj, METHOD_NAME_GET_ENTITY_MANAGER, fleaEntity, Object.class);
            // 获取分表信息
            SplitTable splitTable = fleaEntity.get(DBConstants.LibTableSplitConstants.SPLIT_TABLE, SplitTable.class);
            // 获取分库信息
            SplitLib splitLib = fleaEntity.get(DBConstants.LibTableSplitConstants.SPLIT_LIB, SplitLib.class);
            if (ObjectUtils.isNotEmpty(splitTable)) {
                splitLib = splitTable.getSplitLib();
            }
            if (ObjectUtils.isNotEmpty(splitLib) && splitLib.isExistSplitLib()) {
                transactionName = splitLib.getSplitLibTxName();
            }
            // 不是分表场景
            if (ObjectUtils.isEmpty(splitTable) || !splitTable.isExistSplitTable()) {
                entityManager = null;
            }
        }

        // 根据事物名，获取配置的事物管理者
        PlatformTransactionManager transactionManager = (PlatformTransactionManager) FleaApplicationContext.getBean(transactionName);
        // 事物名【{0}】非法，请检查！
        ObjectUtils.checkEmpty(transactionManager, DaoException.class, "ERROR-DB-DAO0000000015", transactionName);
        // 新建事物模板对象，用于处理事务生命周期和可能的异常
        FleaTransactionTemplate transactionTemplate = new FleaTransactionTemplate(transactionManager, entityManager);
        return transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    return joinPoint.proceed();
                }  catch (Throwable throwable) {
                    ExceptionUtils.throwFleaException(FleaDBException.class, "Proceed with the next advice or target method invocation occurs exception : \n", throwable);
                }
                return null;
            }
        });
    }

}
