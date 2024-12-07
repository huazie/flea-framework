# <img src="src/docs/flea-framework.png" width="80" height="80"> Flea Framework
Flea框架，取名跳蚤，源于作者Huazie的跳蚤市场毕设项目，第一版代码更是从中而来。另外提到跳蚤，自然想到它的小个头和无处不在的特点，这也是Flea框架一直努力的目标，成为一款小巧易用的Java应用基础开发框架。

[英文说明/English Documentation](README_EN.md)

## 介绍

|  模块                  |  名称              |    描述                                |
|-----------------------|--------------------| --------------------------------------|
|  flea-algorithm       | Flea 通用算法包     | 各类算法的Java实现（持续扩充中）  |
|  flea-auth            | Flea 授权操作包     | 授权四子模块，包含用户、角色、权限 和 功能|
|  flea-framework-bom   | Flea 框架依赖清单   | 可以使用该依赖以pom方式导入Flea框架依赖 |
|  flea-cache           | Flea 缓存处理包     | 整合各类缓存接入，支持MemCached、Redis  |
|  flea-common          | Flea 通用工具包     | 国际化、日志、通用对象池、通用策略、通用动态代理、<br/>JSON和XML通用处理等各式工具类|
|  flea-config          | Flea 配置包         | Flea框架各模块使用的配置，尽在flea目录中;<br/>另外也包含JPA配置、Spring配置等|
|  flea-core            | Flea 核心逻辑包     | flea-config配置库、Flea请求处理|
|  flea-db              | Flea 数据持久包     | 分库分表的JPA实现【基于EclipseLink】、原生JDBC通用实现|
|  flea-jersey          | Flea Jersey 接口    | Flea Jersey 三子模块，分别是客户端、服务端 和 通用处理|
|  flea-job             | Flea 调度处理包     | 敬请期待。。。|
|  flea-msg             | Flea 消息处理包     | 敬请期待。。。|
|  flea-tools           | Flea 图形工具       | Unicode转换工具、JPA代码生成器 |

## 文档

### flea-auth

https://blog.huazie.com/diversity/blog/?path=/categories/%E5%BC%80%E5%8F%91%E6%A1%86%E6%9E%B6-Flea/flea-auth/

### flea-cache

https://blog.huazie.com/diversity/blog/?path=/categories/%E5%BC%80%E5%8F%91%E6%A1%86%E6%9E%B6-Flea/flea-cache/

### flea-db

https://blog.huazie.com/diversity/blog/?path=/categories/%E5%BC%80%E5%8F%91%E6%A1%86%E6%9E%B6-Flea/flea-db/

### flea-common

https://blog.huazie.com/diversity/blog/?path=/categories/%E5%BC%80%E5%8F%91%E6%A1%86%E6%9E%B6-Flea/flea-common/

### flea-jersey

https://blog.huazie.com/diversity/blog/?path=/categories/%E5%BC%80%E5%8F%91%E6%A1%86%E6%9E%B6-Flea/flea-jersey/

### flea-msg

https://blog.huazie.com/diversity/blog/?path=/categories/%E5%BC%80%E5%8F%91%E6%A1%86%E6%9E%B6-Flea/flea-msg/