package com.example.apitest.repository;

import com.example.apitest.entity.ExtractorConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExtractorConfigMapper {
    int insert(ExtractorConfig entity);
    int updateById(ExtractorConfig entity);
    ExtractorConfig selectById(Long id);
    List<ExtractorConfig> selectByStepId(@Param("stepId") Long stepId);
    int logicalDelete(@Param("id") Long id);
}
