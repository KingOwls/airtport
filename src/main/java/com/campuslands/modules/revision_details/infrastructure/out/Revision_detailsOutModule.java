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
        JMenu option = new JMenu("Detalles de Revisión");
        option.add(new JMenuItem(new AbstractAction("Registrar Detalles de Revisión") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // employeesAdapter.createEmployee();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Actualizar Detalles de Revisión") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // employeesAdapter.updateEmployee();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Eliminar Detalles de Revisión") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // employeesAdapter.deleteEmployee();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Buscar Detalles de Revisión") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // adapter.VFindAirportAll();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Mostrar todos los Detalles de Revisión") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // employeesAdapter.findAllEmployees();
            }
        }));

        return option;
    }
}
