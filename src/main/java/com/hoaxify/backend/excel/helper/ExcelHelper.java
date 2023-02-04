package com.hoaxify.backend.excel.helper;

import com.hoaxify.backend.excel.model.ExcelContent;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static com.hoaxify.backend.excel.helper.ExcelStyleHelper.createStyledCell;
import static com.hoaxify.backend.excel.helper.ExcelStyleHelper.createStyledHeaderCell;

public final class ExcelHelper {
    static String[] HEADERS = { "Id", "Title", "Description", "Published" };
    static String SHEET_NAME = "Contents";

    public static ByteArrayInputStream contentsToExcel(List<ExcelContent> contentList) {
        try (
          Workbook wb = new XSSFWorkbook();
          ByteArrayOutputStream out = new ByteArrayOutputStream();
          ) {
            Sheet sheet = wb.createSheet(SHEET_NAME);
            // header
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < HEADERS.length; col++) {
                Cell cell = createStyledHeaderCell(wb, headerRow, col, CellType.STRING);
                cell.setCellValue(HEADERS[col]);
            }

            final var rowId = new AtomicInteger();
            rowId.set(1);
            contentList.forEach(content -> {
                Row row = sheet.createRow(rowId.getAndIncrement());
                createStyledCell(wb, row, 0, CellType.NUMERIC).setCellValue(content.getId());
                createStyledCell(wb, row, 1, CellType.STRING).setCellValue(content.getTitle());
                createStyledCell(wb, row, 2, CellType.STRING).setCellValue(content.getDescription());
                createStyledCell(wb, row, 3, CellType.BOOLEAN).setCellValue(content.isPublished());
            });
            adjustColumnWidths(sheet, HEADERS.length);
            wb.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to excel file: " + e.getMessage());
        }
    }

  private static void adjustColumnWidths(Sheet sheet, int columnCount) {
    IntStream.range(0, columnCount)
      .forEach(sheet::autoSizeColumn);
  }
}
