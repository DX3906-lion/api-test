package com.example.apitest.service.impl;

import com.example.apitest.entity.AssertionConfig;
import com.example.apitest.repository.AssertionConfigMapper;
import com.example.apitest.service.AssertionConfigService;
public class AssertionConfigServiceImpl implements AssertionConfigService {

    private final AssertionConfigMapper assertionConfigMapper;

    public AssertionConfigServiceImpl(AssertionConfigMapper assertionConfigMapper) {
        this.assertionConfigMapper = assertionConfigMapper;
    }

    @Override
    public AssertionConfig getById(Long id) {
        return assertionConfigMapper.selectById(id);
    }
}
