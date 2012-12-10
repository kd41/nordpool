package com.nordpool.program;

import com.nordpool.frame.TableFrame;
import com.nordpool.htmlparser.HTMLTableParser;
import com.nordpool.htmlparser.ParserSettings;
import com.nordpool.wrapper.DayStatisticWrapper;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

import java.io.Reader;
import java.io.StringReader;

public class Program {
  public static void main(String... args) throws Exception {
    Properties props = new Properties();
    String filesPaht = props.getFilesLocationPath();

    String fileSource = FileReader.readFileAsString(filesPaht + "prices.html");

    // System.out.println(fileSource);

    HTMLTableParser tableParser = new HTMLTableParser(new ParserSettings(props.getDateFormat()));
    try (Reader reader = new StringReader(fileSource)) {
      HTMLEditorKit.Parser parser = new ParserDelegator();
      parser.parse(reader, tableParser, true);
      System.out.println(tableParser.getDayStatistic());
    }

    DayStatisticWrapper dayStatWrapper = new DayStatisticWrapper(tableParser.getDayStatistic());

    TableFrame tableFrame = new TableFrame();
    tableFrame.setTitles(dayStatWrapper.getTableTitles());
    tableFrame.setData(dayStatWrapper.getTableData());
    tableFrame.show();
  }

}
