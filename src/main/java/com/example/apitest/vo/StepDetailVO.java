package com.example.apitest.vo;

import java.util.List;

public class StepDetailVO {
    private Long stepId;
    private String stepType;
    private String stepName;
    private Integer stepOrder;
    private Integer enabled;
    private Long payloadId;
    private String rawBody;
    private String treeCache;
    private List<FieldConfigItemVO> fieldConfigs;
    private List<ExtractorConfigItemVO> extractors;
    private List<AssertionConfigItemVO> assertions;
    public Long getStepId() { return stepId; }
    public void setStepId(Long stepId) { this.stepId = stepId; }
    public String getStepType() { return stepType; }
    public void setStepType(String stepType) { this.stepType = stepType; }
    public String getStepName() { return stepName; }
    public void setStepName(String stepName) { this.stepName = stepName; }
    public Integer getStepOrder() { return stepOrder; }
    public void setStepOrder(Integer stepOrder) { this.stepOrder = stepOrder; }
    public Integer getEnabled() { return enabled; }
    public void setEnabled(Integer enabled) { this.enabled = enabled; }
    public Long getPayloadId() { return payloadId; }
    public void setPayloadId(Long payloadId) { this.payloadId = payloadId; }
    public String getRawBody() { return rawBody; }
    public void setRawBody(String rawBody) { this.rawBody = rawBody; }
    public String getTreeCache() { return treeCache; }
    public void setTreeCache(String treeCache) { this.treeCache = treeCache; }
    public List<FieldConfigItemVO> getFieldConfigs() { return fieldConfigs; }
    public void setFieldConfigs(List<FieldConfigItemVO> fieldConfigs) { this.fieldConfigs = fieldConfigs; }
    public List<ExtractorConfigItemVO> getExtractors() { return extractors; }
    public void setExtractors(List<ExtractorConfigItemVO> extractors) { this.extractors = extractors; }
    public List<AssertionConfigItemVO> getAssertions() { return assertions; }
    public void setAssertions(List<AssertionConfigItemVO> assertions) { this.assertions = assertions; }
}
