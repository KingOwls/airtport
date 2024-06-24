package com.campuslands.views.domain.models;


import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Input  {
    private JTextField input;
    private JPanel div;


    public Input(String labelText, int labelColumns) {
        input = new JTextField(labelColumns);
        div = new JPanel();
        div.add(new JLabel(labelText));
        div.add(input);
    }

    public String getText() {
        return input.getText();
    }

    public int getInt() {
        try {
            String text = input.getText();
            int value = Integer.parseInt(text);
            return value;
        } catch (NumberFormatException e) {
            throw new RuntimeException("El texto ingresado no es un número válido.");
        }
    }

    public JPanel getDiv() {
        return div;
    }

    

   
}
