package com.example.apitest.service.impl;

import com.example.apitest.dto.ImportDraftRequest;
import com.example.apitest.entity.FlowStep;
import com.example.apitest.entity.PayloadContent;
import com.example.apitest.entity.ImportFileMeta;
import com.example.apitest.entity.ScriptDraft;
import com.example.apitest.exception.BusinessException;
import com.example.apitest.parser.ImportParser;
import com.example.apitest.repository.FlowStepMapper;
import com.example.apitest.repository.PayloadContentMapper;
import com.example.apitest.repository.ImportFileMetaMapper;
import com.example.apitest.repository.ScriptDraftMapper;
import com.example.apitest.service.ImportDraftService;
import com.example.apitest.vo.FlowStepVO;
import com.example.apitest.vo.ImportDraftVO;
import com.example.apitest.vo.ScriptDraftDetailVO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
/**
 * 导入草稿服务实现。
 * 负责按导入格式校验文件并落库 script_draft/flow_step/payload_content 等最小闭环数据。
 */
public class ImportDraftServiceImpl implements ImportDraftService {

    private final ScriptDraftMapper scriptDraftMapper;
    private final FlowStepMapper flowStepMapper;
    private final PayloadContentMapper payloadContentMapper;
    private final ImportFileMetaMapper importFileMetaMapper;
    private final Map<String, ImportParser> parserMap = new HashMap<>();

    public ImportDraftServiceImpl(ScriptDraftMapper scriptDraftMapper,
                                  FlowStepMapper flowStepMapper,
                                  PayloadContentMapper payloadContentMapper,
                                  ImportFileMetaMapper importFileMetaMapper,
                                  List<ImportParser> parsers) {
        this.scriptDraftMapper = scriptDraftMapper;
        this.flowStepMapper = flowStepMapper;
        this.payloadContentMapper = payloadContentMapper;
        this.importFileMetaMapper = importFileMetaMapper;
        for (ImportParser parser : parsers) {
            parserMap.put(parser.supportFormat(), parser);
        }
    }

    @Override
    /**
     * 导入文件并创建草稿、基础步骤与报文占位。
     */
    public ImportDraftVO importDraft(ImportDraftRequest request) {
        if (request.getFile().isEmpty()) {
            throw new BusinessException("FILE_EMPTY", "import file is empty");
        }
        ImportParser parser = parserMap.get(request.getImportFormat().name());
        if (parser == null) {
            throw new BusinessException("FORMAT_NOT_SUPPORTED", "unsupported import format");
        }
        parser.validate(request.getFile());

        ScriptDraft existed = scriptDraftMapper.selectByWorkspaceIdAndDraftName(request.getWorkspaceId(), request.getDraftName());
        if (existed != null) {
            throw new BusinessException("DRAFT_NAME_DUPLICATED", "draft name already exists in current workspace");
        }

        ScriptDraft draft = new ScriptDraft();
        draft.setWorkspaceId(request.getWorkspaceId());
        draft.setDraftName(request.getDraftName());
        draft.setSourceType("IMPORT");
        draft.setImportFormat(request.getImportFormat().name());
        draft.setStatus("DRAFT");
        draft.setRawFilePath("uploads/" + LocalDateTime.now().toString() + "_" + request.getFile().getOriginalFilename());
        draft.setDeleted(0);
        scriptDraftMapper.insert(draft);

        ImportFileMeta meta = new ImportFileMeta();
        meta.setDraftId(draft.getId());
        meta.setWorkspaceId(request.getWorkspaceId());
        meta.setOriginFileName(request.getFile().getOriginalFilename());
        meta.setStoragePath(draft.getRawFilePath());
        meta.setImportFormat(request.getImportFormat().name());
        meta.setFileSize(request.getFile().getSize());
        importFileMetaMapper.insert(meta);

        FlowStep step = new FlowStep();
        step.setDraftId(draft.getId());
        step.setStepType("API_STEP");
        step.setStepName("imported_api_step_1");
        step.setStepOrder(1);
        step.setEnabled(1);
        step.setDeleted(0);
        flowStepMapper.insert(step);

        PayloadContent payload = new PayloadContent();
        payload.setDraftId(draft.getId());
        payload.setStepId(step.getId());
        payload.setContentType("APPLICATION_JSON");
        payload.setRawBody("{}");
        payload.setTreeCache("{}");
        payload.setDeleted(0);
        payloadContentMapper.insert(payload);

        ImportDraftVO vo = new ImportDraftVO();
        vo.setDraftId(draft.getId()); vo.setDraftName(draft.getDraftName());
        vo.setImportFormat(draft.getImportFormat()); vo.setStatus(draft.getStatus());
        return vo;
    }

    @Override
    /**
     * 查询指定工作空间下的草稿列表。
     */
    public List<ImportDraftVO> listDrafts(Long workspaceId) {
        List<ScriptDraft> drafts = scriptDraftMapper.selectByWorkspaceId(workspaceId);
        List<ImportDraftVO> result = new ArrayList<>();
        for (ScriptDraft draft : drafts) {
            ImportDraftVO vo = new ImportDraftVO();
            vo.setDraftId(draft.getId()); vo.setDraftName(draft.getDraftName()); vo.setImportFormat(draft.getImportFormat()); vo.setStatus(draft.getStatus());
            result.add(vo);
        }
        return result;
    }

    @Override
    /**
     * 查询草稿详情及其步骤信息。
     */
    public ScriptDraftDetailVO getDraftDetail(Long draftId) {
        ScriptDraft draft = scriptDraftMapper.selectById(draftId);
        if (draft == null || (draft.getDeleted() != null && draft.getDeleted() == 1)) throw new BusinessException("DRAFT_NOT_FOUND", "draft not found");
        ScriptDraftDetailVO detail = new ScriptDraftDetailVO();
        detail.setDraftId(draft.getId()); detail.setDraftName(draft.getDraftName()); detail.setStatus(draft.getStatus()); detail.setSourceType(draft.getSourceType());
        List<FlowStepVO> steps = new ArrayList<>();
        for (FlowStep step : flowStepMapper.selectByDraftId(draftId)) {
            FlowStepVO vo = new FlowStepVO(); vo.setStepId(step.getId()); vo.setStepType(step.getStepType()); vo.setStepName(step.getStepName()); vo.setStepOrder(step.getStepOrder()); steps.add(vo);
        }
        detail.setSteps(steps);
        return detail;
    }

    @Override
    /**
     * 删除草稿及其步骤（逻辑删除）。
     */
    public void deleteDraft(Long draftId) {
        ScriptDraft draft = scriptDraftMapper.selectById(draftId);
        if (draft == null || (draft.getDeleted() != null && draft.getDeleted() == 1)) throw new BusinessException("DRAFT_NOT_FOUND", "draft not found");
        scriptDraftMapper.logicalDelete(draftId);
        flowStepMapper.logicalDeleteByDraftId(draftId);
    }
}
