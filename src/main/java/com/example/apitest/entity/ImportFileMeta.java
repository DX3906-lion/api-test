package com.example.apitest.entity;

public class ImportFileMeta extends BaseEntity {
    private Long draftId;
    private Long workspaceId;
    private String originFileName;
    private String storagePath;
    private String importFormat;
    private Long fileSize;
    public Long getDraftId() { return draftId; }
    public void setDraftId(Long draftId) { this.draftId = draftId; }
    public Long getWorkspaceId() { return workspaceId; }
    public void setWorkspaceId(Long workspaceId) { this.workspaceId = workspaceId; }
    public String getOriginFileName() { return originFileName; }
    public void setOriginFileName(String originFileName) { this.originFileName = originFileName; }
    public String getStoragePath() { return storagePath; }
    public void setStoragePath(String storagePath) { this.storagePath = storagePath; }
    public String getImportFormat() { return importFormat; }
    public void setImportFormat(String importFormat) { this.importFormat = importFormat; }
    public Long getFileSize() { return fileSize; }
    public void setFileSize(Long fileSize) { this.fileSize = fileSize; }
}
