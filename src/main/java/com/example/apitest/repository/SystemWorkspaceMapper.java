package com.example.apitest.repository;

import com.example.apitest.entity.SystemWorkspace;

public interface SystemWorkspaceMapper {

    int insert(SystemWorkspace entity);

    SystemWorkspace selectById(Long id);
}
