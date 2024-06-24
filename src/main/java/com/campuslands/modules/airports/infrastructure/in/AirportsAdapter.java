package com.campuslands.modules.airports.infrastructure.in;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

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
        ViewOut.VInput nameInput = v.new VInput("Ingresa el Nombre de la Aerolínea", 30);

        JButton addButton = new JButton("Agregar Nueva Aerolínea");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Airport airports = new Airport(nameInput.getText());
                    airportsService.createAirport(airports);
                    v.showMessage("Aerolínea creada exitosamente.");
                } catch (Exception ex) {
                    v.showError("Error al crear la aerolínea: " + ex.getMessage());
                }
            }
        });

        v.container.add(nameInput.getDiv());
        v.printBody(addButton, v.BackButton());
    }

    public void VUpdateAirport() {
        v = new ViewOut();

        ViewOut.VInput idInput = v.new VInput("Ingresa el id de la Aerolínea", 30);
        ViewOut.VInput nameInput = v.new VInput("Ingresa el Nombre de la Aerolínea", 30);
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
                    v.showMessage("Aerolínea actualizada exitosamente.");
                } catch (Exception ex) {
                    v.showError("Error al actualizar la aerolínea: " + ex.getMessage());
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

        ViewOut.VInput idInput = v.new VInput("Ingresa el id de la Aerolínea", 30);

        JButton deleteButton = new JButton("Borrar Aerolínea");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    airportsService.deleteAirport(id);
                    v.showMessage("Aerolínea borrada exitosamente.");
                } catch (Exception ex) {
                    v.showError("Error al borrar la aerolínea: " + ex.getMessage());
                }
            }
        });

        v.container.add(idInput.getDiv());

        v.printBody(deleteButton, v.BackButton());
    }

    public void VFindAirportAll() {
        v = new ViewOut();

        JButton findButton = new JButton("Buscar");
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Aquí debes implementar la lógica para obtener todas las aerolíneas
                    // Ejemplo: List<Airlines> airlinesList = airlinesService.findAllAirlines();
                    // y luego mostrar los resultados en la vista.
                    v.showMessage("Resultado de búsqueda: ");
                } catch (Exception ex) {
                    v.showError("Error al buscar aeropuerto: " + ex.getMessage());
                }
            }
        });

        v.printBody(findButton, v.BackButton());
    }

}