package com.example.apitest.dto;

import javax.validation.constraints.NotBlank;

public class VariableUpsertRequest {
    @NotBlank
    private String varName;
    @NotBlank
    private String varLabel;
    @NotBlank
    private String dataType;
    private String defaultValue;
    private String sourceType;
    private String scopeType;
    private String sourceExpression;
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
