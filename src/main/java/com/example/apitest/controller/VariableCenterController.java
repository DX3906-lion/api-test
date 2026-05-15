package com.example.apitest.controller;

import com.example.apitest.common.ApiResponse;
import com.example.apitest.dto.SetValueAsVariableRequest;
import com.example.apitest.dto.VariableUpsertRequest;
import com.example.apitest.entity.ScriptVariable;
import com.example.apitest.service.VariableCenterService;
import com.example.apitest.vo.FieldConfigItemVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/drafts/{draftId}")
public class VariableCenterController {
    private final VariableCenterService variableCenterService;
    public VariableCenterController(VariableCenterService variableCenterService) { this.variableCenterService = variableCenterService; }

    @GetMapping("/variables")
    public ApiResponse<List<ScriptVariable>> listVariables(@PathVariable Long draftId){ return ApiResponse.success(variableCenterService.listVariables(draftId)); }
    @PostMapping("/variables")
    public ApiResponse<Long> createVariable(@PathVariable Long draftId,@Valid @RequestBody VariableUpsertRequest request){ return ApiResponse.success(variableCenterService.createVariable(draftId, request)); }
    @PutMapping("/variables/{variableId}")
    public ApiResponse<Void> updateVariable(@PathVariable Long draftId,@PathVariable Long variableId,@Valid @RequestBody VariableUpsertRequest request){ variableCenterService.updateVariable(draftId, variableId, request); return ApiResponse.success(); }
    @DeleteMapping("/variables/{variableId}")
    public ApiResponse<Void> deleteVariable(@PathVariable Long draftId,@PathVariable Long variableId){ variableCenterService.deleteVariable(draftId, variableId); return ApiResponse.success(); }

    @GetMapping("/steps/{stepId}/field-configs")
    public ApiResponse<List<FieldConfigItemVO>> listFieldConfigs(@PathVariable Long stepId){ return ApiResponse.success(variableCenterService.listFieldConfigs(stepId)); }
    @PostMapping("/steps/{stepId}/field-configs")
    public ApiResponse<Long> createFieldConfig(@PathVariable Long draftId,@PathVariable Long stepId,@RequestBody FieldConfigItemVO request){ return ApiResponse.success(variableCenterService.createFieldConfig(draftId, stepId, request)); }
    @PutMapping("/field-configs/{fieldConfigId}")
    public ApiResponse<Void> updateFieldConfig(@PathVariable Long fieldConfigId,@RequestBody FieldConfigItemVO request){ variableCenterService.updateFieldConfig(fieldConfigId, request); return ApiResponse.success(); }
    @DeleteMapping("/field-configs/{fieldConfigId}")
    public ApiResponse<Void> deleteFieldConfig(@PathVariable Long fieldConfigId){ variableCenterService.deleteFieldConfig(fieldConfigId); return ApiResponse.success(); }

    @PostMapping("/steps/{stepId}/set-as-variable")
    public ApiResponse<Void> setAsVariable(@PathVariable Long draftId,@PathVariable Long stepId,@Valid @RequestBody SetValueAsVariableRequest request){ variableCenterService.setValueAsVariable(draftId, stepId, request); return ApiResponse.success(); }
    @GetMapping("/steps/{stepId}/scan-variables")
    public ApiResponse<List<String>> scanVariables(@PathVariable Long stepId){ return ApiResponse.success(variableCenterService.scanVariablesFromRawBody(stepId)); }
    @PostMapping("/variables/validate")
    public ApiResponse<Void> validateVariables(@PathVariable Long draftId){ variableCenterService.validateVariablesBeforeSave(draftId); return ApiResponse.success(); }
}
