package com.example.apitest.service.impl;

import com.example.apitest.entity.ScriptVariable;
import com.example.apitest.repository.ScriptVariableMapper;
import com.example.apitest.service.ScriptVariableService;
public class ScriptVariableServiceImpl implements ScriptVariableService {

    private final ScriptVariableMapper scriptVariableMapper;

    public ScriptVariableServiceImpl(ScriptVariableMapper scriptVariableMapper) {
        this.scriptVariableMapper = scriptVariableMapper;
    }

    @Override
    public ScriptVariable getById(Long id) {
        return scriptVariableMapper.selectById(id);
    }
}
