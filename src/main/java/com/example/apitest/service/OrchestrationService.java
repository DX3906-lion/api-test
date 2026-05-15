package com.example.apitest.service;

import com.example.apitest.dto.StepCreateRequest;
import com.example.apitest.dto.StepDetailSaveRequest;
import com.example.apitest.dto.StepOrderUpdateRequest;
import com.example.apitest.vo.DraftOrchestrationVO;
import com.example.apitest.vo.StepDetailVO;

public interface OrchestrationService {
    DraftOrchestrationVO getDraftStructure(Long draftId);
    StepDetailVO addStep(Long draftId, StepCreateRequest request);
    void updateStepOrder(Long draftId, StepOrderUpdateRequest request);
    StepDetailVO copyStep(Long draftId, Long stepId);
    void deleteStep(Long draftId, Long stepId);
    void updateStepEnabled(Long draftId, Long stepId, Integer enabled);
    void saveStepDetail(Long draftId, Long stepId, StepDetailSaveRequest request);
}
