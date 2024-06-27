package com.campuslands.views.infrastructure.in;

import com.campuslands.modules.airports.infrastructure.out.AirportsOutModule;
import com.campuslands.modules.customers.infrastructure.out.CustomersOutModule;
import com.campuslands.modules.documenttypes.infrastructure.out.DocumenttypesOutModule;
import com.campuslands.modules.planes.infrastructure.out.PlanesOutModule;
import com.campuslands.modules.revisions.infrastructure.out.RevisionsOutModule;
import com.campuslands.modules.tripbookingdetails.infrastructure.out.TripbookingdetailsOutModule;
import com.campuslands.modules.tripcrews.infrastructure.out.TripcrewsOutModule;
import com.campuslands.modules.trips.infrastructure.out.TripsOutModule;
import com.campuslands.views.application.ViewsService;
import com.campuslands.views.domain.models.Header;

public class ViewAdapter {

    ViewsService viewsService;

    public ViewAdapter(ViewsService viewsService) {
        this.viewsService = viewsService;
    }

    public void header(String rol) {
        Header.getInstance().newMenu();
        AirportsOutModule airportsOutModule = new AirportsOutModule();
        PlanesOutModule PlanesOutModule = new PlanesOutModule();
        DocumenttypesOutModule documentTypesOutModule = new DocumenttypesOutModule();
        TripsOutModule tripsOutModule = new TripsOutModule();
        TripcrewsOutModule tripcrewsOutModule = new TripcrewsOutModule();
        CustomersOutModule customersOutModule = new CustomersOutModule();
        RevisionsOutModule revisionsOutModule = new RevisionsOutModule();

        TripbookingdetailsOutModule tripbookingdetailsOutModule = new TripbookingdetailsOutModule();
        // TripsOutModule tripsOutModule = new TripsOutModule();
        switch (rol) {
            case "administrador":
                Header.getInstance().addOption(PlanesOutModule.options());
                // Registrar Avion
                // Consultar Avion
                // Actualizar Avion
                // Eliminar Avion

                Header.getInstance().addOption(tripcrewsOutModule.options());
                // Asignar Tripulación

                Header.getInstance().addOption(tripsOutModule.options());
                // Consultar Trayecto
                // Asignar Aeronave a Trayecto
                // Actualizar Trayecto
                // Eliminar Trayecto

                // Actualizar escala
                // Eliminar escala

                // Registrar tarifa de vuelo
                // Actualizar tarifa de vuelo
                // Eliminar tarifa de vuelo

                Header.getInstance().addOption(documentTypesOutModule.options());
                // Registrar tipo de documento
                // Actualizar tipo de documento
                // Eliminar tipo de documento

                Header.getInstance().addOption(airportsOutModule.options(true));
                // Registrar Aeropuerto
                // Consultar Aeropuerto
                // Actualizar Aeropuerto
                // Eliminar Aeropuerto

                // consultar Asignar Tripulación -ventas
                // consultar escalas de un trayecto -ventas
                // consultar tarifa de vuelo - ventas
                // consultar tipo de documento - ventas
                break;

            case "ventas":
                Header.getInstance().addOption(customersOutModule.options());
                // Consultar cliente
                // Actualizar cliente
                // Registrar cliente

                // consultar reserva
                // Crear reserva
                // Eliminar reserva

                // consultar vuelo

                break;
            case "tecnico":
                Header.getInstance().addOption(revisionsOutModule.options());
                // Actualizar revision
                // Eliminar revision
                // Consultar Historial de revisiones
                // registar revision

                break;

            case "cliente":

                Header.getInstance().addOption(tripsOutModule.options());
                // Buscar vuelos
                // Seleccionar vuelos
                // Añadir pasajeros
                // Seleccionar asientos
                // Realizar pago

                Header.getInstance().addOption(tripbookingdetailsOutModule.options());
                // consultar reserva
                // cancelar reserva de vuelo
                // modificar reserva

                break;
        }
    }

}
