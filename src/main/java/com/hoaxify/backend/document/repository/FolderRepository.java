package com.hoaxify.backend.document.repository;

import com.hoaxify.backend.document.model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {

    @Query("from Folder f where f.parentFolder is null")
    List<Folder> findAllByParentFolderIsNull();
}
