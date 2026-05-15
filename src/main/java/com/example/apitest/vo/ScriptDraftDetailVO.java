package com.example.apitest.vo;

import java.util.List;

public class ScriptDraftDetailVO {
    private Long draftId;
    private String draftName;
    private String status;
    private String sourceType;
    private List<FlowStepVO> steps;

    public Long getDraftId() { return draftId; }
    public void setDraftId(Long draftId) { this.draftId = draftId; }
    public String getDraftName() { return draftName; }
    public void setDraftName(String draftName) { this.draftName = draftName; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getSourceType() { return sourceType; }
    public void setSourceType(String sourceType) { this.sourceType = sourceType; }
    public List<FlowStepVO> getSteps() { return steps; }
    public void setSteps(List<FlowStepVO> steps) { this.steps = steps; }
}
