package com.hoaxify.backend.approval.repository;

import com.hoaxify.backend.approval.model.ApprovalDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalDetailRepository extends JpaRepository<ApprovalDetail, Long> {
}
