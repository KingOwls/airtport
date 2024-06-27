package com.campuslands.modules.revision_details.infrastructure.in;

import com.campuslands.modules.revision_details.domain.models.RevisionDetails;
import com.campuslands.modules.revision_details.application.RevisionDetailsService;
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class Revision_detailsAdapter {
    private ViewOut v;
    private final RevisionDetailsService revisionDetailsService;

    public Revision_detailsAdapter(RevisionDetailsService revisionDetailsService) {
        this.revisionDetailsService = revisionDetailsService;
    }

    public void createRevisionDetail() {
        v = new ViewOut();
        ViewOut.VInput descriptionInput = v.new VInput("Ingresa la descripción de la revisión", 30);
        ViewOut.VInput idEmployeeInput = v.new VInput("Ingresa el ID del empleado", 30);

        JButton addButton = new JButton("Agregar Nuevo Detalle de Revisión");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String description = descriptionInput.getText();
                    int idEmployee = idEmployeeInput.getInt();
                    RevisionDetails revisionDetail = new RevisionDetails(0, description, idEmployee);
                    revisionDetailsService.createRevisionDetail(revisionDetail);
                    // JOptionPane.showMessageDialog(v.container, "Detalle de Revisión agregado
                    // exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al agregar el detalle de revisión: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(descriptionInput.getDiv());
        v.container.add(idEmployeeInput.getDiv());
        v.printBody(addButton, v.BackButton());
    }

    public void updateRevisionDetail() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Detalle de Revisión", 30);
        ViewOut.VInput descriptionInput = v.new VInput("Ingresa la descripción de la revisión", 30);
        ViewOut.VInput idEmployeeInput = v.new VInput("Ingresa el ID del empleado", 30);

        JButton updateButton = new JButton("Actualizar Detalle de Revisión");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    String description = descriptionInput.getText();
                    int idEmployee = idEmployeeInput.getInt();
                    RevisionDetails revisionDetail = new RevisionDetails(id, description, idEmployee);
                    revisionDetailsService.updateRevisionDetail(revisionDetail);
                    // JOptionPane.showMessageDialog(v.container, "Detalle de Revisión actualizado
                    // exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al actualizar el detalle de revisión: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.container.add(descriptionInput.getDiv());
        v.container.add(idEmployeeInput.getDiv());
        v.printBody(updateButton, v.BackButton());
    }

    public void deleteRevisionDetail() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Detalle de Revisión a Eliminar", 30);

        JButton deleteButton = new JButton("Eliminar Detalle de Revisión");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    revisionDetailsService.deleteRevisionDetail(id);
                    // JOptionPane.showMessageDialog(v.container, "Detalle de Revisión eliminado
                    // exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al eliminar el detalle de revisión: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.printBody(deleteButton, v.BackButton());
    }

    public void findAllRevisionDetails() {
        v = new ViewOut();
        List<RevisionDetails> revisionDetails = revisionDetailsService.getAllRevisionDetails();
        String[] columnNames = { "ID Empleado", "Descripción", "ID Empleado" };
        Object[][] data = new Object[revisionDetails.size()][3];

        for (int i = 0; i < revisionDetails.size(); i++) {
            RevisionDetails revisionDetail = revisionDetails.get(i);
            data[i][0] = revisionDetail.getId();
            data[i][1] = revisionDetail.getDescription();
            data[i][2] = revisionDetail.getId_employee();
        }

        v.container.add(v.new VTable(columnNames, data).getDiv());
        v.printBody(v.BackButton());
    }

    public void findByIdRevisionDetails() {
        try {
            v = new ViewOut();
            ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Detalle de Revisión a Buscar", 30);

            JButton searchButton = new JButton("Buscar Detalle de Revisión");
            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        JPanel lastWindow = v.body; // Store the previous window panel
                        v = new ViewOut(); // Create a new instance of ViewOut
                        int id = idInput.getInt();
                        Optional<RevisionDetails> revisionDetailOptional = revisionDetailsService
                                .getRevisionDetailById(id);
                        if (revisionDetailOptional.isPresent()) {
                            RevisionDetails revisionDetail = revisionDetailOptional.get();
                            String[] columnNames = { "ID Detalle", "Descripción", "ID Empleado" };
                            Object[][] data = new Object[1][3];
                            data[0][0] = revisionDetail.getId();
                            data[0][1] = revisionDetail.getDescription();
                            data[0][2] = revisionDetail.getId_employee();

                            v.container.add(v.new VTable(columnNames, data).getDiv());
                            v.printBody(v.BackButton("findByIdRevisionDetails", lastWindow));
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "No se encontró el detalle de revisión con el ID especificado", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null,
                                "Ingrese un valor numérico para el ID del detalle de revisión", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(v.container,
                                "Error al buscar el detalle de revisión: " + ex.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            v.container.add(idInput.getDiv());
            v.printBody(searchButton, v.BackButton());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Error al iniciar la búsqueda de detalle de revisión: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
