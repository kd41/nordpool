package com.nordpool.frame;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableFrame {
  private String[] titles = new String[] { "Name", "Vage" };
  private String[][] data = new String[][] { { "Donald Duck", "100" }, { "Mickey Mouse", "120" } };

  public void show() {
    JFrame frame = new JFrame("JFrame and JTable example 2");
    frame.setSize(1500, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JTable table = new JTable(data, titles) {
      private static final long serialVersionUID = 1L;

      public boolean isCellEditable(int row, int column) {
        return false;
      };
    };
    JScrollPane scrollPane = new JScrollPane(table);

    table.setColumnSelectionAllowed(true);
    table.setRowSelectionAllowed(false);

    table.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(final MouseEvent e) {
        if (e.getClickCount() == 1) {
          final JTable table = (JTable) e.getSource();
          final int row = table.getSelectedRow();
          final int column = table.getSelectedColumn();

          System.out.println("Selected: row=" + row + ", column=" + column);

          if (column != 0) {

            double[][] data = new double[2][table.getModel().getRowCount()];

            for (int i = 0; i < table.getRowCount(); i++) {
              data[0][i] = i;
              data[1][i] = Double.parseDouble(table.getValueAt(i, column).toString());
            }

            ChartFrame chartFrame = new ChartFrame(data);
            chartFrame.show();

          }

        }
      }
    });

    frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
    frame.setVisible(true);
  }

  public String[] getTitles() {
    return titles;
  }

  public void setTitles(String[] titles) {
    this.titles = titles;
  }

  public String[][] getData() {
    return data;
  }

  public void setData(String[][] data) {
    this.data = data;
  }
}