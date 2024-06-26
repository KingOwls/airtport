package com.campuslands.modules.tripulationroles.infrastructure.in;

import com.campuslands.modules.tripulationroles.application.TripulationrolesService;
import com.campuslands.modules.tripulationroles.domain.models.Tripulationroles;
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
        JButton findButton = new JButton("Buscar Todos los Roles de Tripulación");
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Tripulationroles> tripulationRoles = tripulationrolesService.getAllTripulationroles();
                    StringBuilder rolesList = new StringBuilder("Lista de Roles de Tripulación:\n");
                    for (Tripulationroles role : tripulationRoles) {
                        rolesList.append("ID: ").append(role.getId())
                                .append(", Nombre: ").append(role.getName())
                                .append("\n");
                    }
                    // JOptionPane.showMessageDialog(v.container, rolesList.toString());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al buscar los Roles de Tripulación: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.printBody(findButton, v.BackButton());
    }
}
