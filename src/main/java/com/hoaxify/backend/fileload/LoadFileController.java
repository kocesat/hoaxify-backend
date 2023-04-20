package com.hoaxify.backend.fileload;

import com.hoaxify.backend.util.AppResourceUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fileload")
public class LoadFileController {

  @GetMapping("/json")
  public String loadJsonFile() {
    return AppResourceUtil.loadJsonAsString("json/example.json");
  }

  @GetMapping("/csv")
  public String loadCsvFile() {
    return AppResourceUtil.loadCsvAsString("csv/example.csv");
  }
}
