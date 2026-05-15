package com.example.apitest.controller;

import com.example.apitest.common.ApiResponse;
import com.example.apitest.dto.ImportDraftRequest;
import com.example.apitest.service.ImportDraftService;
import com.example.apitest.vo.ImportDraftVO;
import com.example.apitest.vo.ScriptDraftDetailVO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/imports")
/**
 * 导入/草稿管理控制器，提供导入、列表、详情、删除接口。
 */
public class ImportDraftController {

    private final ImportDraftService importDraftService;

    public ImportDraftController(ImportDraftService importDraftService) {
        this.importDraftService = importDraftService;
    }

    /**
     * 导入文件并生成脚本草稿。
     */
    @PostMapping("/drafts")
    public ApiResponse<ImportDraftVO> importDraft(@Valid @ModelAttribute ImportDraftRequest request) {
        return ApiResponse.success(importDraftService.importDraft(request));
    }

    /**
     * 按工作空间查询草稿列表。
     */
    @GetMapping("/drafts")
    public ApiResponse<List<ImportDraftVO>> listDrafts(@RequestParam("workspaceId") Long workspaceId) {
        return ApiResponse.success(importDraftService.listDrafts(workspaceId));
    }

    /**
     * 查询单个草稿详情。
     */
    @GetMapping("/drafts/{draftId}")
    public ApiResponse<ScriptDraftDetailVO> getDraftDetail(@PathVariable("draftId") Long draftId) {
        return ApiResponse.success(importDraftService.getDraftDetail(draftId));
    }

    /**
     * 删除草稿（逻辑删除）。
     */
    @DeleteMapping("/drafts/{draftId}")
    public ApiResponse<Void> deleteDraft(@PathVariable("draftId") Long draftId) {
        importDraftService.deleteDraft(draftId);
        return ApiResponse.success();
    }
}
