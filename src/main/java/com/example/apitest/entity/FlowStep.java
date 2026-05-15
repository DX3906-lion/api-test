package com.example.apitest.entity;

public class FlowStep extends BaseEntity {

    private Long draftId;
    private Long parentStepId;
    private String stepType;
    private String stepName;
    private Integer stepOrder;
    private Integer enabled;
    private String refMode;
    private Long refTargetId;
    private String refVersion;

    public Long getDraftId() { return draftId; }
    public void setDraftId(Long draftId) { this.draftId = draftId; }
    public Long getParentStepId() { return parentStepId; }
    public void setParentStepId(Long parentStepId) { this.parentStepId = parentStepId; }
    public String getStepType() { return stepType; }
    public void setStepType(String stepType) { this.stepType = stepType; }
    public String getStepName() { return stepName; }
    public void setStepName(String stepName) { this.stepName = stepName; }
    public Integer getStepOrder() { return stepOrder; }
    public void setStepOrder(Integer stepOrder) { this.stepOrder = stepOrder; }
    public Integer getEnabled() { return enabled; }
    public void setEnabled(Integer enabled) { this.enabled = enabled; }
    public String getRefMode() { return refMode; }
    public void setRefMode(String refMode) { this.refMode = refMode; }
    public Long getRefTargetId() { return refTargetId; }
    public void setRefTargetId(Long refTargetId) { this.refTargetId = refTargetId; }
    public String getRefVersion() { return refVersion; }
    public void setRefVersion(String refVersion) { this.refVersion = refVersion; }
}