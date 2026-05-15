# AI 助手 Agent 对接需求包

## 1. 背景与目标

AI 助手是自动化测试平台新脚本服务中的编排辅助能力，嵌入在脚本编排页面。它不是通用聊天机器人，也不直接修改正式资产。

目标：用户通过自然语言提出需求，AI Agent 根据当前步骤、选中步骤、整个脚本或执行记录上下文，生成结构化建议；用户查看差异并采纳后，由平台应用 Patch Operations 到当前草稿，并进行校验。

## 2. 能力边界

AI 可以做：

- 变量提取建议。
- 参数化建议。
- 平台函数使用建议。
- 断言生成建议。
- 变量依赖分析。
- 执行失败原因分析。
- IF / WAIT 等编排建议。

AI 不做：

- 不直接修改正式脚本版本。
- 不直接修改正式用例版本。
- 不直接修改交易信息正式版本。
- 不绕过用户确认应用变更。
- 不绕过平台校验保存草稿。

## 3. 场景清单

P0：

1. 根据响应提取变量。
2. 将固定值参数化。
3. 生成接口断言。
4. 分析变量依赖。
5. 检查变量未定义或使用前未产生。
6. 根据执行记录分析失败原因。

P1：

1. 推荐 IF-ELSE 条件。
2. 推荐 WAIT 等待步骤。
3. 推荐平台函数使用。
4. 推荐 SQL 结果变量提取。
5. 推荐组件输入输出映射。
6. 批量分析选中步骤。

## 4. 工作流程

```text
用户打开 AI 助手
  ↓
输入自然语言需求
  ↓
前端传入当前步骤 / 选中步骤 / 整个脚本范围
  ↓
Agent 判断 intent
  ↓
Context Builder 按需收集上下文
  ↓
AI 生成结构化 suggestions
  ↓
平台生成 Diff 预览
  ↓
用户选择单条采纳 / 全部采纳
  ↓
平台 applyDraftPatch
  ↓
平台 validateDraft
  ↓
用户保存草稿或调试
```

## 5. AI 输入协议

```json
{
  "requestId": "ai-req-001",
  "scriptDraftId": 1001,
  "userPrompt": "帮我从登录接口响应中提取 token，并应用到后续接口",
  "intent": "EXTRACT_VARIABLE",
  "scope": {
    "scopeType": "SELECTED_STEPS",
    "stepIds": [1, 2, 3]
  },
  "contextPolicy": {
    "includeFullBody": true,
    "includeResponseExample": true,
    "includeExecutionRecord": false,
    "includeVariableCenter": true,
    "includeFieldConfigs": true
  },
  "context": {
    "script": {},
    "steps": [],
    "variables": [],
    "fieldConfigs": [],
    "executionRecord": null
  }
}
```

原则：

- 不默认传整个脚本。
- 当前步骤场景只传当前步骤。
- 选中步骤场景只传选中步骤和必要上下文。
- 失败分析场景才传执行记录。
- 整体依赖分析场景才传脚本结构摘要。

## 6. AI 输出协议

AI 输出必须是结构化建议，不只返回自然语言。

```json
{
  "requestId": "ai-req-001",
  "summary": "发现 2 个变量提取和 3 处可替换位置。",
  "suggestions": [
    {
      "suggestionId": "sug-001",
      "type": "ADD_EXTRACTOR",
      "title": "提取登录 token",
      "description": "从登录接口响应 $.data.token 提取 token，并应用到后续接口 Authorization。",
      "impact": {
        "stepIds": [1, 2, 3],
        "changeCount": 3
      },
      "operations": [
        {
          "op": "ADD_VARIABLE",
          "cnName": "登录令牌",
          "enName": "token",
          "dataType": "STRING",
          "scope": "SCRIPT"
        },
        {
          "op": "ADD_EXTRACTOR",
          "stepId": 1,
          "source": "response_body",
          "expressionType": "JSONPATH",
          "expression": "$.data.token",
          "variableName": "token"
        },
        {
          "op": "REPLACE_HEADER",
          "stepId": 2,
          "headerName": "Authorization",
          "value": "Bearer ${token}"
        }
      ],
      "warnings": ["请确认登录接口响应中始终存在 $.data.token"]
    }
  ]
}
```

## 7. Intent 枚举

- EXTRACT_VARIABLE：变量提取。
- PARAMETERIZE：参数化。
- GENERATE_ASSERTIONS：生成断言。
- ANALYZE_DEPENDENCY：分析依赖。
- ANALYZE_FAILURE：分析失败。
- SUGGEST_CONDITION：推荐条件。
- SUGGEST_WAIT：推荐等待。
- CHECK_VARIABLES：检查变量。
- USE_FUNCTION：推荐平台函数。

## 8. Scope 枚举

- CURRENT_STEP：当前步骤。
- SELECTED_STEPS：选中步骤。
- WHOLE_SCRIPT：整个脚本。
- COMPONENT：某个组件。
- EXECUTION_RECORD：某次执行记录。
- FAILED_STEP：失败步骤。

默认规则：用户选中步骤则使用 SELECTED_STEPS；未选中则使用 CURRENT_STEP；明确说“整个脚本”时使用 WHOLE_SCRIPT；从执行记录进入时使用 EXECUTION_RECORD。

## 9. Patch Operations

第一阶段支持：

- ADD_VARIABLE
- ADD_EXTRACTOR
- ADD_ASSERTION
- REPLACE_BODY_PATH
- REPLACE_URL_PART
- REPLACE_HEADER
- REPLACE_QUERY
- USE_FUNCTION
- ADD_CONDITION
- INSERT_WAIT_STEP
- ANALYZE_FAILURE

平台负责 previewPatch、applyDraftPatch、validateDraft。AI 只生成 operations，不直接改库。

## 10. 工具接口清单

P0：

- getScriptStructure(scriptDraftId)
- getStepDetail(stepId)
- getSelectedSteps(stepIds)
- getVariableCenter(scriptDraftId)
- getResponseExample(stepId)
- parsePayload(rawBody, bodyType)
- scanVariables(content)
- previewPatch(scriptDraftId, operations)
- applyDraftPatch(scriptDraftId, operations)
- validateDraft(scriptDraftId)
- validateVariables(scriptDraftId)

P1：

- getExecutionRecord(runId)
- generateFieldPath(bodyType, selectedRange)
- detectHardcodedValues(stepIds)
- analyzeVariableDependency(scriptDraftId)
- validateExpression(expressionType, expression, sample)
- undoPatch(patchId)

## 11. 平台函数白名单

生成值函数：

- fn.uuid()
- fn.now()
- fn.timestamp()
- fn.randomInt(min,max)
- fn.randomString(length)
- fn.dateAdd(date, amount, unit)
- fn.md5(value)
- fn.base64(value)

变量处理函数：

- trim
- dateFormat
- numberFormat
- urlEncode
- base64

AI 不允许输出平台不支持的函数。

## 12. 校验规则

AI 建议被采纳后，保存或调试前由平台校验：

1. stepId 是否存在。
2. 字段路径是否存在。
3. JSONPath / XPath 是否合法。
4. 变量英文名是否重复。
5. 变量是否使用前产生。
6. 数据类型是否匹配。
7. 平台函数是否在白名单内。
8. 函数参数是否合法。
9. 断言表达式是否有效。
10. patch 操作是否可执行。

## 13. UI 交互要求

AI 助手以小窗口嵌入脚本编排页面，包含自然语言输入框、常用提示、建议列表、查看 Diff、采纳、全部采纳、忽略、重新生成。

建议列表展示：建议标题、建议说明、影响步骤、影响字段、新增变量、新增断言、修改内容、风险提示。

## 14. 典型样例

建议准备以下样例用于验收：

1. 登录响应提取 token 并应用到后续接口。
2. 选中步骤中的客户号参数化。
3. 给当前接口响应生成断言。
4. SQL 查询结果提取 customerId。
5. 执行失败：变量 applyId 为空导致 URL 错误。
6. 推荐将固定流水号替换为 fn.uuid()。

每个样例应包含用户输入、作用范围、输入上下文、期望 AI 输出、期望 patch operations、验收标准。

## 15. 职责分工

平台团队负责：脚本草稿模型、变量中心、fieldConfigs、执行记录、上下文查询接口、Patch 预览、Patch 应用、草稿校验、持久化。

AI 团队负责：意图识别、按范围处理、结构化 suggestions、Patch Operations 输出、失败分析、典型样例调试。

## 16. 验收标准

1. 能识别用户意图。
2. 能区分当前步骤、选中步骤、整个脚本、执行记录范围。
3. 能按需获取上下文。
4. 能输出结构化 suggestions。
5. 能输出平台支持的 operations。
6. 能生成变量提取、参数化、断言建议。
7. 能分析变量依赖问题。
8. 能基于执行记录分析失败原因。
9. 采纳前可预览差异。
10. 采纳后只修改草稿。
11. 平台校验不通过时能提示原因。
