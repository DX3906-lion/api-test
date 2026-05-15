# AGENTS.md

## 项目背景

本项目是自动化测试平台新脚本服务 demo，用于验证：
- 导入 / 草稿管理
- 脚本编排
- API_STEP
- SQL_STEP
- 变量中心
- fieldConfigs
- 执行记录
- 正式脚本用例列表
- 复制模式 / 引用模式
- AI 助手接口预留

## 技术约束

- 后端使用 Java 8 + Spring Boot 2.x
- 数据库使用 MySQL
- 前端可使用 Vue 2 或简单 HTML + JS demo
- demo 优先保证功能链路清晰，不追求完整生产级权限和性能
- 不引入项目 / 团队层级
- 系统切换作为工作空间
- 用户维度资源包括：全局变量、环境配置、组件库、外部数据集

## 代码要求

- 每个功能必须有清晰的 controller / service / repository 分层
- DTO、Entity、VO 不要混用
- 不要把业务逻辑写在 Controller
- 不要一次性大范围重构无关代码
- 数据库表结构需要提供 SQL migration
- 前端页面优先保证原型流程可运行

## 业务约束

- 导入功能当前只支持 HAR 格式
- 导入后生成脚本草稿，不直接生成正式脚本用例
- 草稿确认后同步生成 ScriptVersion 和 CaseVersion
- rawBody 保存干净报文
- 字段备注保存到 fieldConfigs.remark
- treeCache 只作为展示缓存
- AI 助手只预留接口，不直接修改正式版本
- AI 输出应通过 patch operations 应用到草稿

## 每次任务要求

Codex 每次只完成当前任务。
完成后需要说明：
1. 修改了哪些文件
2. 实现了哪些功能
3. 如何启动或测试
4. 还剩哪些未完成项
