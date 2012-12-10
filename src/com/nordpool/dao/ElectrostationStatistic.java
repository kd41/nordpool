package com.nordpool.dao;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ElectrostationStatistic implements Serializable {
  private static final long serialVersionUID = 1L;

  private String name;
  private List<TimePrice> timePrices = new LinkedList<TimePrice>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<TimePrice> getTimePrices() {
    return timePrices;
  }

  public void setTimePrices(List<TimePrice> timePrices) {
    this.timePrices = timePrices;
  }

  @Override
  public String toString() {
    return "\nElectrostationStatistic [name=" + name + "; timePrices=" + timePrices + "]";
  }
}
