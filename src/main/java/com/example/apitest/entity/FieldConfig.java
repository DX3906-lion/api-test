package com.example.apitest.entity;

public class FieldConfig extends BaseEntity {

    private Long draftId;
    private Long stepId;
    private String fieldPath;
    private String configType;
    private String paramMode;
    private String variableName;
    private String variableLabel;
    private String remark;

    public Long getDraftId() { return draftId; }
    public void setDraftId(Long draftId) { this.draftId = draftId; }
    public Long getStepId() { return stepId; }
    public void setStepId(Long stepId) { this.stepId = stepId; }
    public String getFieldPath() { return fieldPath; }
    public void setFieldPath(String fieldPath) { this.fieldPath = fieldPath; }
    public String getConfigType() { return configType; }
    public void setConfigType(String configType) { this.configType = configType; }
    public String getParamMode() { return paramMode; }
    public void setParamMode(String paramMode) { this.paramMode = paramMode; }
    public String getVariableName() { return variableName; }
    public void setVariableName(String variableName) { this.variableName = variableName; }
    public String getVariableLabel() { return variableLabel; }
    public void setVariableLabel(String variableLabel) { this.variableLabel = variableLabel; }
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
}