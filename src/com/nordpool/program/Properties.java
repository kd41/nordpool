package com.nordpool.program;

public class Properties extends PropertiesLoader {

  private static String NORDPOOL_URL_KEY = "nordpool.url";
  private static String FILES_LOCATION_PATH_KEY = "files.location.path";
  private static String DATE_FORMAT_KEY = "date.format";

  public String getNordPoolURL() {
    return properties.getProperty(NORDPOOL_URL_KEY);
  }

  public String getFilesLocationPath() {
    return properties.getProperty(FILES_LOCATION_PATH_KEY);
  }

  public String getDateFormat() {
    return properties.getProperty(DATE_FORMAT_KEY);
  }
}
