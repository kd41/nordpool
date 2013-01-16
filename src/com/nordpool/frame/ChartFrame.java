package com.nordpool.frame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.Color;

public class ChartFrame {

  private String title;
  private double[][] data;

  public ChartFrame(String title, double[][] data) {
    this.title = title;
    this.data = data;
  }

  public void show() {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        JFrame frame = new JFrame(title);

        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        XYDataset ds = createDataset();
        JFreeChart chart = ChartFactory.createXYLineChart(title, "Time period", "Price (EUR)", ds, PlotOrientation.VERTICAL, true, true, false);

        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(1, true);
        plot.setRenderer(renderer);

        ChartPanel cp = new ChartPanel(chart);

        frame.getContentPane().add(cp);
      }
    });
  }

  private XYDataset createDataset() {
    DefaultXYDataset ds = new DefaultXYDataset();
    ds.addSeries(title + " chages between " + getBetween(), getData());
    return ds;
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

  private String getBetween() {
    return getData()[2][2] + "-" + getData()[0][getData()[0].length - 1];
  }

}
