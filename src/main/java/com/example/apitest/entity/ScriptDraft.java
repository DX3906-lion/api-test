package com.example.apitest.entity;

public class ScriptDraft extends BaseEntity {

    private Long workspaceId;
    private String draftName;
    private String sourceType;
    private String importFormat;
    private String status;
    private String rawFilePath;

    public Long getWorkspaceId() { return workspaceId; }
    public void setWorkspaceId(Long workspaceId) { this.workspaceId = workspaceId; }
    public String getDraftName() { return draftName; }
    public void setDraftName(String draftName) { this.draftName = draftName; }
    public String getSourceType() { return sourceType; }
    public void setSourceType(String sourceType) { this.sourceType = sourceType; }
    public String getImportFormat() { return importFormat; }
    public void setImportFormat(String importFormat) { this.importFormat = importFormat; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getRawFilePath() { return rawFilePath; }
    public void setRawFilePath(String rawFilePath) { this.rawFilePath = rawFilePath; }
}