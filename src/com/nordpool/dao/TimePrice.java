package com.nordpool.dao;

import java.io.Serializable;

public class TimePrice implements Serializable {
  private static final long serialVersionUID = 1L;

  private String timeRange;
  private Money price;

  public TimePrice(String timeRange, Money price) {
    this.timeRange = timeRange;
    this.price = price;
  }

  public TimePrice(String timeRange, String price) {
    this.timeRange = timeRange;
    this.price = new Money(price);
  }

  public String getTimeRange() {
    return timeRange;
  }

  public void setTimeRange(String timeRange) {
    this.timeRange = timeRange;
  }

  public Money getPrice() {
    return price;
  }

  public void setPrice(Money price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "TimePrice [timeRange=" + timeRange + "; price=" + price + "]";
  }
}
