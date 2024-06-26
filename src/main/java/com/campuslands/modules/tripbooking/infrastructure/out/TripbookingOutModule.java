package com.campuslands.modules.tripbooking.infrastructure.out;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.campuslands.modules.tripbooking.application.TripbookingService;
import com.campuslands.modules.tripbooking.infrastructure.in.TripbookingAdapter;

public class TripbookingOutModule {

    protected TripbookingMySQL tripbookingMySQL;
    protected TripbookingService tripbookingService;
    protected TripbookingAdapter tripbookingAdapter;

    public TripbookingOutModule() {
        tripbookingMySQL = new TripbookingMySQL(); // Initialize TripbookingMySQL instance
        tripbookingService = new TripbookingService(tripbookingMySQL); // Initialize TripbookingService with
                                                                       // TripbookingMySQL
        tripbookingAdapter = new TripbookingAdapter(tripbookingService); // Initialize TripbookingAdapter with
                                                                         // TripbookingService
    }

    public TripbookingAdapter module() {
        return tripbookingAdapter; // Return the TripbookingAdapter instance
    }

    public JMenu options() {
        JMenu option = new JMenu("Reserva de Viaje");
        option.add(new JMenuItem(new AbstractAction("Registrar Reserva de Viaje") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // employeesAdapter.createEmployee();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Actualizar Reserva de Viaje") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // employeesAdapter.updateEmployee();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Eliminar Reserva de Viaje") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // employeesAdapter.deleteEmployee();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Buscar Reserva de Viaje") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // adapter.VFindAirportAll();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Mostrar todos las Reserva de Viaje") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // employeesAdapter.findAllEmployees();
            }
        }));

        return option;
    }

}
