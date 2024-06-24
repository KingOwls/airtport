package com.campuslands.modules.manufacturers.infrastructure.out;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.campuslands.modules.manufacturers.application.ManufacturersService;
import com.campuslands.modules.manufacturers.infrastructure.in.ManufacturersAdapter;

public class ManufacturersOutModule {

    protected ManufacturersMySqlRepository manufacturersMySQL;
    protected ManufacturersService manufacturersService;
    protected ManufacturersAdapter manufacturersAdapter;

    public ManufacturersOutModule() {
        manufacturersMySQL = new ManufacturersMySqlRepository(); // Initialize ManufacturersMySQL instance
        manufacturersService = new ManufacturersService(manufacturersMySQL); // Initialize ManufacturersService with
                                                                             // ManufacturersMySQL
        manufacturersAdapter = new ManufacturersAdapter(manufacturersService); // Initialize ManufacturersAdapter with
                                                                               // ManufacturersService
    }

    public ManufacturersAdapter module() {
        return manufacturersAdapter; // Return the ManufacturersAdapter instance
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
