package com.campuslands.modules.revision_details.infrastructure.in;

import com.campuslands.modules.revision_details.domain.models.RevisionDetails;
import com.campuslands.modules.revision_details.application.RevisionDetailsService;
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
                    JOptionPane.showMessageDialog(v.container, "Detalle de Revisión agregado exitosamente.");
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
                    JOptionPane.showMessageDialog(v.container, "Detalle de Revisión actualizado exitosamente.");
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
                    JOptionPane.showMessageDialog(v.container, "Detalle de Revisión eliminado exitosamente.");
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
        JButton findButton = new JButton("Buscar Todos los Detalles de Revisión");
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<RevisionDetails> revisionDetails = revisionDetailsService.getAllRevisionDetails();
                    StringBuilder revisionDetailsList = new StringBuilder("Lista de Detalles de Revisión:\n");
                    for (RevisionDetails detail : revisionDetails) {
                        revisionDetailsList.append("ID: ").append(detail.getId()).append(", Descripción: ")
                                .append(detail.getDescription()).append(", ID de Empleado: ")
                                .append(detail.getId_employee()).append("\n");
                    }
                    JOptionPane.showMessageDialog(v.container, revisionDetailsList.toString());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al buscar los detalles de revisión: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.printBody(findButton, v.BackButton());
    }
}
