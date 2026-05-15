package com.example.apitest.repository;

import com.example.apitest.entity.ImportFileMeta;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImportFileMetaMapper {
    int insert(ImportFileMeta entity);
}
