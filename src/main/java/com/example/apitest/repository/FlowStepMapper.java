package com.example.apitest.repository;

import com.example.apitest.entity.FlowStep;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FlowStepMapper {
    int insert(FlowStep entity);
    FlowStep selectById(@Param("id") Long id);
    List<FlowStep> selectByDraftId(@Param("draftId") Long draftId);
    int logicalDeleteByDraftId(@Param("draftId") Long draftId);
    int logicalDeleteById(@Param("id") Long id);
    int updateStepOrder(@Param("id") Long id, @Param("stepOrder") Integer stepOrder);
    int updateEnabled(@Param("id") Long id, @Param("enabled") Integer enabled);
}
