package com.campuslands.modules.customers.infrastructure.in;

import com.campuslands.modules.customers.domain.models.Customer;
import com.campuslands.modules.customers.application.CustomersService;
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CustomersAdapter {
    private ViewOut v;
    private final CustomersService customersService;

    public CustomersAdapter(CustomersService customersService) {
        this.customersService = customersService;
    }

    public void createCustomer() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Cliente", 30);
        ViewOut.VInput nameInput = v.new VInput("Ingresa el Nombre del Cliente", 30);
        ViewOut.VInput ageInput = v.new VInput("Ingresa la Edad del Cliente", 3);
        ViewOut.VInput idDocumentInput = v.new VInput("Ingresa el Documento de Identidad del Cliente", 20);
        ViewOut.VInput passwordInput = v.new VInput("Ingresa la contraseña del Cliente", 20);
        ViewOut.VInput emailInput = v.new VInput("Ingresa el email del Cliente", 20);

        JButton addButton = new JButton("Agregar Nuevo Cliente");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    String name = nameInput.getText();
                    int age = ageInput.getInt();
                    int idDocument = idDocumentInput.getInt();
                    String email = emailInput.getText();
                    String password = passwordInput.getText();
                    Customer customer = new Customer(id, name, age, idDocument, password, email);
                    customersService.createCustomer(customer);
                    // JOptionPane.showMessageDialog(v.container, "Cliente agregado exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al agregar el cliente: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.container.add(nameInput.getDiv());
        v.container.add(ageInput.getDiv());
        v.container.add(emailInput.getDiv());
        v.container.add(passwordInput.getDiv());
        v.container.add(idDocumentInput.getDiv());
        v.printBody(addButton, v.BackButton());
    }

    public void updateCustomer() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Cliente", 30);
        ViewOut.VInput nameInput = v.new VInput("Ingresa el Nombre del Cliente", 30);
        ViewOut.VInput ageInput = v.new VInput("Ingresa la Edad del Cliente", 3);
        ViewOut.VInput idDocumentInput = v.new VInput("Ingresa el Documento de Identidad del Cliente", 20);
        ViewOut.VInput passwordInput = v.new VInput("Ingresa el Documento de Identidad del Cliente", 20);
        ViewOut.VInput emailInput = v.new VInput("Ingresa el Documento de Identidad del Cliente", 20);

        JButton updateButton = new JButton("Actualizar Cliente");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    String name = nameInput.getText();
                    int age = ageInput.getInt();
                    int idDocument = idDocumentInput.getInt();
                    String email = emailInput.getText();
                    String password = passwordInput.getText();

                    Customer customer = new Customer(id, name, age, idDocument, password, email);
                    customersService.updateCustomer(customer);
                    // JOptionPane.showMessageDialog(v.container, "Cliente actualizado
                    // exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al actualizar el cliente: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.container.add(nameInput.getDiv());
        v.container.add(ageInput.getDiv());
        v.container.add(emailInput.getDiv());
        v.container.add(passwordInput.getDiv());
        v.container.add(idDocumentInput.getDiv());
        v.printBody(updateButton, v.BackButton());
    }

    public void deleteCustomer() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Cliente", 30);

        JButton deleteButton = new JButton("Eliminar Cliente");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    customersService.deleteCustomer(idInput.getInt());
                    // JOptionPane.showMessageDialog(v.container, "Cliente eliminado
                    // exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al eliminar el cliente: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.printBody(deleteButton, v.BackButton());
    }

    public void findAllCustomers() {
        v = new ViewOut();
        List<Customer> customers = customersService.getAllCustomers();
        String[] columnNames = { "ID", "Nombre" };
        Object[][] data = new Object[customers.size()][7];

        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            data[i][0] = customer.getId();
            data[i][1] = customer.getName();
            data[i][2] = customer.getAge();
            data[i][3] = customer.getIddocument();
            data[i][4] = customer.getPassword();
            data[i][5] = customer.getEmail();
            data[i][6] = customer.getPassword();
        }

        v.container.add(v.new VTable(columnNames, data).getDiv());
        v.printBody(v.BackButton());
    }
}
