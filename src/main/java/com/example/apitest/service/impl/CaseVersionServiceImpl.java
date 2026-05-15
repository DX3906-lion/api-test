package com.example.apitest.service.impl;

import com.example.apitest.entity.CaseVersion;
import com.example.apitest.repository.CaseVersionMapper;
import com.example.apitest.service.CaseVersionService;
public class CaseVersionServiceImpl implements CaseVersionService {

    private final CaseVersionMapper caseVersionMapper;

    public CaseVersionServiceImpl(CaseVersionMapper caseVersionMapper) {
        this.caseVersionMapper = caseVersionMapper;
    }

    @Override
    public CaseVersion getById(Long id) {
        return caseVersionMapper.selectById(id);
    }
}
