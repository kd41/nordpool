package com.nordpool.program;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileReader {
  public static String readFileAsString(String filename) {
    File file = new File(filename);
    try {
      byte[] bytes = Files.readAllBytes(file.toPath());
      return new String(bytes, "UTF-8");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "";
  }
}
