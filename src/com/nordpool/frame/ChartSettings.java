package com.nordpool.frame;

public class ChartSettings {

  private String title;
  private double[][] data;
  private String startDate;
  private String endDate;

  public ChartSettings() {
  }

  public ChartSettings(String title, double[][] data, String startDate, String endDate) {
    this.title = title;
    this.data = data;
    this.startDate = getReplacedDate(startDate);
    this.endDate = getReplacedDate(endDate);
  }

  public double[][] getData() {
    if (data == null) {
      double[][] defaultData = { { 0.1, 0.2, 0.3 }, { 1, 2, 3 } };
      return defaultData;
    }

    return data;
  }

  public void setData(double[][] data) {
    this.data = data;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBetween() {
    return getStartDate() + "-" + getEndDate();
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = getReplacedDate(startDate);
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = getReplacedDate(endDate);
  }

  private String getReplacedDate(String date) {
    return date.replaceAll("-", ".");
  }
}
