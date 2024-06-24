package com.campuslands.modules.tripcrews.infrastructure.in;

import com.campuslands.modules.tripcrews.domain.models.Tripcrews;
import com.campuslands.modules.tripcrews.application.TripcrewsService; // Asegúrate de importar el servicio correcto
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TripcrewsAdapter {
    private ViewOut v;
    private final TripcrewsService tripcrewsService;

    public TripcrewsAdapter(TripcrewsService tripcrewsService) {
        this.tripcrewsService = tripcrewsService;
    }

    public void createTripcrew() {
        v = new ViewOut();
        ViewOut.VInput idEmployeesInput = v.new VInput("Ingresa el ID del Empleado", 30);
        ViewOut.VInput idConnectionInput = v.new VInput("Ingresa el ID de la Conexión", 30);

        JButton addButton = new JButton("Agregar Tripcrew");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idEmployees = idEmployeesInput.getInt();
                    int idConnection = idConnectionInput.getInt();

                    Tripcrews tripcrew = new Tripcrews(idEmployees, idConnection);
                    tripcrewsService.createTripcrew(tripcrew);
                    JOptionPane.showMessageDialog(v.container, "Tripcrew agregado exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al agregar el Tripcrew: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idEmployeesInput.getDiv());
        v.container.add(idConnectionInput.getDiv());
        v.printBody(addButton, v.BackButton());
    }

    public void updateTripcrew() {
        v = new ViewOut();
        ViewOut.VInput idEmployeesInput = v.new VInput("Ingresa el ID del Empleado", 30);
        ViewOut.VInput idConnectionInput = v.new VInput("Ingresa el ID de la Conexión", 30);

        JButton updateButton = new JButton("Actualizar Tripcrew");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idEmployees = idEmployeesInput.getInt();
                    int idConnection = idConnectionInput.getInt();

                    Tripcrews tripcrew = new Tripcrews(idEmployees, idConnection);
                    tripcrewsService.updateTripcrew(tripcrew);
                    JOptionPane.showMessageDialog(v.container, "Tripcrew actualizado exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al actualizar el Tripcrew: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idEmployeesInput.getDiv());
        v.container.add(idConnectionInput.getDiv());
        v.printBody(updateButton, v.BackButton());
    }

    public void deleteTripcrew() {
        v = new ViewOut();
        ViewOut.VInput idEmployeesInput = v.new VInput("Ingresa el ID del Empleado", 30);

        JButton deleteButton = new JButton("Eliminar Tripcrew");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    tripcrewsService.deleteTripcrew(idEmployeesInput.getInt());
                    JOptionPane.showMessageDialog(v.container, "Tripcrew eliminado exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al eliminar el Tripcrew: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idEmployeesInput.getDiv());
        v.printBody(deleteButton, v.BackButton());
    }

    public void findAllTripcrews() {
        v = new ViewOut();
        JButton findButton = new JButton("Buscar Todos los Tripcrews");
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    java.util.List<Tripcrews> tripcrews = tripcrewsService.getAllTripcrews();
                    StringBuilder tripcrewsList = new StringBuilder("Lista de Tripcrews:\n");
                    for (Tripcrews tripcrew : tripcrews) {
                        tripcrewsList.append("ID Empleado: ").append(tripcrew.getIdemployees())
                                .append(", ID Conexión: ").append(tripcrew.getIdconection())
                                .append("\n");
                    }
                    JOptionPane.showMessageDialog(v.container, tripcrewsList.toString());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al buscar los Tripcrews: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.printBody(findButton, v.BackButton());
    }
}
