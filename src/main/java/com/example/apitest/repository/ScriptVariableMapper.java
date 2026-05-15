package com.example.apitest.repository;

import com.example.apitest.entity.ScriptVariable;

public interface ScriptVariableMapper {

    int insert(ScriptVariable entity);

    ScriptVariable selectById(Long id);
}
