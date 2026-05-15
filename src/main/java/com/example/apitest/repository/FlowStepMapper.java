package com.example.apitest.repository;

import com.example.apitest.entity.FlowStep;

public interface FlowStepMapper {

    int insert(FlowStep entity);

    FlowStep selectById(Long id);
}
