package com.campuslands.modules.cities.infrastructure.out;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.campuslands.modules.cities.application.CitiesService;
import com.campuslands.modules.cities.infrastructure.in.CitiesAdapter;

public class CitiesOutModule {

    protected CitiesMySQL citiesMySQL;
    protected CitiesService citiesService;
    protected CitiesAdapter citiesAdapter;

    public CitiesOutModule() {
        citiesMySQL = new CitiesMySQL();
        citiesService = new CitiesService(citiesMySQL);
        citiesAdapter = new CitiesAdapter(citiesService);
    }

    public CitiesAdapter module() {
        return citiesAdapter;
    }

    public JMenu options() {
        JMenu option = new JMenu("Ciudades");
        option.add(new JMenuItem(new AbstractAction("Registrar Ciudad") {
            @Override
            public void actionPerformed(ActionEvent e) {
                citiesAdapter.createCity();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Actualizar Ciudad") {
            @Override
            public void actionPerformed(ActionEvent e) {
                citiesAdapter.updateCity();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Eliminar Ciudad") {
            @Override
            public void actionPerformed(ActionEvent e) {
                citiesAdapter.deleteCity();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Buscar Ciudad") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // adapter.VFindAirportAll();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Mostrar todos los Ciudad") {
            @Override
            public void actionPerformed(ActionEvent e) {
                citiesAdapter.findCityAll();
            }
        }));

        return option;
    }
}
