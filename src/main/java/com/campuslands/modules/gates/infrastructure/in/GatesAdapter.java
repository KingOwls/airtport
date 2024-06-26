package com.campuslands.modules.gates.infrastructure.in;

import com.campuslands.modules.gates.domain.models.Gates;
import com.campuslands.modules.gates.application.GatesService; // Asegúrate de importar el servicio correcto
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GatesAdapter {
    private ViewOut v;
    private final GatesService gatesService;

    public GatesAdapter(GatesService gatesService) {
        this.gatesService = gatesService;
    }

    public void createGate() {
        v = new ViewOut();
        ViewOut.VInput numberInput = v.new VInput("Ingresa el Número de Puerta", 10);
        ViewOut.VInput idAirportInput = v.new VInput("Ingresa el ID del Aeropuerto", 10);

        JButton addButton = new JButton("Agregar Nueva Puerta");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String number = numberInput.getText();
                    String idAirport = idAirportInput.getText();

                    Gates gate = new Gates(0, number, idAirport);
                    gatesService.createGate(gate);
                    // JOptionPane.showMessageDialog(v.container, "Puerta agregada exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al agregar la puerta: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(numberInput.getDiv());
        v.container.add(idAirportInput.getDiv());
        v.printBody(addButton, v.BackButton());
    }

    public void updateGate() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID de la Puerta", 30);
        ViewOut.VInput numberInput = v.new VInput("Ingresa el Número de Puerta", 10);
        ViewOut.VInput idAirportInput = v.new VInput("Ingresa el ID del Aeropuerto", 10);

        JButton updateButton = new JButton("Actualizar Puerta");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    String number = numberInput.getText();
                    String idAirport = idAirportInput.getText();

                    Gates gate = new Gates(id, number, idAirport);
                    gatesService.updateGate(gate);
                    // JOptionPane.showMessageDialog(v.container, "Puerta actualizada
                    // exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al actualizar la puerta: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.container.add(numberInput.getDiv());
        v.container.add(idAirportInput.getDiv());
        v.printBody(updateButton, v.BackButton());
    }

    public void deleteGate() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID de la Puerta", 30);

        JButton deleteButton = new JButton("Eliminar Puerta");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    gatesService.deleteGate(id);
                    // JOptionPane.showMessageDialog(v.container, "Puerta eliminada exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al eliminar la puerta: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.printBody(deleteButton, v.BackButton());
    }

    public void findAllGates() {
        v = new ViewOut();
        JButton findButton = new JButton("Buscar Todas las Puertas");
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    java.util.List<Gates> gates = gatesService.getAllGates();
                    StringBuilder gatesList = new StringBuilder("Lista de Puertas:\n");
                    for (Gates gate : gates) {
                        gatesList.append("ID: ").append(gate.getId()).append(", Número: ")
                                .append(gate.getGetNumber()).append(", ID Aeropuerto: ").append(gate.getIdAirport())
                                .append("\n");
                    }
                    JOptionPane.showMessageDialog(v.container, gatesList.toString());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al buscar las puertas: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.printBody(findButton, v.BackButton());
    }
}
