package com.campuslands.modules.manufacturers.infrastructure.in;

import com.campuslands.modules.manufacturers.domain.models.Manufacturers;
import com.campuslands.modules.manufacturers.application.ManufacturersService;
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ManufacturersAdapter {
    private ViewOut v;
    private final ManufacturersService manufacturersService;

    public ManufacturersAdapter(ManufacturersService manufacturersService) {
        this.manufacturersService = manufacturersService;
    }

    public void createManufacturer() {
        v = new ViewOut();
        ViewOut.VInput nameInput = v.new VInput("Ingresa el nombre del fabricante", 30);

        JButton addButton = new JButton("Agregar Nuevo Fabricante");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameInput.getText();
                    Manufacturers manufacturer = new Manufacturers(0, name);
                    manufacturersService.createManufacturer(manufacturer);
                    // JOptionPane.showMessageDialog(v.container, "Fabricante agregado
                    // exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al agregar el fabricante: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(nameInput.getDiv());
        v.printBody(addButton, v.BackButton());
    }

    public void updateManufacturer() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Fabricante", 30);
        ViewOut.VInput nameInput = v.new VInput("Ingresa el nombre del fabricante", 30);

        JButton updateButton = new JButton("Actualizar Fabricante");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    String name = nameInput.getText();
                    Manufacturers manufacturer = new Manufacturers(id, name);
                    manufacturersService.updateManufacturer(manufacturer);
                    // JOptionPane.showMessageDialog(v.container, "Fabricante actualizado
                    // exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al actualizar el fabricante: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.container.add(nameInput.getDiv());
        v.printBody(updateButton, v.BackButton());
    }

    public void deleteManufacturer() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Fabricante a Eliminar", 30);

        JButton deleteButton = new JButton("Eliminar Fabricante");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    manufacturersService.deleteManufacturer(id);
                    // JOptionPane.showMessageDialog(v.container, "Fabricante eliminado
                    // exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al eliminar el fabricante: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.printBody(deleteButton, v.BackButton());
    }

    public void findAllManufacturers() {
          v = new ViewOut();
        List<Manufacturers> Manufacturers = manufacturersService.getAllManufacturers();
        String[] columnNames = { "ID", "Nombre"};
        Object[][] data = new Object[Manufacturers.size()][2];

        for (int i = 0; i < Manufacturers.size(); i++) {
            Manufacturers Manufacturer = Manufacturers.get(i);
            data[i][0] = Manufacturer.getId();
            data[i][1] = Manufacturer.getName();
        }

        v.container.add(v.new VTable(columnNames, data).getDiv());
        v.printBody(v.BackButton());
    }
}
