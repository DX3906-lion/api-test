package com.example.apitest.service.impl;

import com.example.apitest.entity.SystemWorkspace;
import com.example.apitest.repository.SystemWorkspaceMapper;
import com.example.apitest.service.SystemWorkspaceService;
public class SystemWorkspaceServiceImpl implements SystemWorkspaceService {

    private final SystemWorkspaceMapper systemWorkspaceMapper;

    public SystemWorkspaceServiceImpl(SystemWorkspaceMapper systemWorkspaceMapper) {
        this.systemWorkspaceMapper = systemWorkspaceMapper;
    }

    @Override
    public SystemWorkspace getById(Long id) {
        return systemWorkspaceMapper.selectById(id);
    }
}
