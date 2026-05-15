package com.example.apitest.repository;

import com.example.apitest.entity.CaseVersion;

public interface CaseVersionMapper {

    int insert(CaseVersion entity);

    CaseVersion selectById(Long id);
}
