package com.example.apitest.service.impl;

import com.example.apitest.entity.FieldConfig;
import com.example.apitest.repository.FieldConfigMapper;
import com.example.apitest.service.FieldConfigService;
public class FieldConfigServiceImpl implements FieldConfigService {

    private final FieldConfigMapper fieldConfigMapper;

    public FieldConfigServiceImpl(FieldConfigMapper fieldConfigMapper) {
        this.fieldConfigMapper = fieldConfigMapper;
    }

    @Override
    public FieldConfig getById(Long id) {
        return fieldConfigMapper.selectById(id);
    }
}
