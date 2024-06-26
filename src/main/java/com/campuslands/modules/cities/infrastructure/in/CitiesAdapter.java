package com.campuslands.modules.cities.infrastructure.in;

import javax.swing.*;

import com.campuslands.views.infrastructure.out.ViewOut;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.campuslands.modules.cities.application.CitiesService;
import com.campuslands.modules.cities.domain.models.Cities;

public class CitiesAdapter {

    private ViewOut v;
    private final CitiesService citiesService;

    public CitiesAdapter(CitiesService citiesService) {
        this.citiesService = citiesService;
    }

    public void createCity() {
        v = new ViewOut();
        ViewOut.VInput nameInput = v.new VInput("Ingresa el Nombre de la Ciudad", 30);
        ViewOut.VInput countryInput = v.new VInput("Ingresa el ID del País", 30);

        JButton addButton = new JButton("Agregar Nueva Ciudad");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Cities city = new Cities(0, nameInput.getText(), countryInput.getInt());
                    citiesService.createCities(city);
                    // v.showMessage("Ciudad creada exitosamente.");

                } catch (Exception ex) {
                    v.showError("Error al crear la ciudad: " + ex.getMessage());
                }
            }
        });

        v.container.add(nameInput.getDiv());
        v.container.add(countryInput.getDiv());
        v.printBody(addButton, v.BackButton());
    }

    public void updateCity() {
        v = new ViewOut();

        ViewOut.VInput idInput = v.new VInput("Ingresa el id de la Ciudad", 30);
        ViewOut.VInput nameInput = v.new VInput("Ingresa el Nombre de la Ciudad", 30);
        ViewOut.VInput countryInput = v.new VInput("Ingresa el ID del País", 30);

        JButton updateButton = new JButton("Actualizar Ciudad");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    String name = nameInput.getText();
                    int idCountry = countryInput.getInt();
                    Cities city = new Cities(id, name, idCountry);
                    citiesService.updateCities(city);
                    // v.showMessage("Ciudad actualizada exitosamente.");

                } catch (Exception ex) {
                    v.showError("Error al actualizar la ciudad: " + ex.getMessage());
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.container.add(nameInput.getDiv());
        v.container.add(countryInput.getDiv());

        v.printBody(updateButton, v.BackButton());
    }

    public void deleteCity() {
        v = new ViewOut();

        ViewOut.VInput idInput = v.new VInput("Ingresa el id de la Ciudad", 30);

        JButton deleteButton = new JButton("Borrar Ciudad");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    citiesService.deleteCities(id);
                    // v.showMessage("Ciudad borrada exitosamente.");
                } catch (Exception ex) {
                    v.showError("Error al borrar la ciudad: " + ex.getMessage());
                }
            }
        });

        v.container.add(idInput.getDiv());

        v.printBody(deleteButton, v.BackButton());
    }

    public void findCityAll() {
        v = new ViewOut();
        List<Cities> cities = citiesService.getAllCities();
        String[] columnNames = { "ID", "Ciudad", "id Pais" };
        Object[][] data = new Object[cities.size()][3];

        for (int i = 0; i < cities.size(); i++) {
            Cities plane = cities.get(i);
            data[i][0] = plane.getId();
            data[i][1] = plane.getName();
            data[i][2] = plane.getIdCountry();
        }

        v.container.add(v.new VTable(columnNames, data).getDiv());
        v.printBody(v.BackButton());
    }
}
