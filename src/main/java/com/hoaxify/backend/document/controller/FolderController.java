package com.hoaxify.backend.document.controller;

import com.hoaxify.backend.common.BaseResponse;
import com.hoaxify.backend.document.model.Folder;
import com.hoaxify.backend.document.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/folder", produces = "application/json")
public class FolderController {
    @Autowired
    private FolderService folderService;

    @GetMapping
    public ResponseEntity<BaseResponse> findAllParents() {
        List<Folder> parentFolders = folderService.findAllParents();
        return ResponseEntity.ok(BaseResponse.success(parentFolders, ""));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> findById(@RequestParam(name = "id") Long folderId) {
        Folder folder = folderService.findById(folderId);
        return ResponseEntity.ok(BaseResponse.success(folder, ""));
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody Folder folder) {
        Folder folderSaved = folderService.create(folder);
        return ResponseEntity.ok(BaseResponse.success(folderSaved, ""));
    }
}
