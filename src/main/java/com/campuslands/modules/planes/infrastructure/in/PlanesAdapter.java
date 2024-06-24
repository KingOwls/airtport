package com.campuslands.modules.planes.infrastructure.in;

import com.campuslands.modules.planes.domain.models.Planes;
import com.campuslands.modules.planes.application.PlanesService;
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

public class PlanesAdapter {
    private ViewOut v;
    private final PlanesService planesService;

    public PlanesAdapter(PlanesService planesService) {
        this.planesService = planesService;
    }

    public void createPlane() {
        v = new ViewOut();
        ViewOut.VInput platesInput = v.new VInput("Ingresa las placas del avión", 30);
        ViewOut.VInput capacityInput = v.new VInput("Ingresa la capacidad del avión", 30);
        ViewOut.VInput fabricationDateInput = v.new VInput("Ingresa la fecha de fabricación del avión (YYYY-MM-DD)", 30);
        ViewOut.VInput idStatusInput = v.new VInput("Ingresa el ID de estado del avión", 30);
        ViewOut.VInput idModelInput = v.new VInput("Ingresa el ID del modelo del avión", 30);

        JButton addButton = new JButton("Agregar Nuevo Avión");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String plates = platesInput.getText();
                    int capacity = capacityInput.getInt();
                    Date fabricationDate = Date.valueOf(fabricationDateInput.getText());
                    int idStatus = idStatusInput.getInt();
                    int idModel = idModelInput.getInt();

                    Planes plane = new Planes(0, plates, capacity, fabricationDate, idStatus, idModel);
                    planesService.createPlane(plane);
                    JOptionPane.showMessageDialog(v.container, "Avión agregado exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al agregar el avión: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(platesInput.getDiv());
        v.container.add(capacityInput.getDiv());
        v.container.add(fabricationDateInput.getDiv());
        v.container.add(idStatusInput.getDiv());
        v.container.add(idModelInput.getDiv());
        v.printBody(addButton, v.BackButton());
    }

    public void updatePlane() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Avión", 30);
        ViewOut.VInput platesInput = v.new VInput("Ingresa las placas del avión", 30);
        ViewOut.VInput capacityInput = v.new VInput("Ingresa la capacidad del avión", 30);
        ViewOut.VInput fabricationDateInput = v.new VInput("Ingresa la fecha de fabricación del avión (YYYY-MM-DD)", 30);
        ViewOut.VInput idStatusInput = v.new VInput("Ingresa el ID de estado del avión", 30);
        ViewOut.VInput idModelInput = v.new VInput("Ingresa el ID del modelo del avión", 30);

        JButton updateButton = new JButton("Actualizar Avión");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    String plates = platesInput.getText();
                    int capacity = capacityInput.getInt();
                    Date fabricationDate = Date.valueOf(fabricationDateInput.getText());
                    int idStatus = idStatusInput.getInt();
                    int idModel = idModelInput.getInt();

                    Planes plane = new Planes(id, plates, capacity, fabricationDate, idStatus, idModel);
                    planesService.updatePlane(plane);
                    JOptionPane.showMessageDialog(v.container, "Avión actualizado exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al actualizar el avión: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.container.add(platesInput.getDiv());
        v.container.add(capacityInput.getDiv());
        v.container.add(fabricationDateInput.getDiv());
        v.container.add(idStatusInput.getDiv());
        v.container.add(idModelInput.getDiv());
        v.printBody(updateButton, v.BackButton());
    }

    public void deletePlane() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Avión a Eliminar", 30);

        JButton deleteButton = new JButton("Eliminar Avión");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    planesService.deletePlane(id);
                    JOptionPane.showMessageDialog(v.container, "Avión eliminado exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al eliminar el avión: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.printBody(deleteButton, v.BackButton());
    }

    public void findAllPlanes() {
        v = new ViewOut();
        JButton findButton = new JButton("Buscar Todos los Aviones");
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Planes> planes = planesService.getAllPlanes();
                    StringBuilder planesList = new StringBuilder("Lista de Aviones:\n");
                    for (Planes plane : planes) {
                        planesList.append("ID: ").append(plane.getId()).append(", Placas: ")
                                .append(plane.getPlates()).append(", Capacidad: ")
                                .append(plane.getCapacity()).append(", Fecha de Fabricación: ")
                                .append(plane.getFabrication_date()).append(", ID de Estado: ")
                                .append(plane.getId_status()).append(", ID de Modelo: ")
                                .append(plane.getId_model()).append("\n");
                    }
                    JOptionPane.showMessageDialog(v.container, planesList.toString());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al buscar los aviones: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.printBody(findButton, v.BackButton());
    }
}
