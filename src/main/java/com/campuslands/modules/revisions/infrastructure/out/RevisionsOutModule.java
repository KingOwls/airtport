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
        JMenu option = new JMenu("Revisiones");
        option.add(new JMenuItem(new AbstractAction("Registrar Revisiones") {
            @Override
            public void actionPerformed(ActionEvent e) {
                revisionsAdapter.createRevision();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Actualizar Revisiones") {
            @Override
            public void actionPerformed(ActionEvent e) {
                revisionsAdapter.updateRevision();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Eliminar Revisiones") {
            @Override
            public void actionPerformed(ActionEvent e) {
                revisionsAdapter.deleteRevision();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Buscar Revisiones") {
            @Override
            public void actionPerformed(ActionEvent e) {
                revisionsAdapter.findByIdRevisions();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Mostrar todos los Revisiones") {
            @Override
            public void actionPerformed(ActionEvent e) {
                revisionsAdapter.findAllRevisions();
            }
        }));

        return option;
    }

}
