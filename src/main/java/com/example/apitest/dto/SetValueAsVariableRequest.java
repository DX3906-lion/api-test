package com.example.apitest.dto;

import javax.validation.constraints.NotBlank;

public class SetValueAsVariableRequest {
    @NotBlank
    private String fieldPath;
    @NotBlank
    private String varName;
    @NotBlank
    private String varLabel;
    @NotBlank
    private String dataType;
    public String getFieldPath() { return fieldPath; }
    public void setFieldPath(String fieldPath) { this.fieldPath = fieldPath; }
    public String getVarName() { return varName; }
    public void setVarName(String varName) { this.varName = varName; }
    public String getVarLabel() { return varLabel; }
    public void setVarLabel(String varLabel) { this.varLabel = varLabel; }
    public String getDataType() { return dataType; }
    public void setDataType(String dataType) { this.dataType = dataType; }
}
