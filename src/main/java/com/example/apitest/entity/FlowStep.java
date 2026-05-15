package com.example.apitest.entity;

/**
 * 编排步骤实体，对应 flow_step 表。
 */
public class FlowStep extends BaseEntity {

    /** 所属草稿ID。 */
    private Long draftId;
    /** 父步骤ID（条件/循环场景可用）。 */
    private Long parentStepId;
    /** 步骤类型，如 API_STEP。 */
    private String stepType;
    /** 步骤名称。 */
    private String stepName;
    /** 步骤顺序。 */
    private Integer stepOrder;
    /** 是否启用：1启用，0禁用。 */
    private Integer enabled;
    /** 引用模式。 */
    private String refMode;
    /** 引用目标ID。 */
    private Long refTargetId;
    /** 引用版本。 */
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
