package com.example.apitest.entity;

/**
 * 导入文件元信息实体，对应 import_file_meta 表。
 */
public class ImportFileMeta extends BaseEntity {
    /** 关联草稿ID。 */
    private Long draftId;
    /** 工作空间ID。 */
    private Long workspaceId;
    /** 原始文件名。 */
    private String originFileName;
    /** 存储路径。 */
    private String storagePath;
    /** 导入格式。 */
    private String importFormat;
    /** 文件大小（字节）。 */
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
