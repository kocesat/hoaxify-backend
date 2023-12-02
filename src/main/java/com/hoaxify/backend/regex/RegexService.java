package com.hoaxify.backend.regex;

import com.hoaxify.backend.param.ParameterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegexService {

  private static final String PARAM_SECTION = "HOAXIFY";
  private static final String PARAM_NAME = "INPUT_REGEX";
  private final ParameterService parameterService;

  public Boolean validateInput(String input) {
    log.info("InputRead: " + input);
    String regex = parameterService
      .getBySectionAndName(PARAM_SECTION, PARAM_NAME)
      .orElseThrow(() -> new IllegalStateException("param not found"));
    Pattern pattern = Pattern.compile(regex);
    int counter = 0;
    String line;
    boolean illegalCharFound = false;
    int startIndex = 0;
    try (BufferedReader reader = new BufferedReader(new StringReader(input))) {
      while ((line = reader.readLine()) != null) {
        counter++;
        Matcher matcher = pattern.matcher(line);
        illegalCharFound = matcher.find();
        if (illegalCharFound) {
          startIndex = matcher.start();
          String illegalChar = line.substring(startIndex, startIndex + 1);
          int illegalCharCodePoint = line.codePointAt(startIndex);
          String illegalCharRepr = Character.isSpaceChar(illegalCharCodePoint)
            ? Character.getName(illegalCharCodePoint)
            : illegalChar;
          log.info("IllegalChar: " + illegalCharRepr + " found at line: " + counter + " at index: " + startIndex);
          log.info(String.format("%d.satır, %d.index'te bulunan illegal karakter: %s", counter, startIndex, illegalCharRepr));
          break;
        }
      }
    } catch (Exception e) {
      throw new RuntimeException("file not read");
    }
    return !illegalCharFound;
  }

  public Boolean validateInput(InputStream inputStream) {
    String regex = parameterService
      .getBySectionAndName(PARAM_SECTION, PARAM_NAME)
      .orElseThrow(() -> new IllegalStateException("param not found"));
    Pattern pattern = Pattern.compile(regex);
    boolean illegalCharFound = false;
    try (BufferedReader reader = new BufferedReader(
      new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
      String line;
      int counter = 0;
      int startIndex;
      while ((line = reader.readLine()) != null) {
        counter++;
        Matcher matcher = pattern.matcher(line);
        illegalCharFound = matcher.find();
        if (illegalCharFound) {
          startIndex = matcher.start();
          String illegalChar = line.substring(startIndex, startIndex + 1);
          int illegalCharCodePoint = line.codePointAt(startIndex);
          String illegalCharRepr = Character.isSpaceChar(illegalCharCodePoint)
            ? Character.getName(illegalCharCodePoint)
            : illegalChar;
          log.info("IllegalChar: " + illegalCharRepr + " found at line: " + counter + " at index: " + startIndex);
          log.info(String.format("%d.satır, %d.index'te bulunan illegal karakter: %s", counter, startIndex, illegalCharRepr));
          break;
        }
      }
    } catch (Exception e) {
      throw new RuntimeException("file not read");
    }
    return !illegalCharFound;
  }
}
