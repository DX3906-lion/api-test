package com.example.apitest.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class StepCreateRequest {
    @NotBlank
    private String stepName;
    @NotBlank
    private String stepType;
    private String rawBody;
    private String treeCache;

    public String getStepName() { return stepName; }
    public void setStepName(String stepName) { this.stepName = stepName; }
    public String getStepType() { return stepType; }
    public void setStepType(String stepType) { this.stepType = stepType; }
    public String getRawBody() { return rawBody; }
    public void setRawBody(String rawBody) { this.rawBody = rawBody; }
    public String getTreeCache() { return treeCache; }
    public void setTreeCache(String treeCache) { this.treeCache = treeCache; }
}
