package com.example.apitest.parser;

import com.example.apitest.exception.BusinessException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * HAR 文件解析策略，校验扩展名和 log.entries 关键结构。
 */
@Component
public class HarImportParser implements ImportParser {
    /**
     * {@inheritDoc}
     */
    @Override
    public String supportFormat() { return "HAR"; }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(MultipartFile file) {
        String name = file.getOriginalFilename();
        if (name == null || !name.endsWith(".har")) {
            throw new BusinessException("FILE_FORMAT_INVALID", "HAR file must end with .har");
        }
        try {
            String content = new String(file.getBytes(), "UTF-8");
            if (!content.contains("\"log\"") || !content.contains("\"entries\"")) {
                throw new BusinessException("HAR_ENTRIES_MISSING", "HAR must contain log.entries");
            }
        } catch (IOException e) {
            throw new BusinessException("FILE_READ_ERROR", "failed to read file");
        }
    }
}
