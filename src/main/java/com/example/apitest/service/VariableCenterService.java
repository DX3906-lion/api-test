package com.example.apitest.service;

import com.example.apitest.dto.SetValueAsVariableRequest;
import com.example.apitest.dto.VariableUpsertRequest;
import com.example.apitest.vo.FieldConfigItemVO;
import com.example.apitest.vo.StepDetailVO;

import java.util.List;

public interface VariableCenterService {
    List<com.example.apitest.entity.ScriptVariable> listVariables(Long draftId);
    Long createVariable(Long draftId, VariableUpsertRequest request);
    void updateVariable(Long draftId, Long variableId, VariableUpsertRequest request);
    void deleteVariable(Long draftId, Long variableId);
    List<FieldConfigItemVO> listFieldConfigs(Long stepId);
    Long createFieldConfig(Long draftId, Long stepId, FieldConfigItemVO request);
    void updateFieldConfig(Long fieldConfigId, FieldConfigItemVO request);
    void deleteFieldConfig(Long fieldConfigId);
    void setValueAsVariable(Long draftId, Long stepId, SetValueAsVariableRequest request);
    List<String> scanVariablesFromRawBody(Long stepId);
    void validateVariablesBeforeSave(Long draftId);
}
