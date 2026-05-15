package com.example.apitest.entity;

public class ScriptVersion extends BaseEntity {

    private Long workspaceId;
    private String scriptCode;
    private String scriptName;
    private Integer versionNo;
    private Long sourceDraftId;
    private String status;

    public Long getWorkspaceId() { return workspaceId; }
    public void setWorkspaceId(Long workspaceId) { this.workspaceId = workspaceId; }
    public String getScriptCode() { return scriptCode; }
    public void setScriptCode(String scriptCode) { this.scriptCode = scriptCode; }
    public String getScriptName() { return scriptName; }
    public void setScriptName(String scriptName) { this.scriptName = scriptName; }
    public Integer getVersionNo() { return versionNo; }
    public void setVersionNo(Integer versionNo) { this.versionNo = versionNo; }
    public Long getSourceDraftId() { return sourceDraftId; }
    public void setSourceDraftId(Long sourceDraftId) { this.sourceDraftId = sourceDraftId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}