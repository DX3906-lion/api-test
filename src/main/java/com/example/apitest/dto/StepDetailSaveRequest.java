package com.example.apitest.dto;

import com.example.apitest.vo.FieldConfigItemVO;

import java.util.List;

public class StepDetailSaveRequest {
    private String rawBody;
    private String treeCache;
    private List<FieldConfigItemVO> fieldConfigs;
    public String getRawBody() { return rawBody; }
    public void setRawBody(String rawBody) { this.rawBody = rawBody; }
    public String getTreeCache() { return treeCache; }
    public void setTreeCache(String treeCache) { this.treeCache = treeCache; }
    public List<FieldConfigItemVO> getFieldConfigs() { return fieldConfigs; }
    public void setFieldConfigs(List<FieldConfigItemVO> fieldConfigs) { this.fieldConfigs = fieldConfigs; }
}
