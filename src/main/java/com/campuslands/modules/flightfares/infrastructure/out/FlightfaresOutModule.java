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
        JMenu option = new JMenu("Tipo de Documentos");
        option.add(new JMenuItem(new AbstractAction("Registrar Tipo de Documentos") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // employeesAdapter.createEmployee();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Actualizar Tipo de Documentos") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // employeesAdapter.updateEmployee();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Eliminar Tipo de Documentos") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // employeesAdapter.deleteEmployee();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Buscar Tipo de Documentos") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // adapter.VFindAirportAll();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Mostrar todos los Tipo de Documentos") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // employeesAdapter.findAllEmployees();
            }
        }));

        return option;
    }
}
