package com.huazie.fleaframework.auth.common.service.interfaces;

import com.huazie.fleaframework.auth.base.function.entity.FleaElement;
import com.huazie.fleaframework.auth.base.function.entity.FleaMenu;
import com.huazie.fleaframework.auth.base.function.entity.FleaOperation;
import com.huazie.fleaframework.auth.base.function.entity.FleaResource;
import com.huazie.fleaframework.auth.common.pojo.function.attr.FleaFunctionAttrPOJO;
import com.huazie.fleaframework.auth.common.pojo.function.element.FleaElementPOJO;
import com.huazie.fleaframework.auth.common.pojo.function.menu.FleaMenuPOJO;
import com.huazie.fleaframework.auth.common.pojo.function.operation.FleaOperationPOJO;
import com.huazie.fleaframework.auth.common.pojo.function.resource.FleaResourcePOJO;
import com.huazie.fleaframework.common.exception.CommonException;

import java.util.List;

/**
 * Flea功能管理服务层接口
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public interface IFleaFunctionModuleSV {

    /**
     * 获取有效的菜单数据集，如果Flea菜单POJO对象传空，则查询所有有效的Flea菜单数据
     *
     * @param fleaMenuPOJO Flea菜单POJO对象
     * @return 菜单数据集
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaMenu> queryValidMenus(FleaMenuPOJO fleaMenuPOJO) throws CommonException;

    /**
     * 添加Flea菜单数据（包括Flea菜单和Flea菜单扩展属性信息）
     *
     * @param fleaMenuPOJO Flea菜单POJO对象
     * @return 菜单编号
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    Long addFleaMenu(FleaMenuPOJO fleaMenuPOJO) throws CommonException;

    /**
     * 修改Flea菜单数据
     *
     * @param menuId       菜单编号
     * @param fleaMenuPOJO Flea菜单POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void modifyFleaMenu(Long menuId, FleaMenuPOJO fleaMenuPOJO) throws CommonException;

    /**
     * 获取有效的操作数据集，如果Flea操作POJO对象传空，则查询所有有效的Flea操作数据
     *
     * @param fleaOperationPOJO Flea操作POJO对象
     * @return Flea操作数据集
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaOperation> queryValidOperations(FleaOperationPOJO fleaOperationPOJO) throws CommonException;

    /**
     * 添加FLea操作数据（包括Flea操作和Flea操作扩展属性信息）
     *
     * @param fleaOperationPOJO Flea操作POJO对象
     * @return 操作编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    Long addFleaOperation(FleaOperationPOJO fleaOperationPOJO) throws CommonException;

    /**
     * 修改Flea操作数据
     *
     * @param operationId       操作编号
     * @param fleaOperationPOJO Flea操作POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void modifyFleaOperation(Long operationId, FleaOperationPOJO fleaOperationPOJO) throws CommonException;

    /**
     * 获取有效的元素数据集，如果Flea元素POJO对象传空，则查询所有有效的Flea元素数据
     *
     * @param fleaElementPOJO Flea元素POJO对象
     * @return Flea元素数据集
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaElement> queryValidElements(FleaElementPOJO fleaElementPOJO) throws CommonException;

    /**
     * 添加Flea元素数据（包括Flea元素和Flea元素扩展属性信息）
     *
     * @param fleaElementPOJO Flea元素POJO对象
     * @return 元素编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    Long addFleaElement(FleaElementPOJO fleaElementPOJO) throws CommonException;

    /**
     * 修改Flea元素数据
     *
     * @param elementId       元素编号
     * @param fleaElementPOJO FLea元素POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void modifyFleaElement(Long elementId, FleaElementPOJO fleaElementPOJO) throws CommonException;

    /**
     * 获取有效的资源数据集，如果Flea资源POJO对象传空，则查询所有有效的Flea资源数据
     *
     * @param fleaResourcePOJO Flea资源POJO对象
     * @return Flea资源数据集
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaResource> queryValidResources(FleaResourcePOJO fleaResourcePOJO) throws CommonException;

    /**
     * 添加Flea资源数据（包括Flea资源和Flea资源扩展属性信息）
     *
     * @param fleaResourcePOJO Flea资源POJO对象
     * @return 资源编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    Long addFleaResource(FleaResourcePOJO fleaResourcePOJO) throws CommonException;

    /**
     * 修改Flea资源数据
     *
     * @param resourceId       资源编号
     * @param fleaResourcePOJO Flea资源POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void modifyFleaResource(Long resourceId, FleaResourcePOJO fleaResourcePOJO) throws CommonException;

    /**
     * 修改功能扩展属性
     *
     * @param fleaFunctionAttrPOJO 功能扩展属性POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void modifyFleaFunctionAttr(FleaFunctionAttrPOJO fleaFunctionAttrPOJO) throws CommonException;

    /**
     * 批量修改功能扩展属性
     *
     * @param fleaFunctionAttrPOJOList 功能扩展属性POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void modifyFleaFunctionAttrs(List<FleaFunctionAttrPOJO> fleaFunctionAttrPOJOList) throws CommonException;
}
