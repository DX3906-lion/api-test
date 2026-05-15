package com.example.apitest.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ImportDraftRequest {

    @NotBlank(message = "draftName must not be blank")
    private String draftName;

    @NotNull(message = "importFormat must not be null")
    private ImportFormat importFormat;

    @NotBlank(message = "fileName must not be blank")
    private String fileName;

    public String getDraftName() {
        return draftName;
    }

    public void setDraftName(String draftName) {
        this.draftName = draftName;
    }

    public ImportFormat getImportFormat() {
        return importFormat;
    }

    public void setImportFormat(ImportFormat importFormat) {
        this.importFormat = importFormat;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
