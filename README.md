# 智能城市旅游管理系统

## 一、技术栈

1. java 17
2. Spring Boot 3.2.5
3. Spring Security + Jwt
4. Spring AI 1.0.0-M6
5. RabbitMQ
6. WebSocket
7. MyBatis Plus
8. redis
9. fastjson2



## 二、功能点

### 旅游资源模块

- 资源信息管理
- 资源类型管理
- 产品类型管理



### 订单模块

- 订单基本管理
- 下单管理
- 支付管理（假接口，未实现）



### 用户模块

- 用户信息管理
- 个人信息管理
- 角色管理
- 权限管理



### 鉴权模块

- SpringSecurity 全局认证
- Jwt 处理模块



### WebSocket 模块

- 建立连接
- 处理消息发送与消息接收



### 定时任务模块

- 定时监控数据库 MySQL 情况



### 全局功能

- aop 日志记录
- 权限管理（基于RBAC鉴权系统的细粒度权限管理）
- 自定义异常处理
- redis 启动失败处理 *
- 数据库性能监控
- 请求ip-api监控
- 用户行为分析（前端页面访问、停留时间等）





## 三、系统安全

### 系统认证

> ps: 主要是基于 Spring Security 的认证

- 自定义 JwtAuthenticationTokenFilter
- 自定义 LoginSucccessHandler
- 自定义 LogoutSuccessHandler
- 自定义 LoginFailureHandler
- 自定义配置（部分接口可跳过鉴权，其余均需要认证）
- 允许异步请求与响应
- 允许定时任务的请求
- 关闭 session
- 关闭 csrf







### 接口权限管理

> ps: 在每个接口前面添加自定义的注解，用于权限校验。使用**RBAC鉴权模式** 

数据库基本逻辑：

1. 数据库 permissions 表存储每个权限
2. 一个角色可以对应多个权限
3. 一个用户对应一个角色（本系统规定。也可设计为多个）



接口需要权限（即 hasAuthority）实现逻辑：

1. 获取到接口需要的权限 code
2. 判断 code 是否为 null
   1. null 则不需要权限，跳过接口鉴权
   2. 非 null 则需要权限继续判断
3. 根据 code 从数据库查询对应的权限是否存在
   1. 存在，需要鉴权
   2. 不存在，抛异常（系统错误）
4. 从登录用户身上查询是否含有此权限（自己封装方法）
   1. 不包含，抛异常（无权限）
   2. 包含，则继续访问即可。







## 四、api接口

### 用户认证

- 登录
- 注册
- 登出



### 用户管理

- 查询所有用户
- 新增用户
- 修改个人信息
- 修改用户信息
- 修改个人密码
- 重置用户密码
- 导出所有用户信息
- 给用户添加角色
- 查询单个用户
- 删除用户
- 查询用户的角色
- 查询用户的权限
- 获取个人信息
- 批量删除用户



### 角色管理



- 获取所有角色
- 新增角色
- 更新角色
- 给角色新增权限
- 根据角色id查询所有权限
- 删除角色
- 批量删除角色



### 权限管理

- 查询所有权限
- 新增权限
- 编辑权限
- 删除权限
- 批量删除权限



### 文件管理

- 上传文件
- 下载文件



### 订单管理

- 查询所有订单信息（管理员）
- 查询所有订单信息（用户）
- 查询所有订单信息（商户）
- 新增订单
- 更新订单
- 支付订单
- 创建订单
- 删除订单
- 批量删除订单



### 订单类型管理

- 查询所有订单类型
- 添加新订单类型
- 更新订单类型
- 删除订单类型
- 批量删除订单类型



### 旅游资源管理

- 查询所有旅游资源（管理员）
- 查询所有旅游资源（商户）
- 查询所有旅游资源（用户）
- 查询推荐旅游资源
- 添加新旅游资源
- 更新旅游资源
- 删除旅游资源
- 批量删除旅游资源



### 旅游资源类型管理

- 查询所有旅游资源类型
- 添加新旅游资源类型
- 更新旅游资源类型
- 删除旅游资源类型
- 批量删除旅游资源类型



### 产品类型管理

- 查询所有产品类型
- 添加新产品类型
- 更新产品类型
- 删除产品类型
- 批量删除产品类型



### AI 管理

- 普通聊天
- 智能客服聊天



## 五、账号测试

商户：

- sj123456
- 123456789



管理员：

- yangg12
- 123456789



用户：

- yk123456
- 12345678



