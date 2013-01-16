package com.nordpool.test;

import static org.junit.Assert.assertEquals;

import com.nordpool.frame.TableFrame;
import com.nordpool.htmlparser.HTMLTableParser;
import com.nordpool.htmlparser.ParserSettings;
import com.nordpool.program.FileReader;
import com.nordpool.program.Properties;
import com.nordpool.wrapper.DayStatisticWrapper;

import org.junit.Before;
import org.junit.Test;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

import java.io.Reader;
import java.io.StringReader;

public class ProgramTest {

  private Properties props;
  private HTMLTableParser tableParser;

  @Before
  public void prepare() throws Exception {
    props = new Properties();
    String filesPaht = props.getFilesLocationPath();
    String fileSource = FileReader.readFileAsString(filesPaht + "prices.html");
    tableParser = new HTMLTableParser(new ParserSettings(props.getDateFormat()));

    try (Reader reader = new StringReader(fileSource)) {
      HTMLEditorKit.Parser parser = new ParserDelegator();
      parser.parse(reader, tableParser, true);
    }

    DayStatisticWrapper dayStatWrapper = new DayStatisticWrapper(tableParser.getDayStatistic());

    TableFrame tableFrame = new TableFrame(tableParser.getDayStatistic().getHeader());
    tableFrame.setTitles(dayStatWrapper.getTableTitles());
    tableFrame.setData(dayStatWrapper.getTableData());
  }

  @Test
  public void testProperties() {
    String filesPaht = props.getFilesLocationPath();
    assertEquals(filesPaht, "files/");
  }

  @Test
  public void testTableHeader() {
    String tableHeader = tableParser.getDayStatistic().getHeader();
    assertEquals(tableHeader, "Elspot prices in [EUR/MWh]");
  }

  @Test
  public void testFirst() {
    String first = tableParser.getDayStatistic().getElectrostationStatistics().get(0).getTimePrices().get(0).getPrice().getAmount().toString();
    assertEquals(first, "51.11");
  }

  @Test
  public void testLast() {
    int electrostationStatisticsLast = tableParser.getDayStatistic().getElectrostationStatistics().size() - 1;
    int timePricesLast = tableParser.getDayStatistic().getElectrostationStatistics().get(electrostationStatisticsLast).getTimePrices().size() - 1;
    String last = tableParser.getDayStatistic().getElectrostationStatistics().get(electrostationStatisticsLast).getTimePrices().get(timePricesLast).getPrice().getAmount()
                             .toString();
    assertEquals(last, "35.04");
  }
}
