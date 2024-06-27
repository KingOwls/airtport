package com.campuslands.modules.models.infrastructure.in;

import com.campuslands.modules.models.domain.models.Models;
import com.campuslands.modules.models.application.ModelsService;
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ModelsAdapter {
    private ViewOut v;
    private final ModelsService modelsService;

    public ModelsAdapter(ModelsService modelsService) {
        this.modelsService = modelsService;
    }

    public void createModel() {
        v = new ViewOut();
        ViewOut.VInput nameInput = v.new VInput("Ingresa el nombre del modelo", 30);
        ViewOut.VInput manufactureIdInput = v.new VInput("Ingresa el ID del fabricante", 30);

        JButton addButton = new JButton("Agregar Nuevo Modelo");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameInput.getText();
                    int manufactureId = manufactureIdInput.getInt();
                    Models model = new Models(0, name, manufactureId);
                    modelsService.createModel(model);
                   // JOptionPane.showMessageDialog(v.container, "Modelo agregado exitosamente.");// ELIMINAR ESTOS DEL
                                                                                                // CRUD
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al agregar el modelo: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(nameInput.getDiv());
        v.container.add(manufactureIdInput.getDiv());
        v.printBody(addButton, v.BackButton());
    }

    public void updateModel() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Modelo", 30);
        ViewOut.VInput nameInput = v.new VInput("Ingresa el nombre del modelo", 30);
        ViewOut.VInput manufactureIdInput = v.new VInput("Ingresa el ID del fabricante", 30);

        JButton updateButton = new JButton("Actualizar Modelo");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    String name = nameInput.getText();
                    int manufactureId = manufactureIdInput.getInt();
                    Models model = new Models(id, name, manufactureId);
                    modelsService.updateModel(model);
                    //JOptionPane.showMessageDialog(v.container, "Modelo actualizado exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al actualizar el modelo: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.container.add(nameInput.getDiv());
        v.container.add(manufactureIdInput.getDiv());
        v.printBody(updateButton, v.BackButton());
    }

    public void deleteModel() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Modelo a Eliminar", 30);

        JButton deleteButton = new JButton("Eliminar Modelo");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    modelsService.deleteModel(id);
                  //  JOptionPane.showMessageDialog(v.container, "Modelo eliminado exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al eliminar el modelo: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.printBody(deleteButton, v.BackButton());
    }

    public void findAllModels() {
        v = new ViewOut();
        List<Models> Models = modelsService.getAllModels();
        String[] columnNames = { "ID", "Nombre", "ID manufactura"};
        Object[][] data = new Object[Models.size()][3];

        for (int i = 0; i < Models.size(); i++) {
            Models Model = Models.get(i);
            data[i][0] = Model.getId();
            data[i][1] = Model.getName();
            data[i][2] = Model.getManuFactureId();
        }

        v.container.add(v.new VTable(columnNames, data).getDiv());
        v.printBody(v.BackButton());
    }


}
