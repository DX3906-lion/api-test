package com.example.apitest.vo;

import java.util.List;

public class DraftOrchestrationVO {
    private Long draftId;
    private String draftName;
    private List<StepDetailVO> steps;
    public Long getDraftId() { return draftId; }
    public void setDraftId(Long draftId) { this.draftId = draftId; }
    public String getDraftName() { return draftName; }
    public void setDraftName(String draftName) { this.draftName = draftName; }
    public List<StepDetailVO> getSteps() { return steps; }
    public void setSteps(List<StepDetailVO> steps) { this.steps = steps; }
}
