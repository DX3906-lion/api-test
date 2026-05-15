# 新脚本服务 Demo 任务看板

## M0 项目骨架

- [ ] T001 创建 Spring Boot 2.x 项目骨架
- [ ] T002 添加统一返回 `ApiResponse`
- [ ] T003 添加全局异常处理
- [ ] T004 添加健康检查接口 `GET /api/health`
- [ ] T005 添加 MySQL 配置示例
- [ ] T006 添加数据库初始化脚本目录

## M1 领域模型与表结构

- [ ] T011 新增 `system_workspace` 表
- [ ] T012 新增 `script_draft` 表和基础 CRUD
- [ ] T013 新增 `flow_step` 表和基础 CRUD
- [ ] T014 新增 `payload_content` / `field_config`
- [ ] T015 新增 `script_variable`
- [ ] T016 新增 `extractor_config` / `assertion_config`
- [ ] T017 新增 `script_version` / `case_version`

## M2 导入 / 草稿管理

- [ ] T021 实现导入弹窗后端接口，当前支持 HAR 与 Postman Collection 格式
- [ ] T022 分别校验 HAR(`log.entries`) 与 Postman Collection(`item`)
- [ ] T023 保存原始导入文件元信息
- [ ] T024 解析 HAR / Postman Collection 并生成 `script_draft`
- [ ] T025 生成基础 API_STEP
- [ ] T026 草稿列表查询
- [ ] T027 草稿详情查询
- [ ] T028 草稿删除

## M3 编排页面基础接口

- [ ] T031 查询草稿完整编排结构
- [ ] T032 新增 API_STEP
- [ ] T033 新增 SQL_STEP
- [ ] T034 更新步骤顺序
- [ ] T035 复制单个步骤
- [ ] T036 删除步骤
- [ ] T037 启用 / 禁用步骤
- [ ] T038 保存步骤详情

## M4 变量中心与字段配置

- [ ] T041 查询脚本变量中心
- [ ] T042 新增 / 编辑 / 删除变量
- [ ] T043 字段配置 `fieldConfigs` CRUD
- [ ] T044 选中值设为变量的后端接口
- [ ] T045 扫描 `rawBody` 中的 `${变量}`
- [ ] T046 保存前变量校验
- [ ] T047 平台函数参数化基础能力

## M5 变量提取与断言

- [ ] T051 `extractor_config` CRUD
- [ ] T052 `assertion_config` CRUD
- [ ] T053 支持 JSONPath / XPath / HEADER / COOKIE / REGEX / SQL_RESULT 配置
- [ ] T054 步骤详情返回 extractors 和 assertions
- [ ] T055 保存草稿时校验表达式基础格式

## M6 SQL 步骤

- [ ] T061 SQL 步骤详情保存
- [ ] T062 支持 MySQL、Oracle、OceanBase-Oracle 枚举
- [ ] T063 页面填写 SQL 环境信息
- [ ] T064 支持仅本步骤使用
- [ ] T065 支持保存到我的环境配置
- [ ] T066 SQL 调试接口，Demo 阶段可 mock 返回结果

## M7 执行记录

- [ ] T071 单步调试记录
- [ ] T072 全流程调试记录
- [ ] T073 执行记录列表
- [ ] T074 执行记录详情
- [ ] T075 左侧逻辑步骤树
- [ ] T076 右侧基本信息、报文信息、执行日志、变量情况

## M8 正式脚本用例与版本

- [ ] T081 草稿保存为 `ScriptVersion`
- [ ] T082 同步生成 `CaseVersion`
- [ ] T083 正式脚本用例列表
- [ ] T084 编辑正式脚本用例时生成新草稿
- [ ] T085 复制正式脚本用例
- [ ] T086 复制时必须填写新的脚本名称

## M9 复制模式 / 引用模式

- [ ] T091 复制模式引入组件 / 单接口脚本 / 可复用步骤
- [ ] T092 引用模式引入组件 / 单接口脚本 / 可复用步骤
- [ ] T093 引用版本选择：固定版本 / 最新版本
- [ ] T094 输入映射
- [ ] T095 输出映射
- [ ] T096 引用步骤点击进入被引用对象编排页面
- [ ] T097 被引用对象生成新版本后选择升级引用

## M10 AI 接口预留

- [ ] T101 AI 请求 DTO
- [ ] T102 AI 响应 DTO
- [ ] T103 intent 枚举
- [ ] T104 scope 枚举
- [ ] T105 contextPolicy
- [ ] T106 suggestions
- [ ] T107 patch operations
- [ ] T108 previewPatch 接口
- [ ] T109 applyDraftPatch 接口
- [ ] T110 validateDraft 接口
- [ ] T111 mock suggestions

## Codex 任务模板

```text
任务名称：
实现【XXX】

背景：
这是自动化测试平台新脚本服务 demo，技术栈为 Java 8 + Spring Boot 2.x + MySQL。
请遵守 AGENTS.md 中的项目约束。

本次目标：
1. ...
2. ...
3. ...

不做的内容：
1. 不实现 ...
2. 不修改 ...
3. 不引入 ...

验收标准：
1. ...
2. ...
3. ...

完成后请输出：
1. 修改文件列表
2. 实现说明
3. 测试方式
4. 后续建议
```
