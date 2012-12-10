package com.nordpool.htmlparser;

public class ParserSettings {
  private String dateFormat;

  public ParserSettings(String dateFormat) {
    this.dateFormat = dateFormat;
  }

  public String getDateFormat() {
    return dateFormat;
  }

  public void setDateFormat(String dateFormat) {
    this.dateFormat = dateFormat;
  }
}
