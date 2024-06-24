package com.campuslands.modules.trips.infrastructure.out;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.campuslands.modules.trips.application.TripsService;
import com.campuslands.modules.trips.infrastructure.in.TripsAdapter;

import java.awt.event.ActionEvent;

public class TripsOutModule {

    protected TripsMySqlRepository tripsMySQL;
    protected TripsService tripsService;
    protected TripsAdapter tripsAdapter;

    public TripsOutModule() {
        tripsMySQL = new TripsMySqlRepository(); // Initialize TripsMySQL instance
        tripsService = new TripsService(tripsMySQL); // Initialize TripsService with TripsMySQL
        tripsAdapter = new TripsAdapter(tripsService); // Initialize TripsAdapter with TripsService
    }

    public TripsAdapter module() {
        return tripsAdapter; // Return the TripsAdapter instance
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
