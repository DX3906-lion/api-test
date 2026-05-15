package com.example.apitest.controller;

import com.example.apitest.common.ApiResponse;
import com.example.apitest.service.StepRuleService;
import com.example.apitest.vo.AssertionConfigItemVO;
import com.example.apitest.vo.ExtractorConfigItemVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drafts/{draftId}/steps/{stepId}")
public class StepRuleController {
    private final StepRuleService stepRuleService;
    public StepRuleController(StepRuleService stepRuleService) { this.stepRuleService = stepRuleService; }

    @GetMapping("/extractors")
    public ApiResponse<List<ExtractorConfigItemVO>> listExtractors(@PathVariable Long stepId){ return ApiResponse.success(stepRuleService.listExtractors(stepId)); }
    @PostMapping("/extractors")
    public ApiResponse<Long> createExtractor(@PathVariable Long draftId,@PathVariable Long stepId,@RequestBody ExtractorConfigItemVO req){ return ApiResponse.success(stepRuleService.createExtractor(draftId,stepId,req)); }
    @PutMapping("/extractors/{id}")
    public ApiResponse<Void> updateExtractor(@PathVariable Long id,@RequestBody ExtractorConfigItemVO req){ stepRuleService.updateExtractor(id,req); return ApiResponse.success(); }
    @DeleteMapping("/extractors/{id}")
    public ApiResponse<Void> deleteExtractor(@PathVariable Long id){ stepRuleService.deleteExtractor(id); return ApiResponse.success(); }

    @GetMapping("/assertions")
    public ApiResponse<List<AssertionConfigItemVO>> listAssertions(@PathVariable Long stepId){ return ApiResponse.success(stepRuleService.listAssertions(stepId)); }
    @PostMapping("/assertions")
    public ApiResponse<Long> createAssertion(@PathVariable Long draftId,@PathVariable Long stepId,@RequestBody AssertionConfigItemVO req){ return ApiResponse.success(stepRuleService.createAssertion(draftId,stepId,req)); }
    @PutMapping("/assertions/{id}")
    public ApiResponse<Void> updateAssertion(@PathVariable Long id,@RequestBody AssertionConfigItemVO req){ stepRuleService.updateAssertion(id,req); return ApiResponse.success(); }
    @DeleteMapping("/assertions/{id}")
    public ApiResponse<Void> deleteAssertion(@PathVariable Long id){ stepRuleService.deleteAssertion(id); return ApiResponse.success(); }
}
