package com.hoaxify.backend.excel.service;

import com.hoaxify.backend.excel.helper.ExcelHelper;
import com.hoaxify.backend.excel.repo.ExcelContentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
@RequiredArgsConstructor
public class ExcelService {
    private final ExcelContentRepo repo;

    public ByteArrayInputStream loadExcelFile() {
        var contents = repo.findAll();
        return ExcelHelper.contentsToExcel(contents);
    }

}
