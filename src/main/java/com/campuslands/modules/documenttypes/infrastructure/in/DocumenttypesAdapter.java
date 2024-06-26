package com.campuslands.modules.documenttypes.infrastructure.in;

import com.campuslands.modules.documenttypes.domain.models.DocumentType;
import com.campuslands.modules.documenttypes.application.DocumenttypesService;
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DocumenttypesAdapter {
    private ViewOut v;
    private final DocumenttypesService documentTypesService;

    public DocumenttypesAdapter(DocumenttypesService documentTypesService) {
        this.documentTypesService = documentTypesService;
    }

    public void createDocumentType() {
        v = new ViewOut();
        ViewOut.VInput nameInput = v.new VInput("Ingresa el Nombre del Tipo de Documento", 30);

        JButton addButton = new JButton("Agregar Nuevo Tipo de Documento");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameInput.getText();
                    DocumentType documentType = new DocumentType(0, name);
                    documentTypesService.createDocumentType(documentType);
                    // JOptionPane.showMessageDialog(v.container, "Tipo de Documento agregado
                    // exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al agregar el tipo de documento: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(nameInput.getDiv());
        v.printBody(addButton, v.BackButton());
    }

    public void updateDocumentType() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Tipo de Documento", 30);
        ViewOut.VInput nameInput = v.new VInput("Ingresa el Nombre del Tipo de Documento", 30);

        JButton updateButton = new JButton("Actualizar Tipo de Documento");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    String name = nameInput.getText();
                    DocumentType documentType = new DocumentType(id, name);
                    documentTypesService.updateDocumentType(documentType);
                    // JOptionPane.showMessageDialog(v.container, "Tipo de Documento actualizado
                    // exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al actualizar el tipo de documento: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.container.add(nameInput.getDiv());
        v.printBody(updateButton, v.BackButton());
    }

    public void deleteDocumentType() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Tipo de Documento", 30);

        JButton deleteButton = new JButton("Eliminar Tipo de Documento");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    documentTypesService.deleteDocumentType(id);
                    // JOptionPane.showMessageDialog(v.container, "Tipo de Documento eliminado
                    // exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al eliminar el tipo de documento: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.printBody(deleteButton, v.BackButton());
    }

    public void findAllDocumentTypes() {
        v = new ViewOut();
        List<DocumentType> customers = documentTypesService.getAllDocumentTypes();
        String[] columnNames = { "ID", "Nombre" };
        Object[][] data = new Object[customers.size()][2];

        for (int i = 0; i < customers.size(); i++) {
            DocumentType customer = customers.get(i);
            data[i][0] = customer.getId();
            data[i][1] = customer.getName();
        }

        v.container.add(v.new VTable(columnNames, data).getDiv());
        v.printBody(v.BackButton());
    }
}
