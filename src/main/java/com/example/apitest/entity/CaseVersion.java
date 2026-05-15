package com.example.apitest.entity;

public class CaseVersion extends BaseEntity {

    private Long workspaceId;
    private String caseCode;
    private String caseName;
    private Long scriptVersionId;
    private Integer versionNo;
    private String status;

    public Long getWorkspaceId() { return workspaceId; }
    public void setWorkspaceId(Long workspaceId) { this.workspaceId = workspaceId; }
    public String getCaseCode() { return caseCode; }
    public void setCaseCode(String caseCode) { this.caseCode = caseCode; }
    public String getCaseName() { return caseName; }
    public void setCaseName(String caseName) { this.caseName = caseName; }
    public Long getScriptVersionId() { return scriptVersionId; }
    public void setScriptVersionId(Long scriptVersionId) { this.scriptVersionId = scriptVersionId; }
    public Integer getVersionNo() { return versionNo; }
    public void setVersionNo(Integer versionNo) { this.versionNo = versionNo; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}