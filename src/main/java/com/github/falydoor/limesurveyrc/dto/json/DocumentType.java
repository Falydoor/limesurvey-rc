package com.github.falydoor.limesurveyrc.dto.json;

public enum DocumentType {
    JSON("json"), PDF("pdf"), CSV("csv"), DOC("doc"), XLS("xls");

    private DocumentType(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }
}
