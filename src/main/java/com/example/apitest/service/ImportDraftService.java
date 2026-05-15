package com.example.apitest.service;

import com.example.apitest.dto.ImportDraftRequest;
import com.example.apitest.vo.ImportDraftVO;

public interface ImportDraftService {

    ImportDraftVO importDraft(ImportDraftRequest request);
}
