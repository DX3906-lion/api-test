package com.example.apitest.service.impl;

import com.example.apitest.dto.StepCreateRequest;
import com.example.apitest.dto.StepDetailSaveRequest;
import com.example.apitest.dto.StepOrderUpdateRequest;
import com.example.apitest.entity.FieldConfig;
import com.example.apitest.entity.FlowStep;
import com.example.apitest.entity.PayloadContent;
import com.example.apitest.entity.ScriptDraft;
import com.example.apitest.exception.BusinessException;
import com.example.apitest.repository.FieldConfigMapper;
import com.example.apitest.repository.FlowStepMapper;
import com.example.apitest.repository.PayloadContentMapper;
import com.example.apitest.repository.ScriptDraftMapper;
import com.example.apitest.service.OrchestrationService;
import com.example.apitest.vo.DraftOrchestrationVO;
import com.example.apitest.vo.FieldConfigItemVO;
import com.example.apitest.vo.StepDetailVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrchestrationServiceImpl implements OrchestrationService {
    private final ScriptDraftMapper scriptDraftMapper;
    private final FlowStepMapper flowStepMapper;
    private final PayloadContentMapper payloadContentMapper;
    private final FieldConfigMapper fieldConfigMapper;
    public OrchestrationServiceImpl(ScriptDraftMapper scriptDraftMapper, FlowStepMapper flowStepMapper,
                                    PayloadContentMapper payloadContentMapper, FieldConfigMapper fieldConfigMapper) {
        this.scriptDraftMapper = scriptDraftMapper; this.flowStepMapper = flowStepMapper;
        this.payloadContentMapper = payloadContentMapper; this.fieldConfigMapper = fieldConfigMapper;
    }
    public DraftOrchestrationVO getDraftStructure(Long draftId) {
        ScriptDraft draft = scriptDraftMapper.selectById(draftId);
        if (draft == null || (draft.getDeleted()!=null && draft.getDeleted()==1)) throw new BusinessException("DRAFT_NOT_FOUND","draft not found");
        DraftOrchestrationVO vo = new DraftOrchestrationVO(); vo.setDraftId(draftId); vo.setDraftName(draft.getDraftName());
        List<StepDetailVO> steps = new ArrayList<>();
        for (FlowStep s: flowStepMapper.selectByDraftId(draftId)) steps.add(toStepDetail(s));
        vo.setSteps(steps); return vo;
    }
    public StepDetailVO addStep(Long draftId, StepCreateRequest request) {
        FlowStep step = new FlowStep(); step.setDraftId(draftId); step.setStepName(request.getStepName()); step.setStepType(request.getStepType());
        step.setEnabled(1); step.setDeleted(0); step.setStepOrder(flowStepMapper.selectByDraftId(draftId).size()+1); flowStepMapper.insert(step);
        PayloadContent p = new PayloadContent(); p.setDraftId(draftId); p.setStepId(step.getId()); p.setContentType("APPLICATION_JSON");
        p.setRawBody(request.getRawBody()==null?"{}":request.getRawBody()); p.setTreeCache(request.getTreeCache()==null?"{}":request.getTreeCache()); p.setDeleted(0); payloadContentMapper.insert(p);
        return toStepDetail(step);
    }
    public void updateStepOrder(Long draftId, StepOrderUpdateRequest request) {
        int i=1; for (Long id: request.getOrderedStepIds()) { flowStepMapper.updateStepOrder(id,i++); }
    }
    public StepDetailVO copyStep(Long draftId, Long stepId) {
        FlowStep src = flowStepMapper.selectById(stepId); if (src==null) throw new BusinessException("STEP_NOT_FOUND","step not found");
        FlowStep target = new FlowStep(); target.setDraftId(draftId); target.setStepType(src.getStepType()); target.setStepName(src.getStepName()+"_copy");
        target.setEnabled(src.getEnabled()); target.setDeleted(0); target.setStepOrder(flowStepMapper.selectByDraftId(draftId).size()+1); flowStepMapper.insert(target);
        PayloadContent srcP = payloadContentMapper.selectByStepId(stepId);
        PayloadContent targetP = new PayloadContent(); targetP.setDraftId(draftId); targetP.setStepId(target.getId()); targetP.setContentType(srcP==null?"APPLICATION_JSON":srcP.getContentType());
        targetP.setRawBody(srcP==null?"{}":srcP.getRawBody()); targetP.setTreeCache(srcP==null?"{}":srcP.getTreeCache()); targetP.setDeleted(0); payloadContentMapper.insert(targetP);
        List<FieldConfig> fields = fieldConfigMapper.selectByStepId(stepId);
        for (FieldConfig f: fields) { FieldConfig nf = new FieldConfig(); nf.setDraftId(draftId); nf.setStepId(target.getId()); nf.setFieldPath(f.getFieldPath()); nf.setConfigType(f.getConfigType()); nf.setParamMode(f.getParamMode()); nf.setVariableName(f.getVariableName()); nf.setVariableLabel(f.getVariableLabel()); nf.setRemark(f.getRemark()); fieldConfigMapper.insert(nf);}        
        return toStepDetail(target);
    }
    public void deleteStep(Long draftId, Long stepId) { flowStepMapper.logicalDeleteById(stepId); }
    public void updateStepEnabled(Long draftId, Long stepId, Integer enabled) { flowStepMapper.updateEnabled(stepId, enabled); }
    public void saveStepDetail(Long draftId, Long stepId, StepDetailSaveRequest request) {
        PayloadContent p = new PayloadContent(); p.setStepId(stepId); p.setRawBody(request.getRawBody()); p.setTreeCache(request.getTreeCache()); payloadContentMapper.updateByStepId(p);
        fieldConfigMapper.deleteByStepId(stepId);
        if (request.getFieldConfigs()!=null) for (FieldConfigItemVO f: request.getFieldConfigs()) { FieldConfig entity=new FieldConfig(); entity.setDraftId(draftId); entity.setStepId(stepId); entity.setFieldPath(f.getFieldPath()); entity.setConfigType(f.getConfigType()); entity.setParamMode(f.getParamMode()); entity.setVariableName(f.getVariableName()); entity.setVariableLabel(f.getVariableLabel()); entity.setRemark(f.getRemark()); fieldConfigMapper.insert(entity);}    }

    private StepDetailVO toStepDetail(FlowStep s) {
        StepDetailVO vo = new StepDetailVO(); vo.setStepId(s.getId()); vo.setStepName(s.getStepName()); vo.setStepType(s.getStepType()); vo.setStepOrder(s.getStepOrder()); vo.setEnabled(s.getEnabled());
        PayloadContent p = payloadContentMapper.selectByStepId(s.getId()); if (p!=null) { vo.setPayloadId(p.getId()); vo.setRawBody(p.getRawBody()); vo.setTreeCache(p.getTreeCache()); }
        List<FieldConfigItemVO> fields = new ArrayList<>();
        for (FieldConfig f: fieldConfigMapper.selectByStepId(s.getId())) { FieldConfigItemVO i = new FieldConfigItemVO(); i.setFieldPath(f.getFieldPath()); i.setConfigType(f.getConfigType()); i.setParamMode(f.getParamMode()); i.setVariableName(f.getVariableName()); i.setVariableLabel(f.getVariableLabel()); i.setRemark(f.getRemark()); fields.add(i);} vo.setFieldConfigs(fields);
        return vo;
    }
}
