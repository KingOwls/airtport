package com.campuslands.modules.auth.infrastructure.in;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.campuslands.modules.auth.application.AuthService;
import com.campuslands.views.infrastructure.out.ViewOut;

public class AuthAdapter {

    ViewOut v;
    private final AuthService authService;
    JTabbedPane tabbedPane;

    public AuthAdapter(AuthService authService) {
        this.authService = authService;
    }

    public void loginView() {
        v = new ViewOut();
        ViewOut.VInput nameInput = v.new VInput("Correo Electrónico", 30);
        ViewOut.VInput passwordInput = v.new VInput("Contraseña", 30);

        JButton addButton = new JButton("Iniciar Sección");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                } catch (Exception a) {

                }
            }
        });

        v.container.add(nameInput.getDiv());
        v.container.add(passwordInput.getDiv());
        v.printBody(addButton);
    }

    public JPanel employeeLogin() {
        v = new ViewOut();

        return v.body;
    }

    public JPanel customerLogin() {
        v = new ViewOut();
        ViewOut.VInput nameInput = v.new VInput("Correo Electrónico", 30);
        ViewOut.VInput passwordInput = v.new VInput("Contraseña", 30);

        JButton addButton = new JButton("Iniciar Sección");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                } catch (Exception a) {

                }
            }
        });

        v.container.add(nameInput.getDiv());
        v.container.add(passwordInput.getDiv());

        return v.body;
    }
}
