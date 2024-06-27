package com.campuslands.modules.tripbooking.infrastructure.in;

import com.campuslands.modules.tripbooking.domain.models.TripBooking;
import com.campuslands.modules.tripbooking.application.TripbookingService;
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import java.util.List;

public class TripbookingAdapter {
    private ViewOut v;
    private final TripbookingService tripBookingService;

    public TripbookingAdapter(TripbookingService tripBookingService) {
        this.tripBookingService = tripBookingService;
    }

    public void createTripBooking() {
        v = new ViewOut();
        ViewOut.VInput dateInput = v.new VInput("Ingresa la Fecha de Reserva (YYYY-MM-DD)", 20);
        ViewOut.VInput idTripsInput = v.new VInput("Ingresa el ID del Viaje", 30);

        JButton addButton = new JButton("Agregar Nueva Reserva de Viaje");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Date date = Date.valueOf(dateInput.getText());
                    int idTrips = idTripsInput.getInt();

                    TripBooking tripBooking = new TripBooking(0, date, idTrips);
                    tripBookingService.createTripbooking(tripBooking);
                    JOptionPane.showMessageDialog(v.container, "Reserva de viaje agregada exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al agregar la reserva de viaje: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(dateInput.getDiv());
        v.container.add(idTripsInput.getDiv());
        v.printBody(addButton, v.BackButton());
    }

    public void updateTripBooking() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID de la Reserva de Viaje", 30);
        ViewOut.VInput dateInput = v.new VInput("Ingresa la Fecha de Reserva (YYYY-MM-DD)", 20);
        ViewOut.VInput idTripsInput = v.new VInput("Ingresa el ID del Viaje", 30);

        JButton updateButton = new JButton("Actualizar Reserva de Viaje");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    Date date = Date.valueOf(dateInput.getText());
                    int idTrips = idTripsInput.getInt();

                    TripBooking tripBooking = new TripBooking(id, date, idTrips);
                    tripBookingService.updateTripbooking(tripBooking);
                    JOptionPane.showMessageDialog(v.container, "Reserva de viaje actualizada exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al actualizar la reserva de viaje: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.container.add(dateInput.getDiv());
        v.container.add(idTripsInput.getDiv());
        v.printBody(updateButton, v.BackButton());
    }

    public void deleteTripBooking() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID de la Reserva de Viaje", 30);

        JButton deleteButton = new JButton("Eliminar Reserva de Viaje");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    tripBookingService.deleteTripbooking(id);
                    JOptionPane.showMessageDialog(v.container, "Reserva de viaje eliminada exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al eliminar la reserva de viaje: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.printBody(deleteButton, v.BackButton());
    }

    public void findAllTripBookings() {
         v = new ViewOut();
        List<TripBooking> tripBookings = tripBookingService.getAllTripbookings();
        String[] columnNames = { "ID ", "Fecha", "Id Viaje"};
        Object[][] data = new Object[tripBookings.size()][3];

        for (int i = 0; i < tripBookings.size(); i++) {
            TripBooking tripBooking = tripBookings.get(i);
            data[i][0] = tripBooking.getId();
            data[i][1] = tripBooking.getDate();
            data[i][2] = tripBooking.getIdtrips();
        }

        v.container.add(v.new VTable(columnNames, data).getDiv());
        v.printBody(v.BackButton());
    }
}
