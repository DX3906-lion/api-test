package com.example.apitest.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class StepOrderUpdateRequest {
    @NotNull
    private List<Long> orderedStepIds;
    public List<Long> getOrderedStepIds() { return orderedStepIds; }
    public void setOrderedStepIds(List<Long> orderedStepIds) { this.orderedStepIds = orderedStepIds; }
}
