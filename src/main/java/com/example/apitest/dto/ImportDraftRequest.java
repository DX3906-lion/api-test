package com.example.apitest.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ImportDraftRequest {

    @NotNull(message = "workspaceId must not be null")
    private Long workspaceId;

    @NotBlank(message = "draftName must not be blank")
    private String draftName;

    @NotNull(message = "file must not be null")
    private MultipartFile file;

    public Long getWorkspaceId() { return workspaceId; }
    public void setWorkspaceId(Long workspaceId) { this.workspaceId = workspaceId; }
    public String getDraftName() { return draftName; }
    public void setDraftName(String draftName) { this.draftName = draftName; }
    public MultipartFile getFile() { return file; }
    public void setFile(MultipartFile file) { this.file = file; }
}
