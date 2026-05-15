package com.example.apitest.repository;

import com.example.apitest.entity.PayloadContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PayloadContentMapper {
    int insert(PayloadContent entity);
    PayloadContent selectById(Long id);
    PayloadContent selectByStepId(@Param("stepId") Long stepId);
    int updateByStepId(PayloadContent entity);
}
