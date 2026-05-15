# API Test 新脚本服务 Demo

本仓库用于验证自动化测试平台新脚本服务 Demo，重点覆盖：

- 导入 / 草稿管理：当前导入功能支持 HAR 格式。
- 脚本编排：API 步骤、SQL 步骤、条件、循环、等待、组件。
- 报文模型：`rawBody + fieldConfigs + treeCache`。
- 变量中心：变量中文名、英文名、数据类型、来源、作用域、使用位置。
- SQL 步骤：支持 MySQL、Oracle、OceanBase-Oracle；支持页面填写 SQL 环境信息并可保存到环境配置。
- 组件 / 单接口 / 可复用步骤：支持复制模式和引用模式，引用对象可进入编排模式。
- 执行记录：放在脚本内部，支持查看逻辑步骤、报文、日志、变量情况。
- 正式脚本用例：草稿确认后同步生成脚本版本和用例版本；复制正式用例时必须修改脚本名称。
- AI 助手预留：通过上下文输入协议、结构化建议输出和 Patch Operations 对接。

## 目录

```text
/docs/requirements/新脚本服务需求说明书_v12.md
/docs/requirements/AI助手Agent对接需求包.md
/docs/prototype/自动化测试平台新脚本服务流程原型_v12.html
/docs/architecture/技术架构说明.md
/docs/api/openapi.yaml
/docs/tasks/backlog.md
AGENTS.md
README.md
```

## 推荐推进方式

1. 先按 `/docs/tasks/backlog.md` 拆分 Codex 小任务。
2. 平台功能先独立跑通，不依赖 AI。
3. AI 先基于协议和 mock 数据开发。
4. 双方提前冻结：AI 输入协议、AI 输出协议、Patch Operations、平台校验规则。

## 技术建议

- 后端：Java 8 + Spring Boot 2.x。
- 数据库：MySQL。
- 前端 Demo：可先使用静态 HTML 或 Vue 页面验证流程。
- AI 对接：先实现接口预留和 mock suggestions，再接入真实 AI 服务。
