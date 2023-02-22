package com.huazie.fleaframework.auth.base.function.service.interfaces;

import com.huazie.fleaframework.auth.base.function.entity.FleaElement;
import com.huazie.fleaframework.auth.common.pojo.function.element.FleaElementPOJO;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;

/**
 * Flea元素SV层接口定义
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public interface IFleaElementSV extends IAbstractFleaJPASV<FleaElement> {

    /**
     * 根据元素编号，查询有效的Flea元素数据
     *
     * @param elementId 元素编号
     * @return Flea元素数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaElement queryValidElement(Long elementId) throws CommonException;

    /**
     * 根据元素编码、元素名称、元素类型和元素内容，查询有效的Flea元素数据集
     *
     * @param elementCode    元素编码
     * @param elementName    元素名称
     * @param elementType    元素类型
     * @param elementContent 元素内容
     * @return Flea元素数据集
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaElement> queryValidElements(String elementCode, String elementName, Integer elementType, String elementContent) throws CommonException;

    /**
     * 查询所有有效的Flea元素数据集
     *
     * @return Flea元素数据集
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaElement> queryAllValidElements() throws CommonException;

    /**
     * 保存Flea元素
     *
     * @param fleaElementPOJO Flea元素POJO对象
     * @return Flea元素数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaElement saveElement(FleaElementPOJO fleaElementPOJO) throws CommonException;
}