# API Test 新脚本服务 Demo

本项目是自动化测试平台新脚本服务的后端 Demo 骨架，当前阶段用于提供可运行的基础服务能力，便于后续迭代导入、草稿管理、脚本编排、变量中心、SQL 步骤、执行记录、正式脚本用例与 AI 接口预留等模块。

## 技术栈

- Java 8
- Spring Boot 2.7.18
- MySQL 8.x（示例配置）

## 当前已实现内容

- Spring Boot 工程骨架。
- 分层包结构：`controller`、`service`、`repository`、`entity`、`dto`、`vo`、`common`。
- 统一返回体 `ApiResponse`。
- 全局异常处理 `GlobalExceptionHandler`。
- 健康检查接口：`GET /api/health`。
- 数据库连接示例配置：`application.yml`。
- 初始化 SQL 占位文件：`docs/sql/init.sql`。

## 目录结构

```text
src/main/java/com/example/apitest
├── ApiTestApplication.java
├── common
├── config
├── controller
├── dto
├── entity
├── exception
├── repository
├── service
│   └── impl
└── vo
```

## 启动方式

### 1. 环境准备

- JDK 1.8+
- Maven 3.6+
- MySQL（可选，当前骨架默认仅提供配置示例）

### 2. 修改数据库配置（如需）

编辑 `src/main/resources/application.yml`：

- `spring.datasource.url`
- `spring.datasource.username`
- `spring.datasource.password`

### 3. 启动项目

```bash
mvn spring-boot:run
```

或先打包再运行：

```bash
mvn clean package
java -jar target/api-test-0.0.1-SNAPSHOT.jar
```

### 4. 验证健康检查

```bash
curl http://localhost:8080/api/health
```

预期返回：

```json
{
  "success": true,
  "code": "SUCCESS",
  "message": "success",
  "data": "success"
}
```

## 本次未实现内容

- 导入功能（HAR）。
- 脚本编排。
- 执行机与执行调度。
- AI 能力接入。
- 复杂权限体系。

以上内容将在后续任务中按模块逐步补齐。
