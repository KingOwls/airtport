package com.campuslands.modules.tripbookingdetails.infrastructure.out;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.campuslands.modules.tripbookingdetails.application.TripbookingdetailsService;
import com.campuslands.modules.tripbookingdetails.domain.models.Tripbookingdetails;
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
                tripBookingDetailsAdapter.createTripbookingdetail();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Actualizar Detalles de reserva del viaje") {
            @Override
            public void actionPerformed(ActionEvent e) {
                tripBookingDetailsAdapter.updateTripbookingdetail();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Eliminar Detalles de reserva del viaje") {
            @Override
            public void actionPerformed(ActionEvent e) {
                tripBookingDetailsAdapter.createTripbookingdetail();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Buscar Detalles de reserva del viaje") {
            @Override
            public void actionPerformed(ActionEvent e) {
                tripBookingDetailsAdapter.findByIdTripBookingDetails();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Mostrar todos los Detalles de reserva del viaje") {
            @Override
            public void actionPerformed(ActionEvent e) {
                tripBookingDetailsAdapter.findAllTripbookingdetails();
            }
        }));

        return option;
    }

    public Map<Integer, String> selectOptions() {
        Map<Integer, String> optionsMap = new HashMap<>();

        // Assuming tripBookingDetailsService.getAllTripbookingdetails() returns a
        // List<TripBookingDetails>
        List<Tripbookingdetails> tripDetailsList = tripBookingDetailsService.getAllTripbookingdetails();

        for (Tripbookingdetails trip : tripDetailsList) {
            optionsMap.put(trip.getId(), trip.getIdcustomers() + trip.getIdfares());
        }

        return optionsMap;
    }
}
