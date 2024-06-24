package com.campuslands.modules.planes.infrastructure.out;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.campuslands.modules.planes.application.PlanesService;
import com.campuslands.modules.planes.infrastructure.in.PlanesAdapter;

public class PlanesOutModule {

    protected PlanesMySqlRepository planesMySQL;
    protected PlanesService planesService;
    protected PlanesAdapter planesAdapter;

    public PlanesOutModule() {
        planesMySQL = new PlanesMySqlRepository(); // Initialize PlanesMySQL instance
        planesService = new PlanesService(planesMySQL); // Initialize PlanesService with PlanesMySQL
        planesAdapter = new PlanesAdapter(planesService); // Initialize PlanesAdapter with PlanesService
    }

    public PlanesAdapter module() {
        return planesAdapter; // Return the PlanesAdapter instance
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
