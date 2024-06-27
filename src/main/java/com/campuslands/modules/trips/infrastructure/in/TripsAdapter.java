package com.campuslands.modules.trips.infrastructure.in;

import com.campuslands.modules.trips.domain.models.Trips;
import com.campuslands.modules.trips.application.TripsService; // Asegúrate de importar el servicio correcto
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class TripsAdapter {
    private ViewOut v;
    private final TripsService tripsService;

    public TripsAdapter(TripsService tripsService) {
        this.tripsService = tripsService;
    }

    public void createTrip() {
        v = new ViewOut();
        // ViewOut.VInput idInput = v.new VInput("Ingresa el id del Viaje", 30);
        ViewOut.VDate dateInput = v.new VDate("Ingresa la Fecha del Viaje (YYYY-MM-DD)", "date");
        ViewOut.VInput priceInput = v.new VInput("Ingresa el Precio del Viaje", 30);
        ViewOut.VInput depurateInput = v.new VInput("Destino de salida", 30);
        ViewOut.VInput arrivalInput = v.new VInput("Destino de llegad", 30);

        JButton addButton = new JButton("Agregar Viaje");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // int id = idInput.getInt();
                    Date date = dateInput.getValue();
                    double price = Double.parseDouble(priceInput.getText());
                    String depurate_airport = depurateInput.getText();
                    String arrival_airport = depurateInput.getText();

                    Trips trip = new Trips(date, price, depurate_airport, arrival_airport); // El ID se asignará
                                                                                            // automáticamente en la
                                                                                            // base de datos
                    tripsService.createTrip(trip);
                    // JOptionPane.showMessageDialog(v.container, "Viaje agregado exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al agregar el Viaje: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // v.container.add(idInput.getDiv());
        v.container.add(dateInput.getDiv());
        v.container.add(priceInput.getDiv());
        v.container.add(depurateInput.getDiv());
        v.container.add(arrivalInput.getDiv());
        v.printBody(addButton, v.BackButton());
    }

    public void updateTrip() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Viaje", 30);
        ViewOut.VDate dateInput = v.new VDate("Ingresa la Fecha del Viaje (YYYY-MM-DD)", "date");
        ViewOut.VInput priceInput = v.new VInput("Ingresa el Precio del Viaje", 30);
        ViewOut.VInput depurateInput = v.new VInput("Destino de salida", 30);
        ViewOut.VInput arrivalInput = v.new VInput("Destino de llegada", 30);

        JButton updateButton = new JButton("Actualizar Viaje");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idInput.getText());
                    Date date = dateInput.getValue();
                    double price = Double.parseDouble(priceInput.getText());
                    String depurate_airport = depurateInput.getText();
                    String arrival_airport = depurateInput.getText();

                    Trips trip = new Trips(id, date, price, depurate_airport, arrival_airport);
                    tripsService.updateTrip(trip);
                    // JOptionPane.showMessageDialog(v.container, "Viaje actualizado
                    // exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al actualizar el Viaje: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.container.add(dateInput.getDiv());
        v.container.add(priceInput.getDiv());
        v.container.add(depurateInput.getDiv());
        v.container.add(arrivalInput.getDiv());
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
                    // JOptionPane.showMessageDialog(v.container, "Viaje eliminado exitosamente.");
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
        List<Trips> trips = tripsService.getAllTrips();
        String[] columnNames = { "ID ", "Precio", "Fecha", "aeropuerto de salida", "aeropuerto de llegada" };
        Object[][] data = new Object[trips.size()][5];

        for (int i = 0; i < trips.size(); i++) {
            Trips trip = trips.get(i);
            data[i][0] = trip.getId();
            data[i][1] = trip.getDate();
            data[i][2] = trip.getPrice();
            data[i][3] = trip.getDeparture_airport();
            data[i][4] = trip.getArrival_airport();
        }

        v.container.add(v.new VTable(columnNames, data).getDiv());
        v.printBody(v.BackButton());

    }

    public void findByIdTrip() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Viaje a Buscar", 30);

        JButton searchButton = new JButton("Buscar Viaje");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JPanel lastWindow = v.body;
                    v = new ViewOut();
                    int id = idInput.getInt();
                    Optional<Trips> tripOptional = tripsService.getTripById(id);

                    if (tripOptional.isPresent()) {

                        Trips trip = tripOptional.get();
                        String[] columnNames = { "ID", "Precio", "Fecha", "Aeropuerto de Salida",
                                "Aeropuerto de Llegada" };
                        Object[][] data = new Object[1][5];

                        data[0][0] = trip.getId();
                        data[0][1] = trip.getPrice();
                        data[0][2] = trip.getDate();
                        data[0][3] = trip.getDeparture_airport();
                        data[0][4] = trip.getArrival_airport();

                        v.container.add(v.new VTable(columnNames, data).getDiv());

                        v.printBody(v.BackButton("findByIdTrip", lastWindow));
                    } else {
                        JOptionPane.showMessageDialog(null, "No se Encontro el id", null, id);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al Buscar el viaje: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.printBody(searchButton, v.BackButton());
    }

}
