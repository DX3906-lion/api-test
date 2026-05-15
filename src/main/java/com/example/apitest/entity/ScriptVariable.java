package com.example.apitest.entity;

/**
 * 脚本变量实体。
 */
public class ScriptVariable extends BaseEntity {

    /** 所属草稿ID。 */
    private Long draftId;
    /** 来源步骤ID（可空）。 */
    private Long stepId;
    /** 变量英文名。 */
    private String varName;
    /** 变量中文名。 */
    private String varLabel;
    /** 数据类型。 */
    private String dataType;
    /** 默认值。 */
    private String defaultValue;
    /** 来源类型。 */
    private String sourceType;
    /** 作用域。 */
    private String scopeType;
    /** 来源表达式。 */
    private String sourceExpression;

    public Long getDraftId() { return draftId; }
    public void setDraftId(Long draftId) { this.draftId = draftId; }
    public Long getStepId() { return stepId; }
    public void setStepId(Long stepId) { this.stepId = stepId; }
    public String getVarName() { return varName; }
    public void setVarName(String varName) { this.varName = varName; }
    public String getVarLabel() { return varLabel; }
    public void setVarLabel(String varLabel) { this.varLabel = varLabel; }
    public String getDataType() { return dataType; }
    public void setDataType(String dataType) { this.dataType = dataType; }
    public String getDefaultValue() { return defaultValue; }
    public void setDefaultValue(String defaultValue) { this.defaultValue = defaultValue; }
    public String getSourceType() { return sourceType; }
    public void setSourceType(String sourceType) { this.sourceType = sourceType; }
    public String getScopeType() { return scopeType; }
    public void setScopeType(String scopeType) { this.scopeType = scopeType; }
    public String getSourceExpression() { return sourceExpression; }
    public void setSourceExpression(String sourceExpression) { this.sourceExpression = sourceExpression; }
}
