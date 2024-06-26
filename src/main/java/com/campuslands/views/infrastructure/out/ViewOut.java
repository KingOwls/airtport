package com.campuslands.views.infrastructure.out;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import com.campuslands.views.application.ViewsService;
import com.campuslands.views.domain.models.DateInput;
import com.campuslands.views.domain.models.Header;
import com.campuslands.views.domain.models.Input;
import com.campuslands.views.domain.models.View;

public class ViewOut extends ViewsService {

    public JPanel body;
    public JPanel container;
    public JPanel bottoms;

    JButton backButton;

    public class VInput extends Input {

        public VInput(String labelText, int labelColumns) {
            super(labelText, labelColumns);
        }

    }

    public class VDate extends DateInput {

        public VDate(String title, String format) {
            super(title, format);

        }

    }

    public class VHeader extends Header {
    }

    public ViewOut() {
        container = new JPanel(new GridLayout(0, 1));
        body = new JPanel(new BorderLayout());
        bottoms = new JPanel(new FlowLayout());

    }

    public void printBody(JButton addButton, JButton backButton) {
        body.add(container, BorderLayout.CENTER);
        bottoms.add(backButton);
        bottoms.add(addButton);
        body.add(bottoms, BorderLayout.SOUTH);
        View.getInstance().loadBody(body);
    }

    public void printBody(JButton addButton) {
        body.add(container, BorderLayout.CENTER);
        bottoms.add(addButton);
        body.add(bottoms, BorderLayout.SOUTH);
        View.getInstance().loadBody(body);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public JButton ExitButton() {
        JButton backButton = new JButton("Salir");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View.getInstance().close();
            }
        });
        return backButton;
    }

    public JButton BackButton() {
        JButton backButton = new JButton("Regresar");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View.getInstance().loadBody(View.getInstance().getHistoryPanel("inicio"));
            }
        });
        return backButton;
    }

}
