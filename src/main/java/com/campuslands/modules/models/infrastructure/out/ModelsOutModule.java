package com.campuslands.modules.models.infrastructure.out;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.campuslands.modules.models.application.ModelsService;
import com.campuslands.modules.models.infrastructure.in.ModelsAdapter;

public class ModelsOutModule {

    protected ModelsMySqlRepository modelsMySQL;
    protected ModelsService modelsService;
    protected ModelsAdapter modelsAdapter;

    public ModelsOutModule() {
        modelsMySQL = new ModelsMySqlRepository(); // Initialize ModelsMySQL instance
        modelsService = new ModelsService(modelsMySQL); // Initialize ModelsService with ModelsMySQL
        modelsAdapter = new ModelsAdapter(modelsService); // Initialize ModelsAdapter with ModelsService
    }

    public ModelsAdapter module() {
        return modelsAdapter; // Return the ModelsAdapter instance
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
