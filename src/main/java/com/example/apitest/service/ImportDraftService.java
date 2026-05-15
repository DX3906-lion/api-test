package com.example.apitest.service;

import com.example.apitest.dto.ImportDraftRequest;
import com.example.apitest.vo.ImportDraftVO;
import com.example.apitest.vo.ScriptDraftDetailVO;

import java.util.List;

public interface ImportDraftService {

    ImportDraftVO importDraft(ImportDraftRequest request);

    List<ImportDraftVO> listDrafts(Long workspaceId);

    ScriptDraftDetailVO getDraftDetail(Long draftId);

    void deleteDraft(Long draftId);
}
