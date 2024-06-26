package com.campuslands.modules.tripulationroles.infrastructure.out;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.campuslands.modules.tripulationroles.application.TripulationrolesService;
import com.campuslands.modules.tripulationroles.infrastructure.in.TripulationrolesAdapter;

public class TripulationrolesOutModule {

    protected TripulationRolesMySQL tripulationRolesMySqlRepository;
    protected TripulationrolesService service;
    protected TripulationrolesAdapter adapter;

    public TripulationrolesOutModule() {
        tripulationRolesMySqlRepository = new TripulationRolesMySQL();
        service = new TripulationrolesService(tripulationRolesMySqlRepository);
        adapter = new TripulationrolesAdapter(service);

    }

    public TripulationrolesAdapter module() {
        return adapter;
    }

    public JMenu options() {
        JMenu option = new JMenu("Roles de tripulación");
        option.add(new JMenuItem(new AbstractAction("Registrar Rol de tripulación") {
            @Override
            public void actionPerformed(ActionEvent e) {
                adapter.createTripulationRole();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Actualizar Rol de tripulación") {
            @Override
            public void actionPerformed(ActionEvent e) {
                adapter.updateTripulationRole();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Eliminar Rol de tripulación") {
            @Override
            public void actionPerformed(ActionEvent e) {
                adapter.deleteTripulationRole();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Buscar rol por ID") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Listar todos los roles") {
            @Override
            public void actionPerformed(ActionEvent e) {
                adapter.findAllTripulationRoles();
            }
        }));

        return option;
    }
}
