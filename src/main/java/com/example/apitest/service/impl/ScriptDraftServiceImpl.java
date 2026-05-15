package com.example.apitest.service.impl;

import com.example.apitest.entity.ScriptDraft;
import com.example.apitest.repository.ScriptDraftMapper;
import com.example.apitest.service.ScriptDraftService;
public class ScriptDraftServiceImpl implements ScriptDraftService {

    private final ScriptDraftMapper scriptDraftMapper;

    public ScriptDraftServiceImpl(ScriptDraftMapper scriptDraftMapper) {
        this.scriptDraftMapper = scriptDraftMapper;
    }

    @Override
    public ScriptDraft getById(Long id) {
        return scriptDraftMapper.selectById(id);
    }
}
