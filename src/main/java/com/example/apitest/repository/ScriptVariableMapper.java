package com.example.apitest.repository;

import com.example.apitest.entity.ScriptVariable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScriptVariableMapper {
    int insert(ScriptVariable entity);
    int updateById(ScriptVariable entity);
    ScriptVariable selectById(Long id);
    List<ScriptVariable> selectByDraftId(@Param("draftId") Long draftId);
    ScriptVariable selectByDraftIdAndVarName(@Param("draftId") Long draftId, @Param("varName") String varName);
    int logicalDelete(@Param("id") Long id);
}
