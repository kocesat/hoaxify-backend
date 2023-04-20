package com.hoaxify.backend.scratch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.Base64;

class FileReadTests {

  @Test
  void testBufferedReader () throws IOException {
    Resource resource = new ClassPathResource("example.txt");
    System.out.println(resource.getFilename());
    byte[] content = resource.getInputStream().readAllBytes();
    String line;
    String firstLine = null;
    int count = 0;
    try (
      InputStream is = new ByteArrayInputStream(content);
      BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    ) {
      while (count < 1 && (line = reader.readLine()) != null) {
        firstLine = line;
        count++;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    assert firstLine != null;
    String firstBlock = firstLine.substring(0, 9);
    Assertions.assertEquals("123456789", firstBlock);
    System.out.println(firstBlock);
  }

  @Test
  void testBase64() throws IOException {
    String expected = "123456789 incresing exampleDescriptionşİğç!'^^%&/()=?_-<>\n" +
      "987654321 decreasing exampleDescription&/()=?_-<>";
    Resource resource = new ClassPathResource("example.txt");
    var content = resource.getInputStream().readAllBytes();
    String base64Encoded = Base64.getEncoder().encodeToString(content);
    String result = new String(Base64.getDecoder().decode(base64Encoded));
    System.out.println(result);
  }
}
