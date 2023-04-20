package com.hoaxify.backend.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class AppResourceUtil {
  private AppResourceUtil() {
    throw new IllegalStateException("utility class");
  }

  public static String loadJsonAsString(String classPath) {
    String result = null;
    try (InputStream is = loadAsStream(classPath)) {
      result = inputStreamToString(is);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result;
  }

  public static String loadCsvAsString(String classPath) {
    String result = null;
    try (InputStream is = loadAsStream(classPath)) {
      result = inputStreamToString(is);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result;
  }

  public static InputStream loadAsStream(String classPath) throws IOException {
    Resource resource = new ClassPathResource(classPath);
    return resource.getInputStream();
  }

  private static String inputStreamToString(InputStream is) {
    StringBuilder sb = new StringBuilder();
    try (
      BufferedReader reader = new BufferedReader(new InputStreamReader(is))
    ) {
      String line;
      while ((line = reader.readLine()) != null){
        sb.append(line + System.getProperty("line.seperator"));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return sb.toString();
  }
}
