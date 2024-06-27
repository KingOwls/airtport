package com.campuslands.modules.flightconnections.infrastructure.in;

import com.campuslands.modules.flightconnections.domain.models.FlightConnection;
import com.campuslands.modules.flightconnections.application.FlightConnectionsService;
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class FlightConnectionsAdapter {
    private ViewOut v;
    private final FlightConnectionsService flightConnectionsService;

    public FlightConnectionsAdapter(FlightConnectionsService flightConnectionsService) {
        this.flightConnectionsService = flightConnectionsService;
    }

    public void createFlightConnection() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID de Conexión de Vuelo", 30);
        ViewOut.VInput connectionNumberInput = v.new VInput("Ingresa el Número de Conexión de Vuelo", 30);
        ViewOut.VInput idTripInput = v.new VInput("Ingresa el ID del Viaje", 10);
        ViewOut.VInput idPlaneInput = v.new VInput("Ingresa el ID del Avión", 10);
        ViewOut.VInput idAirportInput = v.new VInput("Ingresa el ID del Aeropuerto", 30);
        ViewOut.VInput typefrightInput = v.new VInput("Ingresa el ID del Vuelo", 30);
        ViewOut.VInput lastScaleInput = v.new VInput("Ingresa el ID del Vuelo", 30);

        JButton addButton = new JButton("Agregar Nueva Conexión de Vuelo");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    String connectionNumber = connectionNumberInput.getText();
                    int idTrip = idTripInput.getInt();
                    int idPlane = idPlaneInput.getInt();
                    String idAirport = idAirportInput.getText();
                    String typefright = typefrightInput.getText();
                    String lastScale = lastScaleInput.getText();

                    FlightConnection flightConnection = new FlightConnection(id, connectionNumber, idTrip, idPlane,
                            idAirport, typefright, lastScale);
                    flightConnectionsService.createFlightConnection(flightConnection);
                    // JOptionPane.showMessageDialog(v.container, "Conexión de Vuelo agregada
                    // exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al agregar la conexión de vuelo: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.container.add(connectionNumberInput.getDiv());
        v.container.add(idTripInput.getDiv());
        v.container.add(idPlaneInput.getDiv());
        v.container.add(idAirportInput.getDiv());
        v.container.add(typefrightInput.getDiv());
        v.container.add(lastScaleInput.getDiv());
        v.printBody(addButton, v.BackButton());
    }

    public void updateFlightConnection() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID de Conexión de Vuelo", 30);
        ViewOut.VInput connectionNumberInput = v.new VInput("Ingresa el Número de Conexión de Vuelo", 30);
        ViewOut.VInput idTripInput = v.new VInput("Ingresa el ID del Viaje", 10);
        ViewOut.VInput idPlaneInput = v.new VInput("Ingresa el ID del Avión", 10);
        ViewOut.VInput idAirportInput = v.new VInput("Ingresa el ID del Aeropuerto", 30);
        ViewOut.VInput typefrightInput = v.new VInput("Ingresa el ID del Vuelo", 30);
        ViewOut.VInput lastScaleInput = v.new VInput("Ingresa el ID del Vuelo", 30);

        JButton updateButton = new JButton("Actualizar Conexión de Vuelo");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    String connectionNumber = connectionNumberInput.getText();
                    int idTrip = idTripInput.getInt();
                    int idPlane = idPlaneInput.getInt();
                    String idAirport = idAirportInput.getText();
                    String typefright = typefrightInput.getText();
                    String lastScale = lastScaleInput.getText();

                    FlightConnection flightConnection = new FlightConnection(id, connectionNumber, idTrip, idPlane,
                            idAirport, typefright, lastScale);
                    flightConnectionsService.updateFlightConnection(flightConnection);
                    // JOptionPane.showMessageDialog(v.container, "Conexión de Vuelo actualizada
                    // exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al actualizar la conexión de vuelo: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.container.add(connectionNumberInput.getDiv());
        v.container.add(idTripInput.getDiv());
        v.container.add(idPlaneInput.getDiv());
        v.container.add(idAirportInput.getDiv());
        v.container.add(typefrightInput.getDiv());
        v.container.add(lastScaleInput.getDiv());
    }

    public void deleteFlightConnection() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID de la Conexión de Vuelo", 30);

        JButton deleteButton = new JButton("Eliminar Conexión de Vuelo");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    flightConnectionsService.deleteFlightConnection(id);
                    // JOptionPane.showMessageDialog(v.container, "Conexión de Vuelo eliminada
                    // exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al eliminar la conexión de vuelo: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.printBody(deleteButton, v.BackButton());
    }

    public void findAllFlightConnections() {
        v = new ViewOut();
        List<FlightConnection> flightConnections = flightConnectionsService.getAllFlightConnections();
        String[] columnNames = { "ID", "Nombre", "Numero de Conexión", "Id viaje", "Id aeropuerto", "Tipo de vuelo",
                "Ultima Escala" };
        Object[][] data = new Object[flightConnections.size()][7];

        for (int i = 0; i < flightConnections.size(); i++) {
            FlightConnection flightConnection = flightConnections.get(i);
            data[i][0] = flightConnection.getId();
            data[i][1] = flightConnection.getConnection_number();
            data[i][2] = flightConnection.getId_trip();
            data[i][3] = flightConnection.getId_plane();
            data[i][4] = flightConnection.getId_airport();
            data[i][5] = flightConnection.getType_flight();
            data[i][6] = flightConnection.getLast_Scale();

        }

        v.container.add(v.new VTable(columnNames, data).getDiv());
        v.printBody(v.BackButton());
    }

    public void findByIdFlightConnection() {
        try {
            v = new ViewOut();
            ViewOut.VInput idInput = v.new VInput("Ingresa el ID de la Conexión de Vuelo a Buscar", 30);

            JButton searchButton = new JButton("Buscar Conexión de Vuelo");
            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        JPanel lastWindow = v.body; // Store the previous window panel
                        v = new ViewOut(); // Create a new instance of ViewOut
                        int id = idInput.getInt();
                        Optional<FlightConnection> flightConnectionOptional = flightConnectionsService
                                .getFlightConnectionById(id);
                        if (flightConnectionOptional.isPresent()) {
                            FlightConnection flightConnection = flightConnectionOptional.get();
                            String[] columnNames = { "ID", "Número de Conexión", "ID Viaje", "ID Avión",
                                    "ID Aeropuerto", "Tipo de Vuelo", "Última Escala" };
                            Object[][] data = new Object[1][7];
                            data[0][0] = flightConnection.getId();
                            data[0][1] = flightConnection.getConnection_number();
                            data[0][2] = flightConnection.getId_trip();
                            data[0][3] = flightConnection.getId_plane();
                            data[0][4] = flightConnection.getId_airport();
                            data[0][5] = flightConnection.getType_flight();
                            data[0][6] = flightConnection.getLast_Scale();

                            v.container.add(v.new VTable(columnNames, data).getDiv());
                            v.printBody(v.BackButton("findByIdFlightConnection", lastWindow));
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "No se encontró la conexión de vuelo con el ID especificado", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null,
                                "Ingrese un valor numérico para el ID de la conexión de vuelo", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(v.container,
                                "Error al buscar la conexión de vuelo: " + ex.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            v.container.add(idInput.getDiv());
            v.printBody(searchButton, v.BackButton());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al iniciar la búsqueda de conexión de vuelo: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
