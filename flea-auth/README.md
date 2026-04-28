# Flea Auth 模块

通用权限管理模块，提供用户、角色、权限、功能（菜单、操作、元素、资源）等一体化的权限管理解决方案。

## 模块介绍

Flea Auth 是 Flea Framework 框架的核心模块之一，专注于企业级应用的权限管理。它采用经典的 RBAC（基于角色的访问控制）模型，并在此基础上进行了扩展，支持更复杂的权限分配场景。

## 核心特性

- **用户管理**：完整的用户生命周期管理，支持用户注册、登录、登出
- **角色管理**：灵活的角色定义，支持角色组批量管理
- **权限管理**：细粒度的权限控制，支持权限组管理
- **功能管理**：支持菜单、操作、元素、资源四种功能类型
- **授权管理**：灵活的关联关系管理，支持多种授权模式
- **登录日志**：完整的登录登出记录追踪

## 技术架构

### 模块结构

```
flea-auth/
├── src/main/java/com/huazie/fleaframework/auth/
│   ├── base/                          # 基础层
│   │   ├── function/                  # 功能模块（菜单/操作/元素/资源）
│   │   ├── privilege/                 # 权限模块
│   │   ├── role/                      # 角色模块
│   │   └── user/                      # 用户模块
│   ├── cache/                         # 缓存配置
│   ├── common/                        # 公共模块
│   │   ├── pojo/                      # 数据传输对象
│   │   ├── service/                   # 服务接口与实现
│   │   └── exceptions/                # 异常定义
│   └── util/                          # 工具类
├── src/main/resources/
│   ├── flea/cache/                    # 缓存配置文件
│   ├── flea/db/                       # 分库分表配置
│   └── flea/i18n/                     # 国际化资源
└── fleaauth.sql                       # 数据库初始化脚本
```

### 核心模块

| 模块 | 说明 |
|------|------|
| **User Module** | 用户管理服务，提供用户注册、登录、关联管理 |
| **Role Module** | 角色管理服务，提供角色和角色组管理 |
| **Privilege Module** | 权限管理服务，提供权限和权限组管理 |
| **Function Module** | 功能管理服务，提供菜单、操作、元素、资源管理 |
| **Auth Service** | 授权服务，提供权限校验和数据查询 |

## 数据模型

### 核心实体

| 实体 | 说明 |
|------|------|
| `FleaUser` | 用户信息 |
| `FleaAccount` | 账户信息（登录账号） |
| `FleaUserGroup` | 用户组 |
| `FleaRole` | 角色 |
| `FleaRoleGroup` | 角色组 |
| `FleaPrivilege` | 权限 |
| `FleaPrivilegeGroup` | 权限组 |
| `FleaMenu` | 菜单 |
| `FleaOperation` | 操作 |
| `FleaElement` | 元素 |
| `FleaResource` | 资源 |
| `FleaLoginLog` | 登录日志 |

### 关联关系

**用户与角色关联：**
```
用户 ── 用户组 ── 角色组 ── 角色
   │
   └── 角色 ── 权限组 ── 权限
```

**权限与功能关联：**
```
权限 ── 菜单
   ├── 操作
   ├── 元素
   └── 资源
```

**完整链路示例：**
```
用户 → 用户组 → 角色组 → 角色 → 权限组 → 权限 → 菜单/操作/元素/资源
```

## 依赖引入

### 1. Maven 依赖

```xml
<dependency>
    <groupId>com.huazie.fleaframework</groupId>
    <artifactId>flea-auth</artifactId>
    <version>2.0.0</version>
</dependency>
```

### 2. 引入配置

参考 flea-config 模块。该模块的 `applicationContext.xml` 已自动配置了 flea-auth 相关的 Bean：

#### 外部配置（参考 flea-config 模块）

| 配置文件 | 说明 |
|---------|------|
| `spring/db/jpa/fleaauth-spring.xml` | JPA 事务管理器和 EntityManagerFactory |
| `META-INF/fleaauth-persistence.xml` | JPA 持久化单元，定义所有授权相关实体类 |
| `flea/cache/flea-cache.xml` | 主缓存配置，**引入** `flea/cache/flea-auth-cache.xml` |
| `flea/db/flea-table-split.xml` | 主分表配置，**引入** `flea/db/flea-auth-table-split.xml` |

#### 内部配置（flea-auth 模块自带）

| 配置文件 | 说明 |
|---------|------|
| `flea/cache/flea-auth-cache.xml` | 授权模块缓存定义（被 flea-cache.xml 引入） |
| `flea/db/flea-auth-table-split.xml` | 授权模块分表规则（被 flea-table-split.xml 引入） |
| `fleaauth.sql` | 数据库初始化脚本（28 张表） |
| `flea/i18n/flea_i18n_auth*.properties` | 授权模块通用提示语（支持中英文） |
| `flea/i18n/flea_i18n_error_auth*.properties` | 授权模块错误信息（支持中英文） |

#### JPA 持久化单元包含的实体类

```
用户模块：FleaUser, FleaAccount, FleaUserGroup, FleaUserRel, FleaUserAttr,
         FleaAccountAttr, FleaRealNameInfo, FleaLoginLog
角色模块：FleaRole, FleaRoleGroup, FleaRoleRel, FleaRoleGroupRel
权限模块：FleaPrivilege, FleaPrivilegeGroup, FleaPrivilegeRel, FleaPrivilegeGroupRel
功能模块：FleaMenu, FleaOperation, FleaElement, FleaResource, FleaFunctionAttr
```


### 3. 基本使用

#### 用户登录

```java
@Autowired
private IFleaUserModuleSV fleaUserModuleSV;

public void login(String accountCode, String password) {
    FleaUserLoginPOJO loginPOJO = new FleaUserLoginPOJO();
    loginPOJO.setAccountCode(accountCode);
    loginPOJO.setAccountPwd(password);
    FleaAccount account = fleaUserModuleSV.login(loginPOJO);
}
```

#### 用户注册

```java
@Autowired
private IFleaUserModuleSV fleaUserModuleSV;

public void register(String accountCode, String password, String userType) {
    FleaUserRegisterPOJO registerPOJO = new FleaUserRegisterPOJO();
    registerPOJO.setAccountCode(accountCode);
    registerPOJO.setAccountPwd(password);
    registerPOJO.setUserType(userType);
    FleaAccount account = fleaUserModuleSV.register(registerPOJO);
}
```

#### 权限校验

```java
@Autowired
private IFleaAuthSV fleaAuthSV;

public boolean checkPermission(Long accountId, Long systemId, String resourceCode) {
    return fleaAuthSV.checkResourceAuth(accountId, systemId, resourceCode);
}
```

#### 获取用户菜单

```java
@Autowired
private IFleaAuthSV fleaAuthSV;

public List<FleaMenu> getUserMenus(Long accountId, Long systemId) {
    return fleaAuthSV.queryAllAccessibleMenus(accountId, systemId);
}
```

## 安全特性

### 密码加密

采用 BCrypt 算法进行密码加密，支持与旧版 SHA 算法的兼容。

```java
// 密码加密
String encryptedPwd = SecurityUtils.encryptToBCrypt(originalPwd);

// 密码校验
boolean matches = SecurityUtils.matchesPassword(rawPwd, encryptedPwd);
```

## 缓存配置

`flea-auth` 内置了多个缓存区域，用于提升权限校验效率：

```xml
<!-- 配置文件：src/main/resources/flea/cache/flea-auth-cache.xml -->
<flea-cache>
    <caches>
        <cache key="fleaauthuser" type="fleaAuth" expiry="86400" desc="Flea授权用户数据缓存" />
        <cache key="fleaauthaccount" type="fleaAuth" expiry="86400" desc="Flea授权账户数据缓存" />
        <cache key="fleaauthmenu" type="fleaAuth" expiry="86400" desc="Flea授权菜单数据缓存" />
        <cache key="fleaauthoperation" type="fleaAuth" expiry="86400" desc="Flea授权操作数据缓存" />
        <cache key="fleaauthelement" type="fleaAuth" expiry="86400" desc="Flea授权元素数据缓存" />
        <cache key="fleaauthresource" type="fleaAuth" expiry="86400" desc="Flea授权资源数据缓存" />
    </caches>
</flea-cache>
```

## 国际化

支持多语言，资源文件位于 `src/main/resources/flea/i18n/`：

- `flea_i18n_auth.properties` - 通用提示语
- `flea_i18n_error_auth.properties` - 错误信息

## 注意事项

1. **TODO 项**：部分功能待完善，包括 IPv6 地址获取等
2. **数据库兼容性**：当前 SQL 脚本基于 MySQL 5.5+
3. **依赖管理**：需要配合 `flea-db`、`flea-cache` 等模块使用

## 版本历史

- **2.0.0** - 重构优化
- **1.0.0** - 初始版本
