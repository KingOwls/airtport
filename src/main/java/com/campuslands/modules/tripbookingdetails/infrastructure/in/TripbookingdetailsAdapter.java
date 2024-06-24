package com.campuslands.modules.tripbookingdetails.infrastructure.in;

import com.campuslands.modules.tripbookingdetails.domain.models.Tripbookingdetails;
import com.campuslands.modules.tripbookingdetails.application.TripbookingdetailsService; // Asegúrate de importar el servicio correcto
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TripbookingdetailsAdapter {
    private ViewOut v;
    private final TripbookingdetailsService tripbookingdetailsService;

    public TripbookingdetailsAdapter(TripbookingdetailsService tripbookingdetailsService) {
        this.tripbookingdetailsService = tripbookingdetailsService;
    }

    public void createTripbookingdetail() {
        v = new ViewOut();
        ViewOut.VInput idTripBookingInput = v.new VInput("Ingresa el ID de la Reserva de Viaje", 30);
        ViewOut.VInput idCustomersInput = v.new VInput("Ingresa el ID del Cliente", 30);
        ViewOut.VInput idFaresInput = v.new VInput("Ingresa el ID de la Tarifa de Vuelo", 30);

        JButton addButton = new JButton("Agregar Detalle de Reserva de Viaje");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idTripBooking = Integer.parseInt(idTripBookingInput.getText());
                    String idCustomers = idCustomersInput.getText();
                    int idFares = Integer.parseInt(idFaresInput.getText());

                    Tripbookingdetails tripbookingdetail = new Tripbookingdetails(0, idTripBooking, idCustomers,
                            idFares); // El ID se asignará automáticamente en la base de datos
                    tripbookingdetailsService.createTripbookingdetail(tripbookingdetail);
                    JOptionPane.showMessageDialog(v.container, "Detalle de Reserva de Viaje agregado exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al agregar el Detalle de Reserva de Viaje: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idTripBookingInput.getDiv());
        v.container.add(idCustomersInput.getDiv());
        v.container.add(idFaresInput.getDiv());
        v.printBody(addButton, v.BackButton());
    }

    public void updateTripbookingdetail() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Detalle de Reserva de Viaje", 30);
        ViewOut.VInput idTripBookingInput = v.new VInput("Ingresa el ID de la Reserva de Viaje", 30);
        ViewOut.VInput idCustomersInput = v.new VInput("Ingresa el ID del Cliente", 30);
        ViewOut.VInput idFaresInput = v.new VInput("Ingresa el ID de la Tarifa de Vuelo", 30);

        JButton updateButton = new JButton("Actualizar Detalle de Reserva de Viaje");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idInput.getText());
                    int idTripBooking = Integer.parseInt(idTripBookingInput.getText());
                    String idCustomers = idCustomersInput.getText();
                    int idFares = Integer.parseInt(idFaresInput.getText());

                    Tripbookingdetails tripbookingdetail = new Tripbookingdetails(id, idTripBooking, idCustomers,
                            idFares);
                    tripbookingdetailsService.updateTripbookingdetail(tripbookingdetail);
                    JOptionPane.showMessageDialog(v.container, "Detalle de Reserva de Viaje actualizado exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al actualizar el Detalle de Reserva de Viaje: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.container.add(idTripBookingInput.getDiv());
        v.container.add(idCustomersInput.getDiv());
        v.container.add(idFaresInput.getDiv());
        v.printBody(updateButton, v.BackButton());
    }

    public void deleteTripbookingdetail() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Detalle de Reserva de Viaje", 30);

        JButton deleteButton = new JButton("Eliminar Detalle de Reserva de Viaje");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idInput.getText());
                    tripbookingdetailsService.deleteTripbookingdetail(id);
                    JOptionPane.showMessageDialog(v.container, "Detalle de Reserva de Viaje eliminado exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al eliminar el Detalle de Reserva de Viaje: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.printBody(deleteButton, v.BackButton());
    }

    public void findAllTripbookingdetails() {
        v = new ViewOut();
        JButton findButton = new JButton("Buscar Todos los Detalles de Reserva de Viaje");
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    java.util.List<Tripbookingdetails> tripbookingdetails = tripbookingdetailsService
                            .getAllTripbookingdetails();
                    StringBuilder tripbookingdetailsList = new StringBuilder(
                            "Lista de Detalles de Reserva de Viaje:\n");
                    for (Tripbookingdetails tripbookingdetail : tripbookingdetails) {
                        tripbookingdetailsList.append("ID: ").append(tripbookingdetail.getId())
                                .append(", ID Reserva: ").append(tripbookingdetail.getIdtripbooking())
                                .append(", ID Cliente: ").append(tripbookingdetail.getIdcustomers())
                                .append(", ID Tarifa: ").append(tripbookingdetail.getIdfares())
                                .append("\n");
                    }
                    JOptionPane.showMessageDialog(v.container, tripbookingdetailsList.toString());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al buscar los Detalles de Reserva de Viaje: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.printBody(findButton, v.BackButton());
    }
}
