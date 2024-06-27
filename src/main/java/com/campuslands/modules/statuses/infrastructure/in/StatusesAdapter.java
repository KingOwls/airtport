package com.campuslands.modules.statuses.infrastructure.in;

import com.campuslands.modules.statuses.domain.models.Statuses;
import com.campuslands.modules.statuses.application.StatusesService; // Asegúrate de importar el servicio correcto
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class StatusesAdapter {
    private ViewOut v;
    private final StatusesService statusesService;

    public StatusesAdapter(StatusesService statusesService) {
        this.statusesService = statusesService;
    }

    public void createStatus() {
        v = new ViewOut();
        ViewOut.VInput nameInput = v.new VInput("Ingresa el Nombre del Estado", 50);

        JButton addButton = new JButton("Agregar Nuevo Estado");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameInput.getText();

                    Statuses status = new Statuses(0, name);
                    statusesService.createStatus(status);
                    // JOptionPane.showMessageDialog(v.container, "Estado agregado exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al agregar el estado: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(nameInput.getDiv());
        v.printBody(addButton, v.BackButton());
    }

    public void updateStatus() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Estado", 30);
        ViewOut.VInput nameInput = v.new VInput("Ingresa el Nombre del Estado", 50);

        JButton updateButton = new JButton("Actualizar Estado");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    String name = nameInput.getText();

                    Statuses status = new Statuses(id, name);
                    statusesService.updateStatus(status);
                    // JOptionPane.showMessageDialog(v.container, "Estado actualizado
                    // exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al actualizar el estado: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.container.add(nameInput.getDiv());
        v.printBody(updateButton, v.BackButton());
    }

    public void deleteStatus() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Estado", 30);

        JButton deleteButton = new JButton("Eliminar Estado");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    statusesService.deleteStatus(id);
                    // JOptionPane.showMessageDialog(v.container, "Estado eliminado exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al eliminar el estado: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.printBody(deleteButton, v.BackButton());
    }

    public void findAllStatuses() {
        v = new ViewOut();
        List<Statuses> statuses = statusesService.getAllStatuses();
        String[] columnNames = { "ID ", "Nombre" };
        Object[][] data = new Object[statuses.size()][2];

        for (int i = 0; i < statuses.size(); i++) {
            Statuses statuse = statuses.get(i);
            data[i][0] = statuse.getId();
            data[i][1] = statuse.getName();
        }

        v.container.add(v.new VTable(columnNames, data).getDiv());
        v.printBody(v.BackButton());

    }

    public void findByIdStatus() {
        try {
            v = new ViewOut();
            ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Estado a Buscar", 30);

            JButton searchButton = new JButton("Buscar Estado");
            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        JPanel lastWindow = v.body; // Store the previous window panel
                        v = new ViewOut(); // Create a new instance of ViewOut
                        int id = idInput.getInt();
                        Optional<Statuses> statusOptional = statusesService.getStatusById(id);
                        if (statusOptional.isPresent()) {
                            Statuses status = statusOptional.get();
                            String[] columnNames = { "ID", "Nombre" };
                            Object[][] data = new Object[1][2];
                            data[0][0] = status.getId();
                            data[0][1] = status.getName();

                            v.container.add(v.new VTable(columnNames, data).getDiv());
                            v.printBody(v.BackButton("findByIdStatus", lastWindow));
                        } else {
                            JOptionPane.showMessageDialog(null, "No se encontró el estado con el ID especificado",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Ingrese un valor numérico para el ID del estado", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(v.container, "Error al buscar el estado: " + ex.getMessage(),
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            v.container.add(idInput.getDiv());
            v.printBody(searchButton, v.BackButton());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al iniciar la búsqueda de estado: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
