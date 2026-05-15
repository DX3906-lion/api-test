package com.example.apitest.service.impl;

import com.example.apitest.entity.PayloadContent;
import com.example.apitest.repository.PayloadContentMapper;
import com.example.apitest.service.PayloadContentService;
public class PayloadContentServiceImpl implements PayloadContentService {

    private final PayloadContentMapper payloadContentMapper;

    public PayloadContentServiceImpl(PayloadContentMapper payloadContentMapper) {
        this.payloadContentMapper = payloadContentMapper;
    }

    @Override
    public PayloadContent getById(Long id) {
        return payloadContentMapper.selectById(id);
    }
}
