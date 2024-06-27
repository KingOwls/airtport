package com.campuslands.modules.countries.infrastructure.in;

import com.campuslands.modules.countries.domain.models.Country;
import com.campuslands.modules.countries.application.CountriesService;
import com.campuslands.views.infrastructure.out.ViewOut;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import java.util.Optional;

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
                    // v.showMessage("País agregado exitosamente.");
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
                    // v.showMessage("País actualizado exitosamente");
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
                    // v.showMessage("País borrado exitosamente.");
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
        List<Country> countries = countriesService.getAllCountries();
        String[] columnNames = { "ID", "Nombre" };
        Object[][] data = new Object[countries.size()][6];

        for (int i = 0; i < countries.size(); i++) {
            Country country = countries.get(i);
            data[i][0] = country.getId();
            data[i][1] = country.getName();
        }

        v.container.add(v.new VTable(columnNames, data).getDiv());
        v.printBody(v.BackButton());
    }

    public void findByIdCountry() {
        try {
            v = new ViewOut();
            ViewOut.VInput idInput = v.new VInput("Ingresa el ID del País a Buscar", 30);

            JButton searchButton = new JButton("Buscar País");
            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        JPanel lastWindow = v.body;
                        v = new ViewOut();
                        int id = idInput.getInt();
                        Optional<Country> countryOptional = countriesService.getCountryById(id);
                        if (countryOptional.isPresent()) {
                            Country country = countryOptional.get();
                            String[] columnNames = { "ID", "Nombre" };
                            Object[][] data = new Object[1][2];
                            data[0][0] = country.getId();
                            data[0][1] = country.getName();

                            v.container.add(v.new VTable(columnNames, data).getDiv());
                            v.printBody(v.BackButton("findByIdCountry", lastWindow));
                        } else {
                            JOptionPane.showMessageDialog(null, "No se encontró el país con el ID especificado",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Ingrese un valor numérico para el ID del país", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(v.container, "Error al buscar el país: " + ex.getMessage(),
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            v.container.add(idInput.getDiv());
            v.printBody(searchButton, v.BackButton());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al iniciar la búsqueda de país: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
