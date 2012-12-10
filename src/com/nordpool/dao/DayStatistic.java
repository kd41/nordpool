package com.nordpool.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DayStatistic implements Serializable {
  private static final long serialVersionUID = 1L;

  private Date date;
  private String header;
  private List<ElectrostationStatistic> electrostationStatistics;

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public List<ElectrostationStatistic> getElectrostationStatistics() {
    return electrostationStatistics;
  }

  public void setElectrostationStatistics(List<ElectrostationStatistic> electrostationStatistics) {
    this.electrostationStatistics = electrostationStatistics;
  }

  @Override
  public String toString() {
    return "DayStatistic [date=" + date + "; header=" + header + "; electrostationStatistics=" + electrostationStatistics + "]";
  }

}
