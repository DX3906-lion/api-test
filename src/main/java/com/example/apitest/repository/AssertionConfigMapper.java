package com.example.apitest.repository;

import com.example.apitest.entity.AssertionConfig;

public interface AssertionConfigMapper {

    int insert(AssertionConfig entity);

    AssertionConfig selectById(Long id);
}
