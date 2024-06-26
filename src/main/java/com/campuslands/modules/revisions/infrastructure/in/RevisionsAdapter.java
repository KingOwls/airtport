package com.campuslands.modules.revisions.infrastructure.in;

import com.campuslands.modules.revisions.domain.models.Revisions;
import com.campuslands.modules.revisions.application.RevisionsService;
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

public class RevisionsAdapter {
    private ViewOut v;
    private final RevisionsService revisionsService;

    public RevisionsAdapter(RevisionsService revisionsService) {
        this.revisionsService = revisionsService;
    }

    public void createRevision() {
        v = new ViewOut();
        ViewOut.VDate revisionDateInput = v.new VDate("Ingresa la Fecha de la Revisión (YYYY-MM-DD)", "date");
        ViewOut.VInput idPlaneInput = v.new VInput("Ingresa el ID del Avión", 30);

        JButton addButton = new JButton("Agregar Nueva Revisión");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Date revisionDate = revisionDateInput.getValue();
                    int idPlane = idPlaneInput.getInt();
                    Revisions revision = new Revisions(0, revisionDate, idPlane);
                    revisionsService.createRevision(revision);
                    JOptionPane.showMessageDialog(v.container, "Revisión agregada exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al agregar la revisión: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(revisionDateInput.getDiv());
        v.container.add(idPlaneInput.getDiv());
        v.printBody(addButton, v.BackButton());
    }

    public void updateRevision() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID de la Revisión", 30);
        ViewOut.VDate revisionDateInput = v.new VDate("Ingresa la Fecha de la Revisión (YYYY-MM-DD)", "date");
        ViewOut.VInput idPlaneInput = v.new VInput("Ingresa el ID del Avión", 30);

        JButton updateButton = new JButton("Actualizar Revisión");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    Date revisionDate = revisionDateInput.getValue();
                    int idPlane = idPlaneInput.getInt();
                    Revisions revision = new Revisions(id, revisionDate, idPlane);
                    revisionsService.updateRevision(revision);
                    JOptionPane.showMessageDialog(v.container, "Revisión actualizada exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al actualizar la revisión: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.container.add(revisionDateInput.getDiv());
        v.container.add(idPlaneInput.getDiv());
        v.printBody(updateButton, v.BackButton());
    }

    public void deleteRevision() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID de la Revisión a Eliminar", 30);

        JButton deleteButton = new JButton("Eliminar Revisión");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    revisionsService.deleteRevision(id);
                    JOptionPane.showMessageDialog(v.container, "Revisión eliminada exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al eliminar la revisión: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.printBody(deleteButton, v.BackButton());
    }

    public void findAllRevisions() {
        v = new ViewOut();
        JButton findButton = new JButton("Buscar Todas las Revisiones");
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Revisions> revisions = revisionsService.getAllRevisions();
                    StringBuilder revisionsList = new StringBuilder("Lista de Revisiones:\n");
                    for (Revisions revision : revisions) {
                        revisionsList.append("ID: ").append(revision.getId()).append(", Fecha: ")
                                .append(revision.getRevision_date()).append(", ID del Avión: ")
                                .append(revision.getId_plane()).append("\n");
                    }
                    JOptionPane.showMessageDialog(v.container, revisionsList.toString());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al buscar las revisiones: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.printBody(findButton, v.BackButton());
    }
}
