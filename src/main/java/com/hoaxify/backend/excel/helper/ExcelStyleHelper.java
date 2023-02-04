package com.hoaxify.backend.excel.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExcelStyleHelper {

  public static Cell createStyledCell(Workbook wb, Row row, int colId, CellType cellType) {
    final Cell cell = row.createCell(colId, cellType);
    CellStyle cellStyle = getCellStyle(wb);
    cell.setCellStyle(cellStyle);
    return cell;
  }

  public static Cell createStyledHeaderCell(Workbook wb, Row row, int colId, CellType cellType) {
    final Cell cell = row.createCell(colId, cellType);
    CellStyle cellStyle = getHeaderCellStyle(wb);
    cell.setCellStyle(cellStyle);
    return cell;
  }

  private static CellStyle getCellStyle(Workbook wb) {
    CellStyle cellStyle = wb.createCellStyle();
    cellStyle.cloneStyleFrom(createBaseStyle(wb));
    cellStyle.setFillForegroundColor(IndexedColors.GOLD.getIndex());
    cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    cellStyle.setFont(createFontFor(wb));
    return cellStyle;
  }

  private static CellStyle getHeaderCellStyle(Workbook wb) {
    CellStyle cellStyle = wb.createCellStyle();
    cellStyle.cloneStyleFrom(createBaseStyle(wb));
    cellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
    cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    Font font = createFontFor(wb);
    font.setBold(true);
    cellStyle.setFont(font);
    return cellStyle;
  }

  private static Font createFontFor(Workbook wb) {
    Font font = wb.createFont();
    font.setFontHeightInPoints((short)12);
    font.setFontName("Courier New");
    font.setItalic(true);
    font.setBold(true);
    font.setColor(IndexedColors.TEAL.index);
    return font;
  }

  private static CellStyle createBaseStyle(Workbook wb) {
    CellStyle cellStyle = wb.createCellStyle();
    cellStyle.setAlignment(HorizontalAlignment.LEFT);
    cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    return cellStyle;
  }
}
