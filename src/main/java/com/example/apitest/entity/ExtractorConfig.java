package com.example.apitest.entity;

public class ExtractorConfig extends BaseEntity {

    private Long draftId;
    private Long stepId;
    private String extractType;
    private String expression;
    private String targetVarName;
    private String targetVarLabel;
    private Integer enabled;

    public Long getDraftId() { return draftId; }
    public void setDraftId(Long draftId) { this.draftId = draftId; }
    public Long getStepId() { return stepId; }
    public void setStepId(Long stepId) { this.stepId = stepId; }
    public String getExtractType() { return extractType; }
    public void setExtractType(String extractType) { this.extractType = extractType; }
    public String getExpression() { return expression; }
    public void setExpression(String expression) { this.expression = expression; }
    public String getTargetVarName() { return targetVarName; }
    public void setTargetVarName(String targetVarName) { this.targetVarName = targetVarName; }
    public String getTargetVarLabel() { return targetVarLabel; }
    public void setTargetVarLabel(String targetVarLabel) { this.targetVarLabel = targetVarLabel; }
    public Integer getEnabled() { return enabled; }
    public void setEnabled(Integer enabled) { this.enabled = enabled; }
}