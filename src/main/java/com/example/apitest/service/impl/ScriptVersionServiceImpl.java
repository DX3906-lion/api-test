package com.example.apitest.service.impl;

import com.example.apitest.entity.ScriptVersion;
import com.example.apitest.repository.ScriptVersionMapper;
import com.example.apitest.service.ScriptVersionService;
public class ScriptVersionServiceImpl implements ScriptVersionService {

    private final ScriptVersionMapper scriptVersionMapper;

    public ScriptVersionServiceImpl(ScriptVersionMapper scriptVersionMapper) {
        this.scriptVersionMapper = scriptVersionMapper;
    }

    @Override
    public ScriptVersion getById(Long id) {
        return scriptVersionMapper.selectById(id);
    }
}
