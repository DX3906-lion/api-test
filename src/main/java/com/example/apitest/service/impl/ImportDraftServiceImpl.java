package com.example.apitest.service.impl;

import com.example.apitest.dto.ImportDraftRequest;
import com.example.apitest.service.ImportDraftService;
import com.example.apitest.vo.ImportDraftVO;
import org.springframework.stereotype.Service;

@Service
public class ImportDraftServiceImpl implements ImportDraftService {

    @Override
    public ImportDraftVO importDraft(ImportDraftRequest request) {
        ImportDraftVO vo = new ImportDraftVO();
        vo.setDraftId(1L);
        vo.setDraftName(request.getDraftName());
        vo.setImportFormat(request.getImportFormat().name());
        vo.setStatus("DRAFT_CREATED");
        return vo;
    }
}
