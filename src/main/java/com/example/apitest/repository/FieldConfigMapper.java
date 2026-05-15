package com.example.apitest.repository;

import com.example.apitest.entity.FieldConfig;

public interface FieldConfigMapper {

    int insert(FieldConfig entity);

    FieldConfig selectById(Long id);
}
