package com.campuslands.modules.countries.infrastructure.in;

import com.campuslands.modules.countries.domain.models.Country;
import com.campuslands.modules.countries.application.CountriesService;
import com.campuslands.views.infrastructure.out.ViewOut;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CountriesAdapter {

    private ViewOut v;
    private final CountriesService countriesService;

    public CountriesAdapter(CountriesService countriesService) {
        this.countriesService = countriesService;
    }

    public void createCountry() {
        v = new ViewOut();
        ViewOut.VInput nameInput = v.new VInput("Ingresa el Nombre del País", 30);

        JButton addButton = new JButton("Agregar Nuevo País");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Country country = new Country(0, nameInput.getText());
                    countriesService.createCountry(country);
                    v.showMessage("País agregado exitosamente.");
                } catch (Exception ex) {
                    v.showError("Error al agregar el país: " + ex.getMessage());
                }
            }
        });

        v.container.add(nameInput.getDiv());
        v.printBody(addButton, v.BackButton());
    }

    public void updateCountry() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el id del País", 30);
        ViewOut.VInput nameInput = v.new VInput("Ingresa el Nombre del País", 30);

        JButton updateButton = new JButton("Actualizar País");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Country country = new Country(idInput.getInt(), nameInput.getText());
                    countriesService.updateCountry(country);
                    v.showMessage("País actualizado exitosamente");
                } catch (Exception ex) {
                    v.showMessage("Error al actualizar el país: " + ex.getMessage());
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.container.add(nameInput.getDiv());
        v.printBody(updateButton, v.BackButton());
    }

    public void deleteCountry() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el id del País", 30);

        JButton deleteButton = new JButton("Borrar País");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    countriesService.deleteCountry(idInput.getInt());
                    v.showMessage("País borrado exitosamente.");
                } catch (Exception ex) {
                    v.showMessage("Error al borrar el país: " + ex.getMessage());
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.printBody(deleteButton, v.BackButton());
    }

    public void findAllCountries() {
        v = new ViewOut();
        JButton findButton = new JButton("Buscar Todos los Países");
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    java.util.List<Country> countries = countriesService.getAllCountries();
                    StringBuilder countriesList = new StringBuilder("Lista de Países:\n");
                    for (Country country : countries) {
                        countriesList.append("ID: ").append(country.getId()).append(", Nombre: ")
                                .append(country.getName()).append("\n");
                    }
                    v.showMessage(countriesList.toString());
                } catch (Exception ex) {
                    v.showMessage("Error al buscar los países: " + ex.getMessage());
                }
            }
        });

        v.printBody(findButton, v.BackButton());
    }
}
