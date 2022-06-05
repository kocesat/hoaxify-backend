package com.hoaxify.backend.document.service;

import com.hoaxify.backend.document.model.Document;
import com.hoaxify.backend.document.model.Folder;
import com.hoaxify.backend.document.model.dto.DocumentDto;
import com.hoaxify.backend.document.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private FolderService folderService;
    @Autowired
    EntityManagerFactory entityManagerFactory;


    public Document create(DocumentDto dto) {
        Folder folder = folderService.findById(dto.getParentFolderId());
        return documentRepository.save(Document.newInstance(dto, folder));
    }

    public List<Document> findByFolder(Long folderId) {
        return documentRepository.findByFolder(folderId);
    }

    public List<Document> findAll() {return documentRepository.findAll();
    }
}
