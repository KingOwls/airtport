package com.campuslands.modules.statuses.infrastructure.out;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.campuslands.modules.statuses.application.StatusesService;
import com.campuslands.modules.statuses.infrastructure.in.StatusesAdapter;

public class StatusesOutModule {

    protected StatusesMySQL statusesMySQL;
    protected StatusesService statusesService;
    protected StatusesAdapter statusesAdapter;

    public StatusesOutModule() {
        statusesMySQL = new StatusesMySQL(); // Initialize StatusesMySQL instance
        statusesService = new StatusesService(statusesMySQL); // Initialize StatusesService with StatusesMySQL
        statusesAdapter = new StatusesAdapter(statusesService); // Initialize StatusesAdapter with StatusesService
    }

    public StatusesAdapter module() {
        return statusesAdapter; // Return the StatusesAdapter instance
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
