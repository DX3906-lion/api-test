package com.example.apitest.repository;

import com.example.apitest.entity.PayloadContent;

public interface PayloadContentMapper {

    int insert(PayloadContent entity);

    PayloadContent selectById(Long id);
}
