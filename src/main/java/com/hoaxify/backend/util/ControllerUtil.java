package com.hoaxify.backend.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ControllerUtil {

  public static ResponseEntity<Resource> createDownloadableContent(
    String fileName, InputStreamResource file) {
    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s", fileName))
      .contentType(MediaType.APPLICATION_OCTET_STREAM)
      .body(file);
  }

  public static ResponseEntity<Resource> createDownloadableExcel(
    String fileName, InputStreamResource file) {
    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s", fileName))
      .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
      .body(file);
  }
}
