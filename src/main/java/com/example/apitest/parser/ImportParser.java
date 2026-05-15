package com.example.apitest.parser;

import org.springframework.web.multipart.MultipartFile;

/**
 * 导入解析策略接口。
 * 不同格式（HAR / Postman Collection）通过不同实现完成校验逻辑。
 */
public interface ImportParser {
    /**
     * 返回当前策略支持的导入格式标识。
     */
    String supportFormat();

    /**
     * 校验上传文件是否满足格式要求。
     */
    void validate(MultipartFile file);
}
