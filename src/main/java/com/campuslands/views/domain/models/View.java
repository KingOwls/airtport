package com.campuslands.views.domain.models;


import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;


import java.awt.BorderLayout;

import java.util.HashMap;
import java.util.Map;



public class View {
    
    private static JFrame frame;
    private static JPanel mainPanel;

    private static Map<String, JPanel> historyPanel;

    private static View instance;


    public View(){
        historyPanel = new HashMap<>();
        frame = new JFrame("Airport Management");
        mainPanel = new JPanel(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.add(mainPanel);
    }

    public void addHeader(JMenuBar menuBar){
        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
    }

    public JPanel getHistoryPanel(String key) {
        return historyPanel.get(key);
    }

       // Method to reload the center panel
    public void loadBody(JPanel div) {
        // Remove existing center component
        if (mainPanel.getComponentCount() > 0) {
            mainPanel.remove(0);
        }
    
        // Add the new component
        mainPanel.add(div, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }



    public void fire(){
        frame.setVisible(true);
    }

    public void close(){
        frame.setVisible(false);
    }


    public static View getInstance() {
        // Verifica si la instancia ya ha sido creada
        if (instance == null) {
            // Si no ha sido creada, crea una nueva instancia
            instance = new View();
        }
        // Retorna la instancia única
        return instance;
    }




}
