package com.campuslands.modules.revisions.infrastructure.out;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.campuslands.modules.revisions.application.RevisionsService;
import com.campuslands.modules.revisions.infrastructure.in.RevisionsAdapter;

public class RevisionsOutModule {

    protected RevisionsMySqlRepository revisionsMySQL;
    protected RevisionsService revisionsService;
    protected RevisionsAdapter revisionsAdapter;

    public RevisionsOutModule() {
        revisionsMySQL = new RevisionsMySqlRepository(); // Initialize RevisionsMySQL instance
        revisionsService = new RevisionsService(revisionsMySQL); // Initialize RevisionsService with RevisionsMySQL
        revisionsAdapter = new RevisionsAdapter(revisionsService); // Initialize RevisionsAdapter with RevisionsService
    }

    public RevisionsAdapter module() {
        return revisionsAdapter; // Return the RevisionsAdapter instance
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
