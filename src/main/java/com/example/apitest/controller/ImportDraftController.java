package com.example.apitest.controller;

import com.example.apitest.common.ApiResponse;
import com.example.apitest.dto.ImportDraftRequest;
import com.example.apitest.service.ImportDraftService;
import com.example.apitest.vo.ImportDraftVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/imports")
public class ImportDraftController {

    private final ImportDraftService importDraftService;

    public ImportDraftController(ImportDraftService importDraftService) {
        this.importDraftService = importDraftService;
    }

    @PostMapping("/drafts")
    public ApiResponse<ImportDraftVO> importDraft(@Validated @RequestBody ImportDraftRequest request) {
        return ApiResponse.success(importDraftService.importDraft(request));
    }
}
