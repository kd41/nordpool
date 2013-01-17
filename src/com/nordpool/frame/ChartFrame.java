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

  private ChartSettings settings;

  public ChartFrame(ChartSettings settings) {
    this.settings = settings;
  }

  public void show() {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        JFrame frame = new JFrame(settings.getTitle());

        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        XYDataset ds = createDataset();
        JFreeChart chart = ChartFactory.createXYLineChart(settings.getTitle(), "Time period", "Price (EUR)", ds, PlotOrientation.VERTICAL, true, true, false);

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
    ds.addSeries(settings.getTitle() + " chages between " + settings.getBetween(), settings.getData());
    return ds;
  }

}
