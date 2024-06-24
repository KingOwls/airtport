package com.campuslands.views.infrastructure.in;

import com.campuslands.modules.airports.infrastructure.out.AirportsOutModule;
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
        switch (rol) {
            case "administrador":
                Header.getInstance().addOption(airportsOutModule.options());
                // Asignar Tripulación

                // Consultar Trayecto
                // Asignar Aeronave a Trayecto
                // Actualizar Trayecto
                // Eliminar Trayecto

                // Actualizar escala
                // Eliminar escala

                // Registrar tarifa de vuelo
                // Actualizar tarifa de vuelo
                // Eliminar tarifa de vuelo

                // Registrar tipo de documento
                // Actualizar tipo de documento
                // Eliminar tipo de documento

                // Registrar Aeropuerto
                // Consultar Aeropuerto
                // Actualizar Aeropuerto
                // Eliminar Aeropuerto

                // consultar vuelo -ventas
                // consultar Asignar Tripulación -ventas
                // consultar escalas de un trayecto -ventas
                // consultar tarifa de vuelo - ventas
                // consultar tipo de documento - ventas

                break;

            case "ventas":

                // Consultar cliente
                // Actualizar cliente
                // Registrar cliente

                // consultar reserva
                // Crear reserva
                // Eliminar reserva
                break;
            case "tecnico":

                // Actualizar revision
                // Eliminar revision
                // Consultar Historial de revisiones
                // registar revision

                break;

            case "cliente":

                // Buscar vuelos
                // Seleccionar vuelos
                // Añadir pasajeros
                // Seleccionar asientos
                // Realizar pago

                // consultar reserva
                // cancelar reserva de vuelo
                // modificar reserva

                break;
        }
    }

}
