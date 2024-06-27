package com.campuslands.modules.revemployee.infrastructure.in;

import com.campuslands.modules.revemployee.domain.models.Revemployee;
import com.campuslands.modules.revemployee.application.RevemployeeService;
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
                    //JOptionPane.showMessageDialog(v.container, "Relación agregada exitosamente.");
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
                  //  JOptionPane.showMessageDialog(v.container, "Relación eliminada exitosamente.");
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
        List<Revemployee> revemployees = revemployeeService.getAllRevemployees();
        String[] columnNames = { "ID Empleado", "ID Revision"};
        Object[][] data = new Object[revemployees.size()][2];

        for (int i = 0; i < revemployees.size(); i++) {
            Revemployee revemployee = revemployees.get(i);
            data[i][0] = revemployee.getIdEmployee();
            data[i][1] = revemployee.getIdRevision();

        }

        v.container.add(v.new VTable(columnNames, data).getDiv());
        v.printBody(v.BackButton());
    }
}
