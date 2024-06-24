package com.campuslands.modules.flightconnections.infrastructure.in;

import com.campuslands.modules.flightconnections.domain.models.FlightConnection;
import com.campuslands.modules.flightconnections.application.FlightConnectionsService;
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlightConnectionsAdapter {
    private ViewOut v;
    private final FlightConnectionsService flightConnectionsService;

    public FlightConnectionsAdapter(FlightConnectionsService flightConnectionsService) {
        this.flightConnectionsService = flightConnectionsService;
    }

    public void createFlightConnection() {
        v = new ViewOut();
        ViewOut.VInput connectionNumberInput = v.new VInput("Ingresa el Número de Conexión de Vuelo", 30);
        ViewOut.VInput idTripInput = v.new VInput("Ingresa el ID del Viaje", 10);
        ViewOut.VInput idPlaneInput = v.new VInput("Ingresa el ID del Avión", 10);
        ViewOut.VInput idAirportInput = v.new VInput("Ingresa el ID del Aeropuerto", 30);

        JButton addButton = new JButton("Agregar Nueva Conexión de Vuelo");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String connectionNumber = connectionNumberInput.getText();
                    int idTrip = idTripInput.getInt();
                    int idPlane = idPlaneInput.getInt();
                    String idAirport = idAirportInput.getText();

                    FlightConnection flightConnection = new FlightConnection(0, connectionNumber, idTrip, idPlane,
                            idAirport);
                    flightConnectionsService.createFlightConnection(flightConnection);
                    JOptionPane.showMessageDialog(v.container, "Conexión de Vuelo agregada exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al agregar la conexión de vuelo: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(connectionNumberInput.getDiv());
        v.container.add(idTripInput.getDiv());
        v.container.add(idPlaneInput.getDiv());
        v.container.add(idAirportInput.getDiv());
        v.printBody(addButton, v.BackButton());
    }

    public void updateFlightConnection() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID de la Conexión de Vuelo", 30);
        ViewOut.VInput connectionNumberInput = v.new VInput("Ingresa el Número de Conexión de Vuelo", 30);
        ViewOut.VInput idTripInput = v.new VInput("Ingresa el ID del Viaje", 10);
        ViewOut.VInput idPlaneInput = v.new VInput("Ingresa el ID del Avión", 10);
        ViewOut.VInput idAirportInput = v.new VInput("Ingresa el ID del Aeropuerto", 30);

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

                    FlightConnection flightConnection = new FlightConnection(id, connectionNumber, idTrip, idPlane,
                            idAirport);
                    flightConnectionsService.updateFlightConnection(flightConnection);
                    JOptionPane.showMessageDialog(v.container, "Conexión de Vuelo actualizada exitosamente.");
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
        v.printBody(updateButton, v.BackButton());
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
                    JOptionPane.showMessageDialog(v.container, "Conexión de Vuelo eliminada exitosamente.");
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
        JButton findButton = new JButton("Buscar Todas las Conexiones de Vuelo");
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    java.util.List<FlightConnection> flightConnections = flightConnectionsService
                            .getAllFlightConnections();
                    StringBuilder flightConnectionsList = new StringBuilder("Lista de Conexiones de Vuelo:\n");
                    for (FlightConnection flightConnection : flightConnections) {
                        flightConnectionsList.append("ID: ").append(flightConnection.getId())
                                .append(", Número de Conexión: ").append(flightConnection.getConnection_number())
                                .append(", ID Viaje: ").append(flightConnection.getId_trip())
                                .append(", ID Avión: ").append(flightConnection.getId_plane())
                                .append(", ID Aeropuerto: ").append(flightConnection.getId_airport())
                                .append("\n");
                    }
                    JOptionPane.showMessageDialog(v.container, flightConnectionsList.toString());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al buscar las conexiones de vuelo: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.printBody(findButton, v.BackButton());
    }
}
