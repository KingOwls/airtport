package com.campuslands.modules.flightfares.infrastructure.in;

import com.campuslands.modules.flightfares.domain.models.Flightfares;
import com.campuslands.modules.flightfares.application.FlightfaresService;
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class FlightFaresAdapter {
    private ViewOut v;
    private final FlightfaresService flightfaresService;

    public FlightFaresAdapter(FlightfaresService flightfaresService) {
        this.flightfaresService = flightfaresService;
    }

    public void createFlightfare() {
        v = new ViewOut();
        ViewOut.VInput descriptionInput = v.new VInput("Ingresa la Descripción", 50);
        ViewOut.VInput detailsInput = v.new VInput("Ingresa los Detalles", 100);
        ViewOut.VInput valueInput = v.new VInput("Ingresa el Valor", 10);

        JButton addButton = new JButton("Agregar Nueva Tarifa de Vuelo");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String description = descriptionInput.getText();
                    String details = detailsInput.getText();
                    int value = valueInput.getInt();

                    Flightfares flightfare = new Flightfares(0, description, details, value);
                    flightfaresService.createFlightfare(flightfare);
                    // JOptionPane.showMessageDialog(v.container, "Tarifa de vuelo agregada
                    // exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al agregar la tarifa de vuelo: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(descriptionInput.getDiv());
        v.container.add(detailsInput.getDiv());
        v.container.add(valueInput.getDiv());
        v.printBody(addButton, v.BackButton());
    }

    public void updateFlightfare() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID de la Tarifa de Vuelo", 30);
        ViewOut.VInput descriptionInput = v.new VInput("Ingresa la Descripción", 50);
        ViewOut.VInput detailsInput = v.new VInput("Ingresa los Detalles", 100);
        ViewOut.VInput valueInput = v.new VInput("Ingresa el Valor", 10);

        JButton updateButton = new JButton("Actualizar Tarifa de Vuelo");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    String description = descriptionInput.getText();
                    String details = detailsInput.getText();
                    int value = valueInput.getInt();

                    Flightfares flightfare = new Flightfares(id, description, details, value);
                    flightfaresService.updateFlightfare(flightfare);
                    // JOptionPane.showMessageDialog(v.container, "Tarifa de vuelo actualizada
                    // exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al actualizar la tarifa de vuelo: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.container.add(descriptionInput.getDiv());
        v.container.add(detailsInput.getDiv());
        v.container.add(valueInput.getDiv());
        v.printBody(updateButton, v.BackButton());
    }

    public void deleteFlightfare() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID de la Tarifa de Vuelo", 30);

        JButton deleteButton = new JButton("Eliminar Tarifa de Vuelo");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    flightfaresService.deleteFlightfare(id);
                    // JOptionPane.showMessageDialog(v.container, "Tarifa de vuelo eliminada
                    // exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al eliminar la tarifa de vuelo: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.printBody(deleteButton, v.BackButton());
    }

    public void findAllFlightfares() {
        v = new ViewOut();
        List<Flightfares> flightfares = flightfaresService.getAllFlightfares();
        String[] columnNames = { "ID", "descripción", "Detalles", " Valor" };
        Object[][] data = new Object[flightfares.size()][4];

        for (int i = 0; i < flightfares.size(); i++) {
            Flightfares flightfare = flightfares.get(i);
            data[i][0] = flightfare.getId();
            data[i][1] = flightfare.getDescription();
            data[i][2] = flightfare.getDetails();
            data[i][3] = flightfare.getValue();

        }

        v.container.add(v.new VTable(columnNames, data).getDiv());
        v.printBody(v.BackButton());
    }

    public void findByIdFlightfare() {
        try {
            v = new ViewOut();
            ViewOut.VInput idInput = v.new VInput("Ingresa el ID de la Tarifa de Vuelo a Buscar", 30);

            JButton searchButton = new JButton("Buscar Tarifa de Vuelo");
            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        JPanel lastWindow = v.body; // Store the previous window panel
                        v = new ViewOut(); // Create a new instance of ViewOut
                        int id = idInput.getInt();
                        Optional<Flightfares> flightfareOptional = flightfaresService.getFlightfareById(id);
                        if (flightfareOptional.isPresent()) {
                            Flightfares flightfare = flightfareOptional.get();
                            String[] columnNames = { "ID", "Descripción", "Detalles", "Valor" };
                            Object[][] data = new Object[1][4];
                            data[0][0] = flightfare.getId();
                            data[0][1] = flightfare.getDescription();
                            data[0][2] = flightfare.getDetails();
                            data[0][3] = flightfare.getValue();

                            v.container.add(v.new VTable(columnNames, data).getDiv());
                            v.printBody(v.BackButton("findByIdFlightfare", lastWindow));
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "No se encontró la tarifa de vuelo con el ID especificado", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null,
                                "Ingrese un valor numérico para el ID de la tarifa de vuelo", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(v.container,
                                "Error al buscar la tarifa de vuelo: " + ex.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            v.container.add(idInput.getDiv());
            v.printBody(searchButton, v.BackButton());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al iniciar la búsqueda de tarifa de vuelo: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
