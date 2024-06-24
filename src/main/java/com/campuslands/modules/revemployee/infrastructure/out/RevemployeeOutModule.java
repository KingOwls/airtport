package com.campuslands.modules.revemployee.infrastructure.out;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.campuslands.modules.revemployee.application.RevemployeeService;
import com.campuslands.modules.revemployee.infrastructure.in.RevemployeeAdapter;

public class RevemployeeOutModule {

    protected RevEmployeeMySqlRepository revemployeeMySQL;
    protected RevemployeeService revemployeeService;
    protected RevemployeeAdapter revemployeeAdapter;

    public RevemployeeOutModule() {
        revemployeeMySQL = new RevEmployeeMySqlRepository(); // Initialize RevemployeeMySQL instance
        revemployeeService = new RevemployeeService(revemployeeMySQL); // Initialize RevemployeeService with
                                                                       // RevemployeeMySQL
        revemployeeAdapter = new RevemployeeAdapter(revemployeeService); // Initialize RevemployeeAdapter with
                                                                         // RevemployeeService
    }

    public RevemployeeAdapter module() {
        return revemployeeAdapter; // Return the RevemployeeAdapter instance
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
