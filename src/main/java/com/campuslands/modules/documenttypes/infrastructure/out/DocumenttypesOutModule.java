package com.campuslands.modules.documenttypes.infrastructure.out;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.campuslands.modules.documenttypes.application.DocumenttypesService;
import com.campuslands.modules.documenttypes.infrastructure.in.DocumenttypesAdapter;

public class DocumenttypesOutModule {

    protected DocumenttypesMySQL DocumentTypesMySQL;
    protected DocumenttypesService DocumentTypesService;
    protected DocumenttypesAdapter DocumentTypesAdapter;

    public DocumenttypesOutModule() {
        DocumentTypesMySQL = new DocumenttypesMySQL();
        DocumentTypesService = new DocumenttypesService(DocumentTypesMySQL);
        DocumentTypesAdapter = new DocumenttypesAdapter(DocumentTypesService);
    }

    public DocumenttypesAdapter module() {
        return DocumentTypesAdapter;
    }

    public JMenu options() {
        JMenu option = new JMenu("Tipo de Documentos");
        option.add(new JMenuItem(new AbstractAction("Registrar Tipo de Documentos") {
            @Override
            public void actionPerformed(ActionEvent e) {
                DocumentTypesAdapter.createDocumentType();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Actualizar Tipo de Documentos") {
            @Override
            public void actionPerformed(ActionEvent e) {
                DocumentTypesAdapter.updateDocumentType();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Eliminar Tipo de Documentos") {
            @Override
            public void actionPerformed(ActionEvent e) {
                DocumentTypesAdapter.deleteDocumentType();
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
                DocumentTypesAdapter.findAllDocumentTypes();
            }
        }));

        return option;
    }
}
