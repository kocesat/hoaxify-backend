package com.hoaxify.backend.approval.repository;

import com.hoaxify.backend.approval.enums.ApprovalStatus;
import com.hoaxify.backend.approval.model.Approval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApprovalRepository extends JpaRepository<Approval, Long> {

    List<Approval> findByStatusIn(List<ApprovalStatus> statuses);

    @Override
    @Query("select distinct a from Approval a join fetch a.detailList where a.id = :id")
    Optional<Approval> findById(Long id);
}
