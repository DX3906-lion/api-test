package com.example.apitest.service.impl;

import com.example.apitest.service.HealthService;
import org.springframework.stereotype.Service;

@Service
public class HealthServiceImpl implements HealthService {

    @Override
    public String health() {
        return "success";
    }
}
