package com.example.apitest.parser;

import com.example.apitest.exception.BusinessException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Postman Collection 解析策略，校验扩展名和 item 关键结构。
 */
@Component
public class PostmanCollectionParser implements ImportParser {
    /**
     * {@inheritDoc}
     */
    @Override
    public String supportFormat() { return "POSTMAN_COLLECTION"; }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(MultipartFile file) {
        String name = file.getOriginalFilename();
        if (name == null || !name.endsWith(".json")) {
            throw new BusinessException("FILE_FORMAT_INVALID", "Postman collection must be .json");
        }
        try {
            String content = new String(file.getBytes(), "UTF-8");
            if (!content.contains("\"item\"")) {
                throw new BusinessException("POSTMAN_ITEM_MISSING", "Postman collection must contain item");
            }
        } catch (IOException e) {
            throw new BusinessException("FILE_READ_ERROR", "failed to read file");
        }
    }
}
