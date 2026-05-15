package com.example.apitest.controller;

import com.example.apitest.common.ApiResponse;
import com.example.apitest.dto.StepCreateRequest;
import com.example.apitest.dto.StepDetailSaveRequest;
import com.example.apitest.dto.StepOrderUpdateRequest;
import com.example.apitest.service.OrchestrationService;
import com.example.apitest.vo.DraftOrchestrationVO;
import com.example.apitest.vo.StepDetailVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/drafts/{draftId}")
public class OrchestrationController {
    private final OrchestrationService orchestrationService;
    public OrchestrationController(OrchestrationService orchestrationService) { this.orchestrationService = orchestrationService; }

    @GetMapping("/orchestration")
    public ApiResponse<DraftOrchestrationVO> getDraft(@PathVariable Long draftId){ return ApiResponse.success(orchestrationService.getDraftStructure(draftId)); }
    @PostMapping("/steps/api")
    public ApiResponse<StepDetailVO> addApi(@PathVariable Long draftId,@Valid @RequestBody StepCreateRequest req){ req.setStepType("API_STEP"); return ApiResponse.success(orchestrationService.addStep(draftId,req)); }
    @PostMapping("/steps/sql")
    public ApiResponse<StepDetailVO> addSql(@PathVariable Long draftId,@Valid @RequestBody StepCreateRequest req){ req.setStepType("SQL_STEP"); return ApiResponse.success(orchestrationService.addStep(draftId,req)); }
    @PutMapping("/steps/reorder")
    public ApiResponse<Void> reorder(@PathVariable Long draftId,@RequestBody StepOrderUpdateRequest req){ orchestrationService.updateStepOrder(draftId,req); return ApiResponse.success(); }
    @PostMapping("/steps/{stepId}/copy")
    public ApiResponse<StepDetailVO> copy(@PathVariable Long draftId,@PathVariable Long stepId){ return ApiResponse.success(orchestrationService.copyStep(draftId,stepId)); }
    @DeleteMapping("/steps/{stepId}")
    public ApiResponse<Void> del(@PathVariable Long draftId,@PathVariable Long stepId){ orchestrationService.deleteStep(draftId,stepId); return ApiResponse.success(); }
    @PutMapping("/steps/{stepId}/enabled")
    public ApiResponse<Void> enabled(@PathVariable Long draftId,@PathVariable Long stepId,@RequestParam Integer enabled){ orchestrationService.updateStepEnabled(draftId,stepId,enabled); return ApiResponse.success(); }
    @PutMapping("/steps/{stepId}/detail")
    public ApiResponse<Void> saveDetail(@PathVariable Long draftId,@PathVariable Long stepId,@RequestBody StepDetailSaveRequest req){ orchestrationService.saveStepDetail(draftId,stepId,req); return ApiResponse.success(); }
}
