package com.example.apitest.repository;

import com.example.apitest.entity.ScriptDraft;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScriptDraftMapper {
    int insert(ScriptDraft entity);
    ScriptDraft selectById(@Param("id") Long id);
    ScriptDraft selectByWorkspaceIdAndDraftName(@Param("workspaceId") Long workspaceId, @Param("draftName") String draftName);
    List<ScriptDraft> selectByWorkspaceId(@Param("workspaceId") Long workspaceId);
    int logicalDelete(@Param("id") Long id);
}
