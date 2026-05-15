package com.example.apitest.service.impl;

import com.example.apitest.entity.ExtractorConfig;
import com.example.apitest.repository.ExtractorConfigMapper;
import com.example.apitest.service.ExtractorConfigService;
public class ExtractorConfigServiceImpl implements ExtractorConfigService {

    private final ExtractorConfigMapper extractorConfigMapper;

    public ExtractorConfigServiceImpl(ExtractorConfigMapper extractorConfigMapper) {
        this.extractorConfigMapper = extractorConfigMapper;
    }

    @Override
    public ExtractorConfig getById(Long id) {
        return extractorConfigMapper.selectById(id);
    }
}
