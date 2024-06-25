package com.campuslands.modules.airports.infrastructure.out;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.campuslands.modules.airports.application.AirportsService;
import com.campuslands.modules.airports.infrastructure.in.AirportsAdapter;

public class AirportsOutModule {

    protected AirportsMySQL MySQL;
    protected AirportsService service;
    protected AirportsAdapter adapter;

    public AirportsOutModule() {
        MySQL = new AirportsMySQL();
        service = new AirportsService(MySQL);
        adapter = new AirportsAdapter(service);

    }

    public AirportsAdapter module() {
        return adapter;
    }

    public JMenu options(Boolean CRUD) {
        JMenu option = new JMenu("Aeropuertos");
        if (CRUD) {
            option.add(new JMenuItem(new AbstractAction("Registrar Aeropuertos") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    adapter.VCreateAirport();
                }
            }));

            option.add(new JMenuItem(new AbstractAction("Actualizar Aeropuertos") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    adapter.VUpdateAirport();
                }
            }));

            option.add(new JMenuItem(new AbstractAction("Eliminar Aeropuertos") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    adapter.VDeleteAirport();
                }
            }));
        }

        option.add(new JMenuItem(new AbstractAction("Buscar Aeropuerto") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // adapter.VFindAirportAll();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Mostrar todos los Aeropuertos") {
            @Override
            public void actionPerformed(ActionEvent e) {
                adapter.VFindAirportAll();
            }
        }));

        return option;
    }
}
