package com.campuslands.modules.tripcrews.infrastructure.out;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

//import com.campuslands.modules.tripcrews.application.TripcrewsService;
//import com.campuslands.modules.tripcrews.infrastructure.in.TripcrewsAdapter;

public class TripcrewsOutModule {

    /*
     * protected Tripcew tripcrewsMySQL;
     * protected TripcrewsService tripcrewsService;
     * protected TripcrewsAdapter tripcrewsAdapter;
     * 
     * public TripcrewsOutModule() {
     * tripcrewsMySQL = new TripcrewsMySQL(); // Initialize TripcrewsMySQL instance
     * tripcrewsService = new TripcrewsService(tripcrewsMySQL); // Initialize
     * TripcrewsService with TripcrewsMySQL
     * tripcrewsAdapter = new TripcrewsAdapter(tripcrewsService); // Initialize
     * TripcrewsAdapter with TripcrewsService
     * }
     * 
     * public TripcrewsAdapter module() {
     * return tripcrewsAdapter; // Return the TripcrewsAdapter instance
     * }
     */
    public JMenu options() {
        JMenu option = new JMenu("Tipo de tripulantes");
        option.add(new JMenuItem(new AbstractAction("Registrar Tipo de tripulantes") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // employeesAdapter.createEmployee();

            }
        }));

        option.add(new JMenuItem(new AbstractAction("Actualizar Tipo de tripulantes") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // employeesAdapter.updateEmployee();

            }
        }));

        option.add(new JMenuItem(new AbstractAction("Eliminar Tipo de tripulantes") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // employeesAdapter.deleteEmployee();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Buscar Tipo de tripulantes") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // adapter.VFindAirportAll();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Mostrar todos los Tipo de tripulantes") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // employeesAdapter.findAllEmployees();
            }
        }));

        return option;
    }
}
