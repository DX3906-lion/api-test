package com.example.apitest.entity;

/**
 * 报文内容实体，对应 payload_content 表。
 */
public class PayloadContent extends BaseEntity {

    /** 所属草稿ID。 */
    private Long draftId;
    /** 所属步骤ID。 */
    private Long stepId;
    /** 报文类型。 */
    private String contentType;
    /** 可执行原始报文（事实源）。 */
    private String rawBody;
    /** 树结构缓存（仅展示用途）。 */
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
