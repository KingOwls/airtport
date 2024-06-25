package com.campuslands.modules.flightfares.infrastructure.out;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.campuslands.modules.flightfares.application.FlightfaresService;
import com.campuslands.modules.flightfares.infrastructure.in.FlightFaresAdapter;

public class FlightfaresOutModule {

    protected FlightFaresMySQL flightFaresMySQL;
    protected FlightfaresService flightFaresService;
    protected FlightFaresAdapter flightFaresAdapter;

    public FlightfaresOutModule() {
        flightFaresMySQL = new FlightFaresMySQL();
        flightFaresService = new FlightfaresService(flightFaresMySQL);
        flightFaresAdapter = new FlightFaresAdapter(flightFaresService);

    }

    public FlightFaresAdapter module() {
        return flightFaresAdapter; // Return the FlightFaresAdapter instance
    }

    public JMenu options() {
        JMenu option = new JMenu("Tarifas de vuelo");
        option.add(new JMenuItem(new AbstractAction("Registrar Tarifas de vuelo") {
            @Override
            public void actionPerformed(ActionEvent e) {
                flightFaresAdapter.createFlightfare();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Actualizar Tarifas de vuelo") {
            @Override
            public void actionPerformed(ActionEvent e) {
                flightFaresAdapter.updateFlightfare();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Eliminar Tarifas de vuelo") {
            @Override
            public void actionPerformed(ActionEvent e) {
                flightFaresAdapter.deleteFlightfare();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Buscar Tarifas de vuelo") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // adapter.VFindAirportAll();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Mostrar todos los Tarifas de vuelo") {
            @Override
            public void actionPerformed(ActionEvent e) {
                flightFaresAdapter.findAllFlightfares();
            }
        }));

        return option;
    }
}
