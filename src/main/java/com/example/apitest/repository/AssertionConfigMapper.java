package com.example.apitest.repository;

import com.example.apitest.entity.AssertionConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AssertionConfigMapper {
    int insert(AssertionConfig entity);
    int updateById(AssertionConfig entity);
    AssertionConfig selectById(Long id);
    List<AssertionConfig> selectByStepId(@Param("stepId") Long stepId);
    int logicalDelete(@Param("id") Long id);
}
