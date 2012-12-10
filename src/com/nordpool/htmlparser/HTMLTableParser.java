package com.nordpool.htmlparser;

import com.nordpool.dao.DayStatistic;
import com.nordpool.dao.ElectrostationStatistic;
import com.nordpool.dao.TimePrice;

import org.apache.commons.lang3.StringUtils;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class HTMLTableParser extends HTMLEditorKit.ParserCallback {

  private boolean encounteredATableRow = false;
  private boolean isTableHeader = false;
  private DayStatistic dayStatistic = new DayStatistic();
  private ParserSettings settings;
  private int rowCount = 0;
  private int columnCount = 0;
  private String timePeriod;
  List<ElectrostationStatistic> electrostationStatistics = new LinkedList<ElectrostationStatistic>();

  public HTMLTableParser(ParserSettings settings) {
    this.settings = settings;
    dayStatistic.setElectrostationStatistics(electrostationStatistics);
  }

  @Override
  public void handleText(char[] data, int pos) {
    String text = new String(data);

    if (StringUtils.isEmpty(text) || "\u00A0".equals(text)) {
      return;
    }

    if (encounteredATableRow) {
      TimePrice timePrice = null;
      if (columnCount == 0) {
        timePeriod = text;
      } else if (!isTableHeader) {
        timePrice = new TimePrice(timePeriod, text);
      }

      Date date;
      try {
        date = new SimpleDateFormat(settings.getDateFormat()).parse(text);
        dayStatistic.setDate(date);
      } catch (ParseException e) {
      }

      if (isTableHeader) {
        if (StringUtils.isEmpty(dayStatistic.getHeader())) {
          dayStatistic.setHeader(text);
        }

        if (StringUtils.isNotEmpty(text) && !"\u00A0".equals(text) && !text.equals(dayStatistic.getHeader())) {
          ElectrostationStatistic es = new ElectrostationStatistic();
          es.setName(text);
          electrostationStatistics.add(es);
        }
      }

      if (timePrice != null) {
        dayStatistic.getElectrostationStatistics().get(columnCount - 1).getTimePrices().add(timePrice);
      }
    }

    // System.out.println("text: " + text + "; rowCount: " + rowCount + "; columnCount: " + columnCount + "; isTableHeader: " + isTableHeader +
    // "; timePeriod= " + timePeriod);
    columnCount++;
  }

  @Override
  public void handleStartTag(HTML.Tag tag, MutableAttributeSet a, int pos) {
    if (tag == HTML.Tag.TR) {
      encounteredATableRow = true;
      rowCount++;
    } else if (tag == HTML.Tag.TH) {
      isTableHeader = true;
    }
  }

  @Override
  public void handleEndTag(HTML.Tag tag, int pos) {
    if (tag == HTML.Tag.TR) {
      encounteredATableRow = false;
      columnCount = 0;
    } else if (tag == HTML.Tag.TH) {
      isTableHeader = false;
    }
  }

  public DayStatistic getDayStatistic() {
    return dayStatistic;
  }

  public void setDayStatistic(DayStatistic dayStatistic) {
    this.dayStatistic = dayStatistic;
  }
}
