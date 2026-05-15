package com.example.apitest.entity;

public class AssertionConfig extends BaseEntity {

    private Long draftId;
    private Long stepId;
    private String assertType;
    private String expression;
    private String expectedValue;
    private Integer enabled;

    public Long getDraftId() { return draftId; }
    public void setDraftId(Long draftId) { this.draftId = draftId; }
    public Long getStepId() { return stepId; }
    public void setStepId(Long stepId) { this.stepId = stepId; }
    public String getAssertType() { return assertType; }
    public void setAssertType(String assertType) { this.assertType = assertType; }
    public String getExpression() { return expression; }
    public void setExpression(String expression) { this.expression = expression; }
    public String getExpectedValue() { return expectedValue; }
    public void setExpectedValue(String expectedValue) { this.expectedValue = expectedValue; }
    public Integer getEnabled() { return enabled; }
    public void setEnabled(Integer enabled) { this.enabled = enabled; }
}