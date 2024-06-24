package com.campuslands.modules.revision_details.infrastructure.out;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.campuslands.modules.revision_details.application.RevisionDetailsService;
import com.campuslands.modules.revision_details.infrastructure.in.Revision_detailsAdapter;

public class Revision_detailsOutModule {

    protected Revision_detailsMySQL revisionDetailsMySQL;
    protected RevisionDetailsService revisionDetailsService;
    protected Revision_detailsAdapter revisionDetailsAdapter;

    public Revision_detailsOutModule() {
        revisionDetailsMySQL = new Revision_detailsMySQL(); // Initialize RevisionDetailsMySQL instance
        revisionDetailsService = new RevisionDetailsService(revisionDetailsMySQL); // Initialize RevisionDetailsService
                                                                                   // with RevisionDetailsMySQL
        revisionDetailsAdapter = new Revision_detailsAdapter(revisionDetailsService); // Initialize
                                                                                      // RevisionDetailsAdapter with
                                                                                      // RevisionDetailsService
    }

    public Revision_detailsAdapter module() {
        return revisionDetailsAdapter; // Return the RevisionDetailsAdapter instance
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
