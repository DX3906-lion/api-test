package com.example.apitest.entity;

/**
 * 脚本草稿实体，对应 script_draft 表。
 */
public class ScriptDraft extends BaseEntity {

    /** 工作空间ID（系统维度隔离）。 */
    private Long workspaceId;
    /** 草稿名称。 */
    private String draftName;
    /** 来源类型，例如 IMPORT/BLANK。 */
    private String sourceType;
    /** 导入格式，例如 HAR/POSTMAN_COLLECTION。 */
    private String importFormat;
    /** 草稿状态。 */
    private String status;
    /** 原始导入文件存储路径。 */
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
