package com.hoaxify.backend.document.service;

import com.hoaxify.backend.document.model.Folder;
import com.hoaxify.backend.document.repository.FolderRepository;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FolderService {
    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    EntityManagerFactory entityManagerFactory;

    public List<Folder> findAllParents() {
        //Obtain the entity manager for the current transaction
        EntityManagerHolder holder = (EntityManagerHolder) TransactionSynchronizationManager.getResource(entityManagerFactory);
        EntityManager entityManager = holder.getEntityManager();

        //Unwrap to get the underlying hibernate session
        Session hibernateSession = entityManager.unwrap(Session.class);

        //Configure your filters
        Filter activeFilter = hibernateSession.enableFilter("activeFilter");
        activeFilter.setParameter("isActive", 0);
        activeFilter.validate();
        return folderRepository.findAllByParentFolderIsNull();
    }

    public Folder create(Folder folder) {
        return folderRepository.save(folder);
    }

    public Folder findById(Long id) {
        return folderRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
