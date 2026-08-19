# Boot Demo

基于 **Spring Boot 4.x** 的通用微服务基础框架，按功能拆分为独立子模块，集成常用企业级技术栈，开箱即用。

## 技术栈

| 类别 | 技术 | 版本 |
|------|------|------|
| 基础框架 | Spring Boot | 4.1.0 |
| 微服务 | Spring Cloud | 2025.1.2 |
| 语言 | Java | 21 |
| ORM | Hibernate (JPA) / MyBatis-Plus | 7.4.x / 3.5.17 |
| 构建工具 | Maven | — |
| 注解处理 | Lombok + MapStruct | — |

## 模块结构

```
boot-demo
├── boot-base              # 基础工具模块（通用工具类、注解、AOP、DTO 等）
├── data-jpa               # Spring Data JPA 数据访问（Hibernate + H2）
├── data-mybatis           # MyBatis-Plus 数据访问（Spring Boot 4 适配）
├── data-rest              # Spring Data REST（自动生成 RESTful API + HAL Explorer）
├── data-audit             # 数据审计（Javers 变更追踪与差异对比）
├── multi-data-source      # 多数据源配置（JPA + MySQL/HSQLDB）
├── redis-cache            # Redis 缓存
│   ├── data-redis         # Spring Data Redis 集成
│   └── data-redisson      # Redisson 分布式锁/数据结构集成
├── web                    # Web 交互
│   ├── mvc                # Spring MVC（Validation + Actuator）
│   ├── flux               # Spring WebFlux 响应式编程
│   └── swagger            # Swagger API 文档
├── websocket              # WebSocket 实时通信
├── http-util              # HTTP 工具（HttpClient5 + OpenFeign + OkHttp）
├── easy-excel             # Excel 导入导出（EasyExcel + Apache POI）
├── minio                  # MinIO 对象存储集成
├── jwt                    # JWT 认证（Nimbus JOSE + Jasypt 加密）
├── cloud-config           # Spring Cloud 配置中心
│   ├── config-server      # 配置服务端
│   └── config-client      # 配置客户端
├── log                    # 日志（Log4j2）
├── breaker                # 熔断器（Resilience4j）
└── counter-maven-plugin   # 自定义 Maven 插件（依赖计数示例）
```

## 环境要求

- **JDK** 17+
- **Maven** 3.9+

## 快速开始

### 构建项目

```bash
mvn clean package -Dmaven.test.skip=true
```

### 使用 Mise 任务管理

项目提供 [Mise](https://mise.jdx.dev/) 任务配置（`.mise.toml`）：

| 任务 | 说明 |
|------|------|
| `mise run build` | 清理并构建整个项目 |
| `mise run dep-check` | 检查可更新的依赖版本 |
| `mise run dep-update` | 自动更新依赖到最新稳定版 |
| `mise run set-version` | 设置项目版本号 |

## 项目约定

- 父 POM 统一管理 Spring Boot / Spring Cloud BOM 版本
- 子模块间通过 `${project.version}` 引用内部依赖
- 使用 MapStruct + Lombok 注解处理器链，编译期生成映射代码
- 集成 `git-commit-id-maven-plugin`，打包时自动生成 Git 构建信息
- 使用 `versions-maven-plugin` 管理项目版本变更
