package com.example.apitest.parser;

import org.springframework.web.multipart.MultipartFile;

public interface ImportParser {
    String supportFormat();
    void validate(MultipartFile file);
}
