package com.hoaxify.backend.document.controller;

import com.hoaxify.backend.common.BaseResponse;
import com.hoaxify.backend.document.model.Document;
import com.hoaxify.backend.document.model.dto.DocumentDto;
import com.hoaxify.backend.document.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/document", produces = "application/json")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody DocumentDto dto) {
        Document doc = documentService.create(dto);
        return ResponseEntity.ok(BaseResponse.success(doc, ""));
    }

    @GetMapping
    public ResponseEntity<BaseResponse> findAll() {
        return ResponseEntity.ok(BaseResponse.success(documentService.findAll(), ""));
    }
}
