package com.hoaxify.backend.excel.controller;

import com.hoaxify.backend.excel.service.ExcelService;
import com.hoaxify.backend.util.ControllerUtil;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/excel-content")
@RestController
public class ExcelController {
  private ExcelService excelService;

  public ExcelController(ExcelService excelService) {
    this.excelService = excelService;
  }

  @GetMapping("/download")
  public ResponseEntity<Resource> downloadExcelFile() {
    final String fileName = "tutorial.xslx";
    InputStreamResource file = new InputStreamResource(excelService.loadExcelFile());
    return ControllerUtil.createDownloadableExcel(fileName, file);
  }

}
