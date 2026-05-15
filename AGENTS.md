# AGENTS.md

## 项目背景

本项目是自动化测试平台新脚本服务 Demo，用于验证以下能力：

- 导入 / 草稿管理，当前导入功能支持 HAR 格式。
- 脚本编排：API_STEP、SQL_STEP、IF-ELSE、FOR、WAIT、COMPONENT。
- 新报文模型：`rawBody + fieldConfigs + treeCache`。
- 变量中心：变量中文名、英文名、数据类型、来源、作用域、使用位置。
- SQL 步骤：支持 MySQL、Oracle、OceanBase-Oracle；支持页面填写 SQL 环境信息并可选择保存到环境配置。
- 复制模式 / 引用模式：组件、单接口脚本、可复用步骤支持复制引入和引用引入，引用对象可进入编排模式。
- 执行记录：放在脚本内部，支持查看逻辑步骤、报文信息、执行日志、变量情况。
- 正式脚本用例：草稿确认后同步生成脚本版本和用例版本；正式用例复制时必须修改脚本名称。
- AI 助手接口预留：通过上下文输入协议、结构化建议输出和 Patch Operations 对接。

## 技术约束

- 后端使用 Java 8 + Spring Boot 2.x。
- 数据库使用 MySQL。
- 前端 Demo 可先使用静态 HTML、Vue 2 或简单页面验证流程。
- Demo 优先保证功能链路清晰，不追求完整生产级权限和性能。
- 不引入项目 / 团队层级。
- 系统切换作为工作空间。
- 用户维度资源包括：全局变量、环境配置、组件库、外部数据集。

## 代码要求

- 保持 Controller / Service / Repository / Entity / DTO / VO 分层。
- 不要把业务逻辑写在 Controller。
- DTO、Entity、VO 不要混用。
- 数据库表结构需要提供 SQL migration 或初始化脚本。
- 不要一次性大范围重构无关代码。
- 每次任务尽量控制在可审查的小变更内。

## 业务约束

- 导入功能当前只支持 HAR 格式。
- 导入后生成脚本草稿，不直接生成正式脚本用例。
- 草稿确认后同步生成 ScriptVersion 和 CaseVersion。
- `rawBody` 保存干净可执行报文。
- 字段备注保存到 `fieldConfigs.remark`。
- `treeCache` 只作为展示缓存，执行逻辑不能依赖它。
- AI 助手只预留接口，不直接修改正式版本。
- AI 输出应通过 Patch Operations 应用到草稿。
- 复制正式脚本用例时必须修改脚本名称。

## 每次任务要求

Codex 每次只完成当前任务。完成后需要说明：

1. 修改了哪些文件。
2. 实现了哪些功能。
3. 如何启动或测试。
4. 还剩哪些未完成项。
