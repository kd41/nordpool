package com.nordpool.program;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
  protected static Properties properties;
  protected static PropertiesLoader instance;
  private static final String RESOURCE_FILE_NAME = "project.properties";

  public PropertiesLoader() {
    properties = new Properties();
    FileInputStream is = null;
    try {
      is = new FileInputStream(RESOURCE_FILE_NAME);
      properties.load(is);
    } catch (FileNotFoundException e) {
    } catch (IOException e) {
      System.out.println(e);
    } finally {
      if (null != is) {
        try {
          is.close();
        } catch (IOException e) {
        }
      }
    }
  }

}
