package com.example.apitest.repository;

import com.example.apitest.entity.ExtractorConfig;

public interface ExtractorConfigMapper {

    int insert(ExtractorConfig entity);

    ExtractorConfig selectById(Long id);
}
