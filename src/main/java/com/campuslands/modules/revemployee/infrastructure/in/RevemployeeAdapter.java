package com.campuslands.modules.revemployee.infrastructure.in;

import com.campuslands.modules.revemployee.domain.models.Revemployee;
import com.campuslands.modules.revemployee.application.RevemployeeService;
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

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
                    // JOptionPane.showMessageDialog(v.container, "Relación agregada
                    // exitosamente.");
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
                    // JOptionPane.showMessageDialog(v.container, "Relación eliminada
                    // exitosamente.");
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
        String[] columnNames = { "ID Empleado", "ID Revision" };
        Object[][] data = new Object[revemployees.size()][2];

        for (int i = 0; i < revemployees.size(); i++) {
            Revemployee revemployee = revemployees.get(i);
            data[i][0] = revemployee.getIdEmployee();
            data[i][1] = revemployee.getIdRevision();

        }

        v.container.add(v.new VTable(columnNames, data).getDiv());
        v.printBody(v.BackButton());
    }

    public void findByIdRevemployee() {
        try {
            v = new ViewOut();
            ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Empleado de Revisión a Buscar", 30);

            JButton searchButton = new JButton("Buscar Empleado de Revisión");
            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        JPanel lastWindow = v.body; // Store the previous window panel
                        v = new ViewOut(); // Create a new instance of ViewOut
                        int id = idInput.getInt();
                        Optional<Revemployee> revemployeeOptional = revemployeeService.getRevemployeeById(id);
                        if (revemployeeOptional.isPresent()) {
                            Revemployee revemployee = revemployeeOptional.get();
                            String[] columnNames = { "ID Empleado", "ID Revisión" };
                            Object[][] data = new Object[1][2];
                            data[0][0] = revemployee.getIdEmployee();
                            data[0][1] = revemployee.getIdRevision();

                            v.container.add(v.new VTable(columnNames, data).getDiv());
                            v.printBody(v.BackButton("findByIdRevemployee", lastWindow));
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "No se encontró el empleado de revisión con el ID especificado", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null,
                                "Ingrese un valor numérico para el ID del empleado de revisión", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(v.container,
                                "Error al buscar el empleado de revisión: " + ex.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            v.container.add(idInput.getDiv());
            v.printBody(searchButton, v.BackButton());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Error al iniciar la búsqueda de empleado de revisión: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
