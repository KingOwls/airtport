package com.campuslands.modules.tripulationroles.infrastructure.in;

import com.campuslands.modules.tripulationroles.application.TripulationrolesService;
import com.campuslands.modules.tripulationroles.domain.models.Tripulationroles;
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class TripulationrolesAdapter {
    private ViewOut v;
    private final TripulationrolesService tripulationrolesService;

    public TripulationrolesAdapter(TripulationrolesService tripulationrolesService) {
        this.tripulationrolesService = tripulationrolesService;
    }

    public void createTripulationRole() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Rol de Tripulación", 30);
        ViewOut.VInput nameInput = v.new VInput("Ingresa el nombre del Rol de Tripulación", 30);

        JButton addButton = new JButton("Agregar Rol de Tripulación");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idInput.getText());
                    String name = nameInput.getText();

                    Tripulationroles tripulationRole = new Tripulationroles(id, name);
                    tripulationrolesService.createTripulationroles(tripulationRole);
                    // JOptionPane.showMessageDialog(v.container, "Rol de Tripulación agregado
                    // exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al agregar el Rol de Tripulación: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.container.add(nameInput.getDiv());
        v.printBody(addButton, v.BackButton());
    }

    public void updateTripulationRole() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Rol de Tripulación a actualizar", 30);
        ViewOut.VInput nameInput = v.new VInput("Ingresa el nuevo nombre del Rol de Tripulación", 30);

        JButton updateButton = new JButton("Actualizar Rol de Tripulación");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idInput.getText());
                    String name = nameInput.getText();

                    Tripulationroles tripulationRole = new Tripulationroles(id, name);
                    tripulationrolesService.updateTripulationroles(tripulationRole);
                    // JOptionPane.showMessageDialog(v.container, "Rol de Tripulación actualizado
                    // exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al actualizar el Rol de Tripulación: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.container.add(nameInput.getDiv());
        v.printBody(updateButton, v.BackButton());
    }

    public void deleteTripulationRole() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Rol de Tripulación a eliminar", 30);

        JButton deleteButton = new JButton("Eliminar Rol de Tripulación");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idInput.getText());
                    tripulationrolesService.deleteTripulationroles(id);
                    // JOptionPane.showMessageDialog(v.container, "Rol de Tripulación eliminado
                    // exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al eliminar el Rol de Tripulación: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.printBody(deleteButton, v.BackButton());
    }

    public void findAllTripulationRoles() {
        v = new ViewOut();
        List<Tripulationroles> tripulationroles = tripulationrolesService.getAllTripulationroles();
        String[] columnNames = { "ID ", "Nombre" };
        Object[][] data = new Object[tripulationroles.size()][5];

        for (int i = 0; i < tripulationroles.size(); i++) {
            Tripulationroles tripulationrole = tripulationroles.get(i);
            data[i][0] = tripulationrole.getId();
            data[i][1] = tripulationrole.getName();

        }

        v.container.add(v.new VTable(columnNames, data).getDiv());
        v.printBody(v.BackButton());

    }

    public void findByIdTripulationRole() {
        try {
            v = new ViewOut();
            ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Rol de Tripulación a Buscar", 30);

            JButton searchButton = new JButton("Buscar Rol de Tripulación");
            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        JPanel lastWindow = v.body; // Store the previous window panel
                        v = new ViewOut(); // Create a new instance of ViewOut
                        int id = idInput.getInt();
                        Optional<Tripulationroles> tripulationRoleOptional = tripulationrolesService
                                .getTripulationrolesById(id);
                        if (tripulationRoleOptional.isPresent()) {
                            Tripulationroles tripulationRole = tripulationRoleOptional.get();
                            String[] columnNames = { "ID", "Nombre" };
                            Object[][] data = new Object[1][2];
                            data[0][0] = tripulationRole.getId();
                            data[0][1] = tripulationRole.getName();

                            v.container.add(v.new VTable(columnNames, data).getDiv());
                            v.printBody(v.BackButton("findByIdTripulationRole", lastWindow));
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "No se encontró el rol de tripulación con el ID especificado", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null,
                                "Ingrese un valor numérico para el ID del rol de tripulación", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(v.container,
                                "Error al buscar el rol de tripulación: " + ex.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            v.container.add(idInput.getDiv());
            v.printBody(searchButton, v.BackButton());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Error al iniciar la búsqueda del rol de tripulación: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
