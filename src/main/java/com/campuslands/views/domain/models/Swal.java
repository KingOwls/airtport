package com.campuslands.views.domain.models;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;


public class Swal { 

    private static Swal instance;
    JDialog dialog;
    JPanel buttons;
    JButton backButton;
    static String title;
  

    public Swal() {
        dialog = new JDialog();
        dialog.setTitle(title);
        dialog.setSize(700, 500);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null); // Centrar el diálogo en la pantalla
        dialog.setLayout(new BorderLayout());

        /* Buttons */
        buttons = new JPanel(new FlowLayout());
        backButton = new JButton("Regresar");
        buttons.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
            }
        });

        dialog.add(buttons, BorderLayout.SOUTH);
    }

    public void setBodySwal(JPanel view) {
        dialog.add(view, BorderLayout.CENTER);
    }

    public void setAddButton(JButton addButton) {
        buttons.add(addButton);
    }
    
    public void fire(){
        dialog.setVisible(true);
    }

    public void close(){
        dialog.setVisible(false);
    }

     // Method to get the Singleton instance
    public static synchronized Swal getInstance(String newTitle) {
        title = newTitle;
        if (instance == null) {
            instance = new Swal();
        }
        return instance;
    }


}
