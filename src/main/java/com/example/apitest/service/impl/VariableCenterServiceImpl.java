package com.example.apitest.service.impl;

import com.example.apitest.dto.SetValueAsVariableRequest;
import com.example.apitest.dto.VariableUpsertRequest;
import com.example.apitest.entity.FieldConfig;
import com.example.apitest.entity.PayloadContent;
import com.example.apitest.entity.ScriptVariable;
import com.example.apitest.exception.BusinessException;
import com.example.apitest.repository.FieldConfigMapper;
import com.example.apitest.repository.PayloadContentMapper;
import com.example.apitest.repository.ScriptVariableMapper;
import com.example.apitest.service.VariableCenterService;
import com.example.apitest.vo.FieldConfigItemVO;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class VariableCenterServiceImpl implements VariableCenterService {
    private final ScriptVariableMapper scriptVariableMapper;
    private final FieldConfigMapper fieldConfigMapper;
    private final PayloadContentMapper payloadContentMapper;
    private static final Pattern VAR_PATTERN = Pattern.compile("\\$\\{([a-zA-Z0-9_.]+)\\}");
    public VariableCenterServiceImpl(ScriptVariableMapper scriptVariableMapper, FieldConfigMapper fieldConfigMapper, PayloadContentMapper payloadContentMapper) {
        this.scriptVariableMapper = scriptVariableMapper; this.fieldConfigMapper = fieldConfigMapper; this.payloadContentMapper = payloadContentMapper;
    }
    public List<ScriptVariable> listVariables(Long draftId) { return scriptVariableMapper.selectByDraftId(draftId); }
    public Long createVariable(Long draftId, VariableUpsertRequest request) {
        if (scriptVariableMapper.selectByDraftIdAndVarName(draftId, request.getVarName()) != null) throw new BusinessException("VAR_DUPLICATE","variable name duplicated");
        ScriptVariable v=new ScriptVariable(); v.setDraftId(draftId); v.setVarName(request.getVarName()); v.setVarLabel(request.getVarLabel()); v.setDataType(request.getDataType()); v.setDefaultValue(request.getDefaultValue()); v.setSourceType(request.getSourceType()); v.setScopeType(request.getScopeType()); v.setSourceExpression(request.getSourceExpression()); v.setDeleted(0); scriptVariableMapper.insert(v); return v.getId();
    }
    public void updateVariable(Long draftId, Long variableId, VariableUpsertRequest request) { ScriptVariable v=scriptVariableMapper.selectById(variableId); if (v==null) throw new BusinessException("VAR_NOT_FOUND","variable not found"); v.setVarLabel(request.getVarLabel()); v.setDataType(request.getDataType()); v.setDefaultValue(request.getDefaultValue()); v.setSourceType(request.getSourceType()); v.setScopeType(request.getScopeType()); v.setSourceExpression(request.getSourceExpression()); scriptVariableMapper.updateById(v); }
    public void deleteVariable(Long draftId, Long variableId) { scriptVariableMapper.logicalDelete(variableId); }
    public List<FieldConfigItemVO> listFieldConfigs(Long stepId) { List<FieldConfigItemVO> out=new ArrayList<>(); for(FieldConfig f:fieldConfigMapper.selectByStepId(stepId)){ FieldConfigItemVO i=new FieldConfigItemVO(); i.setFieldPath(f.getFieldPath()); i.setConfigType(f.getConfigType()); i.setParamMode(f.getParamMode()); i.setVariableName(f.getVariableName()); i.setVariableLabel(f.getVariableLabel()); i.setRemark(f.getRemark()); out.add(i);} return out; }
    public Long createFieldConfig(Long draftId, Long stepId, FieldConfigItemVO req) { FieldConfig f=new FieldConfig(); f.setDraftId(draftId); f.setStepId(stepId); f.setFieldPath(req.getFieldPath()); f.setConfigType(req.getConfigType()); f.setParamMode(req.getParamMode()); f.setVariableName(req.getVariableName()); f.setVariableLabel(req.getVariableLabel()); f.setRemark(req.getRemark()); fieldConfigMapper.insert(f); return f.getId(); }
    public void updateFieldConfig(Long fieldConfigId, FieldConfigItemVO req) { FieldConfig f=fieldConfigMapper.selectById(fieldConfigId); if (f==null) throw new BusinessException("FIELD_CONFIG_NOT_FOUND","field config not found"); f.setFieldPath(req.getFieldPath()); f.setConfigType(req.getConfigType()); f.setParamMode(req.getParamMode()); f.setVariableName(req.getVariableName()); f.setVariableLabel(req.getVariableLabel()); f.setRemark(req.getRemark()); fieldConfigMapper.updateById(f); }
    public void deleteFieldConfig(Long fieldConfigId) { fieldConfigMapper.logicalDeleteById(fieldConfigId); }
    public void setValueAsVariable(Long draftId, Long stepId, SetValueAsVariableRequest req) { VariableUpsertRequest v = new VariableUpsertRequest(); v.setVarName(req.getVarName()); v.setVarLabel(req.getVarLabel()); v.setDataType(req.getDataType()); v.setSourceType("MANUAL_SET"); v.setScopeType("SCRIPT"); createVariable(draftId, v); FieldConfigItemVO f=new FieldConfigItemVO(); f.setFieldPath(req.getFieldPath()); f.setConfigType("PARAM"); f.setParamMode("VARIABLE"); f.setVariableName(req.getVarName()); f.setVariableLabel(req.getVarLabel()); f.setRemark("selected value set as variable"); createFieldConfig(draftId, stepId, f); }
    public List<String> scanVariablesFromRawBody(Long stepId) { PayloadContent p=payloadContentMapper.selectByStepId(stepId); if (p==null||p.getRawBody()==null) return Collections.emptyList(); Matcher m=VAR_PATTERN.matcher(p.getRawBody()); Set<String> vars=new LinkedHashSet<>(); while(m.find()){ String v=m.group(1); if(!v.startsWith("fn.")) vars.add(v);} return new ArrayList<>(vars); }
    public void validateVariablesBeforeSave(Long draftId) { List<ScriptVariable> vars=scriptVariableMapper.selectByDraftId(draftId); Set<String> names=new HashSet<>(); for(ScriptVariable v:vars){ if(!names.add(v.getVarName())) throw new BusinessException("VAR_DUPLICATED","duplicated var: "+v.getVarName()); }
        // function marker is allowed: ${fn.uuid()}
    }
}
