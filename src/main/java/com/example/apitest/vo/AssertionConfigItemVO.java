package com.example.apitest.vo;

public class AssertionConfigItemVO {
    private Long id;
    private String assertType;
    private String expression;
    private String expectedValue;
    private Integer enabled;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAssertType() { return assertType; }
    public void setAssertType(String assertType) { this.assertType = assertType; }
    public String getExpression() { return expression; }
    public void setExpression(String expression) { this.expression = expression; }
    public String getExpectedValue() { return expectedValue; }
    public void setExpectedValue(String expectedValue) { this.expectedValue = expectedValue; }
    public Integer getEnabled() { return enabled; }
    public void setEnabled(Integer enabled) { this.enabled = enabled; }
}
