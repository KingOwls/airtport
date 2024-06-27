package com.campuslands.modules.gates.infrastructure.in;

import com.campuslands.modules.gates.domain.models.Gates;
import com.campuslands.modules.gates.application.GatesService; // Asegúrate de importar el servicio correcto
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
        List<Gates> Gates = gatesService.getAllGates();
        String[] columnNames = { "ID", "Número", "Id Aeropuerto"};
        Object[][] data = new Object[Gates.size()][4];

        for (int i = 0; i < Gates.size(); i++) {
            Gates Gate = Gates.get(i);
            data[i][0] = Gate.getId();
            data[i][1] = Gate.getGetNumber();
            data[i][2] = Gate.getIdAirport();
        }

        v.container.add(v.new VTable(columnNames, data).getDiv());
        v.printBody(v.BackButton());
    }
}
