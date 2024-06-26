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
        JMenu option = new JMenu("modelo");
        option.add(new JMenuItem(new AbstractAction("Registrar modelo") {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelsAdapter.createModel();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Actualizar modelo") {
            @Override
            public void actionPerformed(ActionEvent e) {
             modelsAdapter.updateModel();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Eliminar modelo") {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelsAdapter.deleteModel();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Buscar modelo") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // adapter.VFindAirportAll();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Mostrar todos los modelo") {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelsAdapter.findAllModels();
            }
        }));

        return option;
    }
}
