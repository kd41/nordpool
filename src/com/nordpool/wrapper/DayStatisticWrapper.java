package com.nordpool.wrapper;

import com.nordpool.dao.DayStatistic;

import java.util.Arrays;

public class DayStatisticWrapper {
  private Object[][] tableData;
  private DayStatistic dayStatistic;

  public DayStatisticWrapper(DayStatistic dayStatistic) {
    this.dayStatistic = dayStatistic;
  }

  public String[] getTableTitles() {
    String[] titles = Arrays.copyOf(getTableData2()[0], getTableData2()[0].length, String[].class);
    // System.out.println("titles" + titles);
    return titles;
  }

  public String[][] getTableData() {
    Object[][] data = getTableData2();
    int rows = data.length - 1;
    int colums = data[0].length;
    String[][] returnedData = new String[rows][colums];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < colums; j++) {
        returnedData[i][j] = data[i + 1][j].toString();
        // System.out.println("returnedData[" + i + "][" + j + "]" + returnedData[i][j]);
      }
    }

    return returnedData;
  }

  private Object[][] getTableData2() {
    if (tableData == null) {
      int rows = dayStatistic.getElectrostationStatistics().get(0).getTimePrices().size() + 1;
      int columns = dayStatistic.getElectrostationStatistics().size() + 1;
      Object[][] data = new Object[rows][columns];
      data[0][0] = "";
      for (int i = 0; i < dayStatistic.getElectrostationStatistics().size(); i++) {
        data[0][i + 1] = dayStatistic.getElectrostationStatistics().get(i).getName();
        if (i == 0) {
          for (int j = 0; j < dayStatistic.getElectrostationStatistics().get(i).getTimePrices().size(); j++) {
            data[j + 1][0] = dayStatistic.getElectrostationStatistics().get(i).getTimePrices().get(j).getTimeRange();
          }
        }
        for (int j = 0; j < dayStatistic.getElectrostationStatistics().get(i).getTimePrices().size(); j++) {
          data[j + 1][i + 1] = dayStatistic.getElectrostationStatistics().get(i).getTimePrices().get(j).getPrice().getAmount();
        }
      }
      tableData = data;
    }

    return tableData;
  }

}
