package com.campuslands.modules.tripcrews.infrastructure.in;

import com.campuslands.modules.tripcrews.domain.models.Tripcrews;

import com.campuslands.modules.tripcrews.application.TripcrewsService; // Asegúrate de importar el servicio correcto
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

                    // JOptionPane.showMessageDialog(v.container, "Tripcrew agregado
                    // exitosamente.");
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
                    // JOptionPane.showMessageDialog(v.container, "Tripcrew actualizado
                    // exitosamente.");
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
                    // JOptionPane.showMessageDialog(v.container, "Tripcrew eliminado
                    // exitosamente.");
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
        List<Tripcrews> tripCrew = tripcrewsService.getAllTripcrews();
        String[] columnNames = { "id empleado", "id conexión" };
        Object[][] data = new Object[tripCrew.size()][6];

        for (int i = 0; i < tripCrew.size(); i++) {
            Tripcrews tripCrews = tripCrew.get(i);
            data[i][0] = tripCrews.getIdemployees();
            data[i][1] = tripCrews.getIdconection();
        }

        v.container.add(v.new VTable(columnNames, data).getDiv());
        v.printBody(v.BackButton());
    }
}
