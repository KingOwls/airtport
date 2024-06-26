package com.campuslands.views.domain.models;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;



public class Table {
    DefaultTableModel model;
    JTable table;
    JScrollPane scrollPane;
    JPanel div;

    public Table(String[] columnNames, Object[][] data){
        div = new JPanel(new GridLayout(0, 1));
        model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        table.setBorder(new EmptyBorder(100, 10, 10, 10));
        scrollPane = new JScrollPane(table);
        div.add(scrollPane);
    }


    public JPanel getDiv(){
        return div;
    }

}
