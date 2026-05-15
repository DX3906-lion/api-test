package com.example.apitest.repository;

import com.example.apitest.entity.ScriptDraft;

public interface ScriptDraftMapper {

    int insert(ScriptDraft entity);

    ScriptDraft selectById(Long id);
}
