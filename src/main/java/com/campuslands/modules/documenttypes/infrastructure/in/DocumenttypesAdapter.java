package com.campuslands.modules.documenttypes.infrastructure.in;

import com.campuslands.modules.documenttypes.domain.models.DocumentType;
import com.campuslands.modules.documenttypes.application.DocumenttypesService;
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

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

    public void findByIdDocumentType() {
        // Create a new instance of ViewOut for displaying the GUI components
        v = new ViewOut();

        // Create an input field for the document type ID
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Tipo de Documento a Buscar", 30);

        // Create a button for searching the document type
        JButton searchButton = new JButton("Buscar Tipo de Documento");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Store the previous window panel
                    JPanel lastWindow = v.body;

                    // Create a new instance of ViewOut
                    v = new ViewOut();

                    // Get the document type ID from the input field
                    int id = idInput.getInt();

                    // Fetch the document type by ID using the service
                    Optional<DocumentType> documentTypeOptional = documentTypesService.getDocumentTypeById(id);

                    if (documentTypeOptional.isPresent()) {
                        // If the document type is found, extract its details
                        DocumentType documentType = documentTypeOptional.get();
                        String[] columnNames = { "ID", "Nombre" };
                        Object[][] data = new Object[1][2];

                        data[0][0] = documentType.getId();
                        data[0][1] = documentType.getName();

                        // Add the document type details to the container in a table format
                        v.container.add(v.new VTable(columnNames, data).getDiv());

                        // Add a back button to return to the previous window
                        v.printBody(v.BackButton("findByIdDocumentType", lastWindow));
                    } else {
                        // If the document type is not found, show a message dialog
                        JOptionPane.showMessageDialog(null, "No se encontró el ID", null, id);
                    }
                } catch (Exception ex) {
                    // If an error occurs, show an error message dialog
                    JOptionPane.showMessageDialog(v.container,
                            "Error al buscar el tipo de documento: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add the input field and the search button to the container
        v.container.add(idInput.getDiv());
        v.printBody(searchButton, v.BackButton());
    }

}
