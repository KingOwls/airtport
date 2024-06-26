package com.campuslands.modules.tripbookingdetails.infrastructure.out;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.campuslands.modules.tripbookingdetails.application.TripbookingdetailsService;
import com.campuslands.modules.tripbookingdetails.infrastructure.in.TripbookingdetailsAdapter;

public class TripbookingdetailsOutModule {

    protected TripBookingdetailsMySQL tripBookingDetailsMySQL;
    protected TripbookingdetailsService tripBookingDetailsService;
    protected TripbookingdetailsAdapter tripBookingDetailsAdapter;

    public TripbookingdetailsOutModule() {
        tripBookingDetailsMySQL = new TripBookingdetailsMySQL();
        tripBookingDetailsAdapter = new TripbookingdetailsAdapter(tripBookingDetailsService);
    }

    public TripbookingdetailsAdapter module() {
        return tripBookingDetailsAdapter;
    }

    public JMenu options() {
        JMenu option = new JMenu("Detalles de reserva del viaje");
        option.add(new JMenuItem(new AbstractAction("Registrar Detalles de reserva del viaje") {
            @Override
            public void actionPerformed(ActionEvent e) {

                // employeesAdapter.createEmployee();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Actualizar Detalles de reserva del viaje") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // employeesAdapter.updateEmployee();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Eliminar Detalles de reserva del viaje") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // employeesAdapter.deleteEmployee();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Buscar Detalles de reserva del viaje") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // adapter.VFindAirportAll();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Mostrar todos los Detalles de reserva del viaje") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // employeesAdapter.findAllEmployees();
            }
        }));

        return option;
    }
}
