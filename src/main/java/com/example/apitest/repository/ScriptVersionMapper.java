package com.example.apitest.repository;

import com.example.apitest.entity.ScriptVersion;

public interface ScriptVersionMapper {

    int insert(ScriptVersion entity);

    ScriptVersion selectById(Long id);
}
