package com.hoaxify.backend.excel.repo;

import com.hoaxify.backend.excel.model.ExcelContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcelContentRepo extends JpaRepository<ExcelContent, Long> {
}
