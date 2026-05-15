package com.example.apitest.service.impl;

import com.example.apitest.entity.FlowStep;
import com.example.apitest.repository.FlowStepMapper;
import com.example.apitest.service.FlowStepService;
public class FlowStepServiceImpl implements FlowStepService {

    private final FlowStepMapper flowStepMapper;

    public FlowStepServiceImpl(FlowStepMapper flowStepMapper) {
        this.flowStepMapper = flowStepMapper;
    }

    @Override
    public FlowStep getById(Long id) {
        return flowStepMapper.selectById(id);
    }
}
