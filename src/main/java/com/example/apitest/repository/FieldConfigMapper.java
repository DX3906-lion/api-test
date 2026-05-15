package com.example.apitest.repository;

import com.example.apitest.entity.FieldConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FieldConfigMapper {
    int insert(FieldConfig entity);
    int updateById(FieldConfig entity);
    FieldConfig selectById(Long id);
    List<FieldConfig> selectByStepId(@Param("stepId") Long stepId);
    int deleteByStepId(@Param("stepId") Long stepId);
    int logicalDeleteById(@Param("id") Long id);
}
