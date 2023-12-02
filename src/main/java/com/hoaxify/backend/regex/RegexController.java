package com.hoaxify.backend.regex;

import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regex")
public class RegexController {

  private final RegexService regexService;

  @GetMapping("/validate")
  public Boolean validateInput(@RequestParam("input") String input) {
    return regexService.validateInput(input);
  }

  @PostMapping("/validate-file")
  public Boolean validateFile(@RequestParam("file") MultipartFile file) {
    try {
      return regexService.validateInput(file.getInputStream());
    } catch (IOException e) {
      throw new RuntimeException("file not read successfully");
    }
  }
}
