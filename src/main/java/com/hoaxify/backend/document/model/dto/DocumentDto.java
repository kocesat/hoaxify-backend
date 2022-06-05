package com.hoaxify.backend.document.model.dto;

import lombok.Data;

@Data
public class DocumentDto {
    private String name;
    private int active;
    private long parentFolderId;
}
