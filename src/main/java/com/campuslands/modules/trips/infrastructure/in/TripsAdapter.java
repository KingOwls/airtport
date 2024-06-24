package com.campuslands.modules.trips.infrastructure.in;

import com.campuslands.modules.trips.domain.models.Trips;
import com.campuslands.modules.trips.application.TripsService; // Asegúrate de importar el servicio correcto
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class TripsAdapter {
    private ViewOut v;
    private final TripsService tripsService;

    public TripsAdapter(TripsService tripsService) {
        this.tripsService = tripsService;
    }

    public void createTrip() {
        v = new ViewOut();
        ViewOut.VInput dateInput = v.new VInput("Ingresa la Fecha del Viaje (YYYY-MM-DD)", 30);
        ViewOut.VInput priceInput = v.new VInput("Ingresa el Precio del Viaje", 30);

        JButton addButton = new JButton("Agregar Viaje");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Date date = Date.valueOf(dateInput.getText());
                    double price = Double.parseDouble(priceInput.getText());

                    Trips trip = new Trips(0, date, price); // El ID se asignará automáticamente en la base de datos
                    tripsService.createTrip(trip);
                    JOptionPane.showMessageDialog(v.container, "Viaje agregado exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al agregar el Viaje: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(dateInput.getDiv());
        v.container.add(priceInput.getDiv());
        v.printBody(addButton, v.BackButton());
    }

    public void updateTrip() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Viaje", 30);
        ViewOut.VInput dateInput = v.new VInput("Ingresa la Fecha del Viaje (YYYY-MM-DD)", 30);
        ViewOut.VInput priceInput = v.new VInput("Ingresa el Precio del Viaje", 30);

        JButton updateButton = new JButton("Actualizar Viaje");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idInput.getText());
                    Date date = Date.valueOf(dateInput.getText());
                    double price = Double.parseDouble(priceInput.getText());

                    Trips trip = new Trips(id, date, price);
                    tripsService.updateTrip(trip);
                    JOptionPane.showMessageDialog(v.container, "Viaje actualizado exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al actualizar el Viaje: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.container.add(dateInput.getDiv());
        v.container.add(priceInput.getDiv());
        v.printBody(updateButton, v.BackButton());
    }

    public void deleteTrip() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Viaje", 30);

        JButton deleteButton = new JButton("Eliminar Viaje");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idInput.getText());
                    tripsService.deleteTrip(id);
                    JOptionPane.showMessageDialog(v.container, "Viaje eliminado exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al eliminar el Viaje: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.printBody(deleteButton, v.BackButton());
    }

    public void findAllTrips() {
        v = new ViewOut();
        JButton findButton = new JButton("Buscar Todos los Viajes");
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    java.util.List<Trips> trips = tripsService.getAllTrips();
                    StringBuilder tripsList = new StringBuilder("Lista de Viajes:\n");
                    for (Trips trip : trips) {
                        tripsList.append("ID: ").append(trip.getId())
                                .append(", Fecha: ").append(trip.getDate())
                                .append(", Precio: ").append(trip.getPrice())
                                .append("\n");
                    }
                    JOptionPane.showMessageDialog(v.container, tripsList.toString());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al buscar los Viajes: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.printBody(findButton, v.BackButton());
    }
}
