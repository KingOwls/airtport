package com.campuslands.modules.countries.infrastructure.out;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.campuslands.modules.countries.application.CountriesService;
import com.campuslands.modules.countries.infrastructure.in.CountriesAdapter;

public class CountriesOutModule {

    protected CountriesMySQL countriesMySQL;
    protected CountriesService countriesService;
    protected CountriesAdapter countriesAdapter;

    public CountriesOutModule() {
        countriesMySQL = new CountriesMySQL();
        countriesService = new CountriesService(countriesMySQL);
        countriesAdapter = new CountriesAdapter(countriesService);
    }

    public CountriesAdapter module() {
        return countriesAdapter;
    }

    public JMenu options() {
        JMenu option = new JMenu("Paises");
        option.add(new JMenuItem(new AbstractAction("Registrar Pais") {
            @Override
            public void actionPerformed(ActionEvent e) {
                countriesAdapter.createCountry();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Actualizar Pais") {
            @Override
            public void actionPerformed(ActionEvent e) {
                countriesAdapter.updateCountry();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Eliminar Pais") {
            @Override
            public void actionPerformed(ActionEvent e) {
                countriesAdapter.deleteCountry();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Buscar Pais") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // adapter.VFindAirportAll();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Mostrar todos los Pais") {
            @Override
            public void actionPerformed(ActionEvent e) {
                countriesAdapter.findAllCountries();
            }
        }));

        return option;
    }
}
