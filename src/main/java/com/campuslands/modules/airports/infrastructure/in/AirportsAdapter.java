package com.campuslands.modules.airports.infrastructure.in;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.util.List;
import java.util.Optional;

import com.campuslands.views.infrastructure.out.ViewOut;
import com.campuslands.modules.airports.domain.models.Airport;
import com.campuslands.modules.airports.application.AirportsService;

/**
 * AirportsAdapter
 */
public class AirportsAdapter {
    ViewOut v;
    private final AirportsService airportsService;

    public AirportsAdapter(AirportsService airportsService) {
        this.airportsService = airportsService;
    }

    public void VCreateAirport() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el Nombre del Aeropuerto", 30);
        ViewOut.VInput nameInput = v.new VInput("Ingresa el Nombre del Aeropuerto", 30);
        ViewOut.VInput idCity = v.new VInput("Ingresa el id de la ciudad", 30);

        JButton addButton = new JButton("Agregar Nueva Aeropuerto");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Airport airports = new Airport(idInput.getInt(), nameInput.getText(), idCity.getInt());
                    airportsService.createAirport(airports);
                    //v.showMessage("Aeropuerto creada exitosamente.");
                } catch (Exception ex) {
                    v.showError("Error al crear la Aeropuerto: " + ex.getMessage());
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.container.add(nameInput.getDiv());
        v.container.add(idCity.getDiv());
        v.printBody(addButton, v.BackButton());
    }

    public void VUpdateAirport() {
        v = new ViewOut();

        ViewOut.VInput idInput = v.new VInput("Ingresa el id de la Aeropuerto", 30);
        ViewOut.VInput nameInput = v.new VInput("Ingresa el Nombre de la Aeropuerto", 30);
        ViewOut.VInput idCityInput = v.new VInput("Ingresa el id de la Ciudad", 30);

        JButton updateButton = new JButton("Actualizar Aeropuerto");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    String name = nameInput.getText();
                    int idCity = idCityInput.getInt();
                    Airport airports = new Airport(id, name, idCity);
                    airportsService.updateAirport(airports);
                    //v.showMessage("Aeropuerto actualizada exitosamente.");
                } catch (Exception ex) {
                    v.showError("Error al actualizar la Aeropuerto: " + ex.getMessage());
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.container.add(nameInput.getDiv());
        v.container.add(idCityInput.getDiv());
        v.printBody(updateButton, v.BackButton());
    }

    public void VDeleteAirport() {
        v = new ViewOut();

        ViewOut.VInput idInput = v.new VInput("Ingresa el id de la Aeropuerto", 30);

        JButton deleteButton = new JButton("Borrar Aeropuerto");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    airportsService.deleteAirport(id);
                    //v.showMessage("Aeropuerto borrada exitosamente.");
                } catch (Exception ex) {
                    v.showError("Error al borrar la Aeropuerto: " + ex.getMessage());
                }
            }
        });

        v.container.add(idInput.getDiv());

        v.printBody(deleteButton, v.BackButton());
    }

    public void VFindAirportAll() {
        v = new ViewOut();
        List<Airport> airports = airportsService.getAllAirports();
        String[] columnNames = { "ID", "Nombre", "Id City" };
        Object[][] data = new Object[airports.size()][3];

        for (int i = 0; i < airports.size(); i++) {
            Airport airport = airports.get(i);
            data[i][0] = airport.getId();
            data[i][1] = airport.getName();
            data[i][2] = airport.getIdCity();
        }

        v.container.add(v.new VTable(columnNames, data).getDiv());
        v.printBody(v.BackButton());
    }

    public void findByIdAirport() {
        // Create a new instance of ViewOut for displaying the GUI components
        v = new ViewOut();

        // Create an input field for the airport ID
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Aeropuerto a Buscar", 30);

        // Create a button for searching the airport
        JButton searchButton = new JButton("Buscar Aeropuerto");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Store the previous window panel
                    JPanel lastWindow = v.body;

                    // Create a new instance of ViewOut
                    v = new ViewOut();

                    // Get the airport ID from the input field
                    int id = idInput.getInt();

                    // Fetch the airport by ID using the service
                    Optional<Airport> airportOptional = airportsService.getAirportById(id);

                    if (airportOptional.isPresent()) {
                        // If the airport is found, extract its details
                        Airport airport = airportOptional.get();
                        String[] columnNames = { "ID", "Nombre", "Id City" };
                        Object[][] data = new Object[1][3];

                        data[0][0] = airport.getId();
                        data[0][1] = airport.getName();
                        data[0][2] = airport.getIdCity();

                        // Add the airport details to the container in a table format
                        v.container.add(v.new VTable(columnNames, data).getDiv());

                        // Add a back button to return to the previous window
                        v.printBody(v.BackButton("findByIdAirport", lastWindow));
                    } else {
                        // If the airport is not found, show a message dialog
                        JOptionPane.showMessageDialog(null, "No se encontró el ID", null, id);
                    }
                } catch (Exception ex) {
                    // If an error occurs, show an error message dialog
                    JOptionPane.showMessageDialog(v.container,
                            "Error al buscar el aeropuerto: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add the input field and the search button to the container
        v.container.add(idInput.getDiv());
        v.printBody(searchButton, v.BackButton());
    }

}