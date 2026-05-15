package com.example.apitest.entity;

public class PayloadContent extends BaseEntity {

    private Long draftId;
    private Long stepId;
    private String contentType;
    private String rawBody;
    private String treeCache;

    public Long getDraftId() { return draftId; }
    public void setDraftId(Long draftId) { this.draftId = draftId; }
    public Long getStepId() { return stepId; }
    public void setStepId(Long stepId) { this.stepId = stepId; }
    public String getContentType() { return contentType; }
    public void setContentType(String contentType) { this.contentType = contentType; }
    public String getRawBody() { return rawBody; }
    public void setRawBody(String rawBody) { this.rawBody = rawBody; }
    public String getTreeCache() { return treeCache; }
    public void setTreeCache(String treeCache) { this.treeCache = treeCache; }
}