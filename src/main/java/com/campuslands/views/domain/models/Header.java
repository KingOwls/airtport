package com.campuslands.views.domain.models;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class Header {

    private static Header instance;

    private static JMenuBar menuBar;

    public Header() {
        menuBar = new JMenuBar();
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public void addOption(JMenu option) {
        menuBar.add(option);
    }

    public void newMenu() {
        menuBar = new JMenuBar();
    }

    public static Header getInstance() {
        // Verifica si la instancia ya ha sido creada
        if (instance == null) {
            // Si no ha sido creada, crea una nueva instancia
            instance = new Header();
        }
        // Retorna la instancia única
        return instance;
    }

}
