# FLEA RESTful 接口规范

| 版本号 | 主要修改内容 | 编制人 | 更新时间 |
| --- | --- | --- | --- |
| 1.0.0 | 初稿 | huazie | 2018-04-13 |
| 1.0.1 | 概述修改，接口地址内容修改，请求报文内容修改，响应报文内容修改 | huazie | 2019-11-21 |
| 1.0.2 | 请求公共报文内容修改（ACCT => ACCOUNT） | huazie | 2021-01-03 |
| 1.0.3 | 新增返回码规范 | Huazie | 2022-06-21 |
| 1.0.4 | 新增资源定义，并更新返回码规范 | Huazie | 2025-03-04 |

# 总体概述

Flea RESTful 接口，即遵守REST式风格的接口，基于Jersey开发，遵循JAX-RS规范。接入Flea RESTful 接口的应用提供RESTful Web Services（REST式的Web服务，它是一种遵守REST式风格的Web服务）。REST式的Web服务是一种ROA（Resource-Oriented Architecture, 面向资源的架构）的应用。其主要特点是方法信息存在于HTTP的方法中（比如GET、PUT、POST、DELETE），作用域存在于URI中。

# 接口定义

## 接口协议

基于HTTP协议，接口完整报文支持XML和JSON，接口业务报文使用JSON

## 交互编码

交互内容编码均采用UTF-8格式

## 接口地址

服务端主机地址/**自定义部分** （http://ffs.huazie.com/**fleafs**）

**自定义部分** 可见如下代码中 注解 **ApplicationPath** 内容

```java
/**
 * FleaFS 资源入口
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@ApplicationPath("/fleafs/*")
public class FleaFSResourceConfig extends FleaResourceConfig {

    /**
     * 无参构造方法
     *
     * @since 1.0.0
     */
    public FleaFSResourceConfig() {
        super();
        // 设置 Jersey 过滤器配置文件 路径
        FleaJerseyFilterConfig.setFilePath("flea/jersey/fleafs-jersey-filter.xml");
    }
}
```

## 资源定义

以上传资源为例，如下贴出上传资源类，其中注解 `Path` 内容会追加到接口地址中来请求（http://ffs.huazie.com/fleafs/upload）。

```java
/**
 * 上传资源类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Path("upload")
public class UploadResource extends FleaJerseyPostResource {

}
```

## 请求报文

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<JERSEY>
    <REQUEST>
        <PUBLIC>
            <SYSTEM_ACCOUNT_ID>系统账户编号</SYSTEM_ACCOUNT_ID>
            <ACCOUNT_ID>账户编号</ACCOUNT_ID>
            <RESOURCE_CODE>资源编码</RESOURCE_CODE>
            <SERVICE_CODE>服务编码</SERVICE_CODE>
        </PUBLIC>
        <BUSINESS>
            <INPUT>业务入参JSON报文</INPUT>
        </BUSINESS>
    </REQUEST>
</JERSEY>
```

## 响应报文

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<JERSEY>
    <RESPONSE>
        <PUBLIC>
            <RESULT_CODE>返回码</RESULT_CODE>
            <RESULT_MESS>返回信息</RESULT_MESS>
        </PUBLIC>
        <BUSINESS>
            <OUTPUT>业务出参JSON报文</OUTPUT>
        </BUSINESS>
    </RESPONSE>
</JERSEY>
```

# 返回码定义

Flea RESTful接口资源服务提供方，如果出现异常，应当抛出异常对应的国际码，同时在Flea Jersey 国际码和错误码映射表【flea_jersey_i18n_error_mapping】中配置异常国际码和错误返回码的映射关系，其中异常国际码由各资源服务提供方自行配置并使用、错误返回码统一按下面的规范定义。

目前，错误返回码包含如下分类：.

## 成功

| 一码位 | 二码位 | 三码位 | 描述 |
| --- | --- | --- | --- |
| 0 | 00 | 000 | 成功，RESULT_MESS返回success |

## Flea Jersey接口服务端的过滤器错误返回码

| 一码位 | 二码位 | 三码位 | 描述 |
| --- | --- | --- | --- |
| 1 | 00 | 000 | 请求报文不能为空 |
| 1 | 00 | 001 | 请求公共报文不能为空 |
| 1 | 00 | 002 | 请求业务报文不能为空 |
| 1 | 00 | 003 | 请求公共报文入参【{0}】不能为空 |
| 1 | 00 | 004 | 未能找到指定资源服务配置数据【service_code = {0} ，resource_code = {1}】 |
| 1 | 00 | 005 | 请检查服务端配置【service_code = {0} ，resource_code = {1}】：【{2} = {3}】非法 |
| 1 | 00 | 006 | 资源【{0}】下的服务【{1}】请求异常：配置的出参【{2}】与服务方法【{3}】出参【{4}】类型不一致 |
| 1 | 00 | 007 | 用户【user_id = {0}】不存在或已失效！ |
| 1 | 00 | 008 | 账户【account_id = {0}】不存在或已失效！ |
| 1 | 00 | 009 | 资源【resource_code = {0}】不存在或已失效！ |
| 1 | 00 | 010 | 账户【account_id = {0}】没有权限调用归属于系统【system_account_id = {1}】的资源【{2}】 |
| 1 | 00 | 011 | 当前资源【{0}】不属于指定系统【system_account_id = {1}】，请确认！ |
| 1 | 00 | 012 | 【{0}】获取失败，请检查！ |
| 1 | 00 | 013 | 【{0}】不能为空，请检查！ |
| 1 | 00 | 014~999 | 保留的过滤器错误返回码 |
| 9 | 99 | 998 | 返回码未配置 |
| 9 | 99 | 999 | 未知异常 (系统异常等，非自定义的异常) |

## Flea Jersey接口服务端的业务异常错误返回码

| 一码位 | 二码位 | 三码位 | 描述 |
| --- | --- | --- | --- |
| 1 | 01 | 000 | 【{0}】不能为空！ |
| 1 | 01 | 001 | 入参【{0}】不能为空！ |
| 1 | 01 | 002 | 入参【{0}】或【{1}】两者必填一个，请检查！ |
| 1 | 01 | 003 | 文件类目【{0}】不存在，请检查！ |
| 1 | 01 | 004~999 | 业务异常错误返回码 |
| 1 | 02~99 | 000~999 | 业务异常错误返回码 |
| 2~8 | 00~99 | 000~999 | 业务异常错误返回码 |
| 9 | 00~99 | 000~997 | 业务异常错误返回码 |
