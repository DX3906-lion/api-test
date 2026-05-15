package com.example.apitest.entity;

public class ScriptVariable extends BaseEntity {

    private Long draftId;
    private Long stepId;
    private String varName;
    private String varLabel;
    private String dataType;
    private String sourceType;
    private String scopeType;
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
    public String getSourceType() { return sourceType; }
    public void setSourceType(String sourceType) { this.sourceType = sourceType; }
    public String getScopeType() { return scopeType; }
    public void setScopeType(String scopeType) { this.scopeType = scopeType; }
    public String getSourceExpression() { return sourceExpression; }
    public void setSourceExpression(String sourceExpression) { this.sourceExpression = sourceExpression; }
}