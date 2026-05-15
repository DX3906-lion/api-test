package com.example.apitest.repository;

import com.example.apitest.entity.PayloadContent;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayloadContentMapper {
    int insert(PayloadContent entity);
    PayloadContent selectById(Long id);
}
