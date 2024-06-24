package com.campuslands.modules.revemployee.infrastructure.in;

import com.campuslands.modules.revemployee.domain.models.Revemployee;
import com.campuslands.modules.revemployee.application.RevemployeeService;
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RevemployeeAdapter {
    private ViewOut v;
    private final RevemployeeService revemployeeService;

    public RevemployeeAdapter(RevemployeeService revemployeeService) {
        this.revemployeeService = revemployeeService;
    }

    public void createRevemployee() {
        v = new ViewOut();
        ViewOut.VInput idEmployeeInput = v.new VInput("Ingrese el ID del Empleado", 30);
        ViewOut.VInput idRevisionInput = v.new VInput("Ingrese el ID de la Revisión", 30);

        JButton addButton = new JButton("Agregar Relación Empleado-Revisión");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idEmployee = idEmployeeInput.getInt();
                    int idRevision = idRevisionInput.getInt();

                    Revemployee revemployee = new Revemployee(idEmployee, idRevision);
                    revemployeeService.createRevemployee(revemployee);
                    JOptionPane.showMessageDialog(v.container, "Relación agregada exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al agregar la relación: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idEmployeeInput.getDiv());
        v.container.add(idRevisionInput.getDiv());
        v.printBody(addButton, v.BackButton());
    }

    public void deleteRevemployee() {
        v = new ViewOut();
        ViewOut.VInput idEmployeeInput = v.new VInput("Ingrese el ID del Empleado", 30);

        JButton deleteButton = new JButton("Eliminar Relación Empleado-Revisión");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    revemployeeService.deleteRevemployee(idEmployeeInput.getInt());
                    JOptionPane.showMessageDialog(v.container, "Relación eliminada exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al eliminar la relación: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idEmployeeInput.getDiv());
        v.printBody(deleteButton, v.BackButton());
    }

    public void findRevemployeeAll() {
        v = new ViewOut();
        // Traer

        JButton addButton = new JButton("Buscar");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                } catch (Exception a) {

                }
            }
        });

        v.printBody(addButton, v.BackButton());
    }
}
