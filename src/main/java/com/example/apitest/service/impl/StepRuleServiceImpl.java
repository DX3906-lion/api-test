package com.example.apitest.service.impl;

import com.example.apitest.entity.AssertionConfig;
import com.example.apitest.entity.ExtractorConfig;
import com.example.apitest.exception.BusinessException;
import com.example.apitest.repository.AssertionConfigMapper;
import com.example.apitest.repository.ExtractorConfigMapper;
import com.example.apitest.service.StepRuleService;
import com.example.apitest.vo.AssertionConfigItemVO;
import com.example.apitest.vo.ExtractorConfigItemVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StepRuleServiceImpl implements StepRuleService {
    private static final List<String> TYPES = Arrays.asList("JSONPath","XPath","HEADER","COOKIE","REGEX","SQL_RESULT");
    private final ExtractorConfigMapper extractorConfigMapper;
    private final AssertionConfigMapper assertionConfigMapper;
    public StepRuleServiceImpl(ExtractorConfigMapper extractorConfigMapper, AssertionConfigMapper assertionConfigMapper) {
        this.extractorConfigMapper = extractorConfigMapper; this.assertionConfigMapper = assertionConfigMapper;
    }
    public Long createExtractor(Long draftId, Long stepId, ExtractorConfigItemVO r) { validateExpressionFormat(r.getExtractType(), r.getExpression()); ExtractorConfig e=new ExtractorConfig(); e.setDraftId(draftId); e.setStepId(stepId); e.setExtractType(r.getExtractType()); e.setExpression(r.getExpression()); e.setTargetVarName(r.getTargetVarName()); e.setTargetVarLabel(r.getTargetVarLabel()); e.setEnabled(r.getEnabled()==null?1:r.getEnabled()); extractorConfigMapper.insert(e); return e.getId(); }
    public void updateExtractor(Long id, ExtractorConfigItemVO r) { ExtractorConfig e=extractorConfigMapper.selectById(id); if(e==null) throw new BusinessException("EXTRACTOR_NOT_FOUND","extractor not found"); validateExpressionFormat(r.getExtractType(), r.getExpression()); e.setExtractType(r.getExtractType()); e.setExpression(r.getExpression()); e.setTargetVarName(r.getTargetVarName()); e.setTargetVarLabel(r.getTargetVarLabel()); e.setEnabled(r.getEnabled()); extractorConfigMapper.updateById(e); }
    public void deleteExtractor(Long id) { extractorConfigMapper.logicalDelete(id); }
    public List<ExtractorConfigItemVO> listExtractors(Long stepId) { List<ExtractorConfigItemVO> out=new ArrayList<>(); for(ExtractorConfig e: extractorConfigMapper.selectByStepId(stepId)){ ExtractorConfigItemVO v=new ExtractorConfigItemVO(); v.setId(e.getId()); v.setExtractType(e.getExtractType()); v.setExpression(e.getExpression()); v.setTargetVarName(e.getTargetVarName()); v.setTargetVarLabel(e.getTargetVarLabel()); v.setEnabled(e.getEnabled()); out.add(v);} return out; }

    public Long createAssertion(Long draftId, Long stepId, AssertionConfigItemVO r) { validateExpressionFormat(r.getAssertType(), r.getExpression()); AssertionConfig a=new AssertionConfig(); a.setDraftId(draftId); a.setStepId(stepId); a.setAssertType(r.getAssertType()); a.setExpression(r.getExpression()); a.setExpectedValue(r.getExpectedValue()); a.setEnabled(r.getEnabled()==null?1:r.getEnabled()); assertionConfigMapper.insert(a); return a.getId(); }
    public void updateAssertion(Long id, AssertionConfigItemVO r) { AssertionConfig a=assertionConfigMapper.selectById(id); if(a==null) throw new BusinessException("ASSERTION_NOT_FOUND","assertion not found"); validateExpressionFormat(r.getAssertType(), r.getExpression()); a.setAssertType(r.getAssertType()); a.setExpression(r.getExpression()); a.setExpectedValue(r.getExpectedValue()); a.setEnabled(r.getEnabled()); assertionConfigMapper.updateById(a); }
    public void deleteAssertion(Long id) { assertionConfigMapper.logicalDelete(id); }
    public List<AssertionConfigItemVO> listAssertions(Long stepId) { List<AssertionConfigItemVO> out=new ArrayList<>(); for(AssertionConfig a: assertionConfigMapper.selectByStepId(stepId)){ AssertionConfigItemVO v=new AssertionConfigItemVO(); v.setId(a.getId()); v.setAssertType(a.getAssertType()); v.setExpression(a.getExpression()); v.setExpectedValue(a.getExpectedValue()); v.setEnabled(a.getEnabled()); out.add(v);} return out; }

    public void validateExpressionFormat(String type, String expression) {
        if (!TYPES.contains(type)) throw new BusinessException("TYPE_INVALID","unsupported type: "+type);
        if (expression == null || expression.trim().isEmpty()) throw new BusinessException("EXPR_EMPTY","expression is empty");
        if ("JSONPath".equals(type) && !expression.startsWith("$")) throw new BusinessException("EXPR_INVALID","JSONPath must start with $");
        if ("XPath".equals(type) && !expression.startsWith("/")) throw new BusinessException("EXPR_INVALID","XPath must start with /");
        if (("HEADER".equals(type) || "COOKIE".equals(type)) && expression.contains(" ")) throw new BusinessException("EXPR_INVALID","HEADER/COOKIE expression cannot contain spaces");
        if ("REGEX".equals(type) && expression.length() < 2) throw new BusinessException("EXPR_INVALID","REGEX too short");
    }
}
