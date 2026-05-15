package com.example.apitest.service;

import com.example.apitest.vo.AssertionConfigItemVO;
import com.example.apitest.vo.ExtractorConfigItemVO;

import java.util.List;

public interface StepRuleService {
    Long createExtractor(Long draftId, Long stepId, ExtractorConfigItemVO request);
    void updateExtractor(Long id, ExtractorConfigItemVO request);
    void deleteExtractor(Long id);
    List<ExtractorConfigItemVO> listExtractors(Long stepId);

    Long createAssertion(Long draftId, Long stepId, AssertionConfigItemVO request);
    void updateAssertion(Long id, AssertionConfigItemVO request);
    void deleteAssertion(Long id);
    List<AssertionConfigItemVO> listAssertions(Long stepId);

    void validateExpressionFormat(String type, String expression);
}
