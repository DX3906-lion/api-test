package com.example.apitest.service.impl;

import com.example.apitest.dto.ImportDraftRequest;
import com.example.apitest.entity.FlowStep;
import com.example.apitest.entity.ScriptDraft;
import com.example.apitest.exception.BusinessException;
import com.example.apitest.repository.InMemoryFlowStepRepository;
import com.example.apitest.repository.InMemoryScriptDraftRepository;
import com.example.apitest.service.ImportDraftService;
import com.example.apitest.vo.FlowStepVO;
import com.example.apitest.vo.ImportDraftVO;
import com.example.apitest.vo.ScriptDraftDetailVO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImportDraftServiceImpl implements ImportDraftService {

    private final InMemoryScriptDraftRepository scriptDraftRepository;
    private final InMemoryFlowStepRepository flowStepRepository;

    public ImportDraftServiceImpl(InMemoryScriptDraftRepository scriptDraftRepository,
                                  InMemoryFlowStepRepository flowStepRepository) {
        this.scriptDraftRepository = scriptDraftRepository;
        this.flowStepRepository = flowStepRepository;
    }

    @Override
    public ImportDraftVO importDraft(ImportDraftRequest request) {
        validateImportRequest(request);

        ScriptDraft existed = scriptDraftRepository.findByWorkspaceIdAndDraftName(request.getWorkspaceId(), request.getDraftName());
        if (existed != null) {
            throw new BusinessException("DRAFT_NAME_DUPLICATED", "draft name already exists in current workspace");
        }

        ScriptDraft draft = new ScriptDraft();
        draft.setWorkspaceId(request.getWorkspaceId());
        draft.setDraftName(request.getDraftName());
        draft.setSourceType("IMPORT");
        draft.setImportFormat("HAR");
        draft.setStatus("DRAFT");
        draft.setRawFilePath("uploads/" + LocalDateTime.now().toString() + "_" + request.getFile().getOriginalFilename());
        draft.setDeleted(0);
        scriptDraftRepository.save(draft);

        FlowStep step = new FlowStep();
        step.setDraftId(draft.getId());
        step.setStepType("API_STEP");
        step.setStepName("imported_api_step_1");
        step.setStepOrder(1);
        step.setEnabled(1);
        step.setDeleted(0);
        flowStepRepository.save(step);

        ImportDraftVO vo = new ImportDraftVO();
        vo.setDraftId(draft.getId());
        vo.setDraftName(draft.getDraftName());
        vo.setImportFormat(draft.getImportFormat());
        vo.setStatus(draft.getStatus());
        return vo;
    }

    @Override
    public List<ImportDraftVO> listDrafts(Long workspaceId) {
        List<ScriptDraft> drafts = scriptDraftRepository.findByWorkspaceId(workspaceId);
        List<ImportDraftVO> result = new ArrayList<>();
        for (ScriptDraft draft : drafts) {
            ImportDraftVO vo = new ImportDraftVO();
            vo.setDraftId(draft.getId());
            vo.setDraftName(draft.getDraftName());
            vo.setImportFormat(draft.getImportFormat());
            vo.setStatus(draft.getStatus());
            result.add(vo);
        }
        return result;
    }

    @Override
    public ScriptDraftDetailVO getDraftDetail(Long draftId) {
        ScriptDraft draft = scriptDraftRepository.findById(draftId);
        if (draft == null || (draft.getDeleted() != null && draft.getDeleted() == 1)) {
            throw new BusinessException("DRAFT_NOT_FOUND", "draft not found");
        }

        ScriptDraftDetailVO detail = new ScriptDraftDetailVO();
        detail.setDraftId(draft.getId());
        detail.setDraftName(draft.getDraftName());
        detail.setStatus(draft.getStatus());
        detail.setSourceType(draft.getSourceType());

        List<FlowStepVO> steps = new ArrayList<>();
        for (FlowStep step : flowStepRepository.findByDraftId(draftId)) {
            FlowStepVO flowStepVO = new FlowStepVO();
            flowStepVO.setStepId(step.getId());
            flowStepVO.setStepType(step.getStepType());
            flowStepVO.setStepName(step.getStepName());
            flowStepVO.setStepOrder(step.getStepOrder());
            steps.add(flowStepVO);
        }
        detail.setSteps(steps);
        return detail;
    }

    @Override
    public void deleteDraft(Long draftId) {
        ScriptDraft draft = scriptDraftRepository.findById(draftId);
        if (draft == null || (draft.getDeleted() != null && draft.getDeleted() == 1)) {
            throw new BusinessException("DRAFT_NOT_FOUND", "draft not found");
        }
        draft.setDeleted(1);
        scriptDraftRepository.save(draft);
        flowStepRepository.deleteByDraftId(draftId);
    }

    private void validateImportRequest(ImportDraftRequest request) {
        String originalFileName = request.getFile().getOriginalFilename();
        if (originalFileName == null || originalFileName.trim().isEmpty()) {
            throw new BusinessException("FILE_NAME_INVALID", "file name is empty");
        }
        if (!originalFileName.endsWith(".har")) {
            throw new BusinessException("FILE_FORMAT_INVALID", "only .har file is supported currently");
        }
        try {
            String content = new String(request.getFile().getBytes(), "UTF-8");
            if (!content.contains("\"log\"") || !content.contains("\"entries\"")) {
                throw new BusinessException("HAR_ENTRIES_MISSING", "HAR must contain log.entries");
            }
        } catch (IOException e) {
            throw new BusinessException("FILE_READ_ERROR", "failed to read import file");
        }
    }
}
