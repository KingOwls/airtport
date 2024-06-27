package com.campuslands.modules.customers.infrastructure.in;

import com.campuslands.modules.customers.domain.models.Customer;
import com.campuslands.modules.customers.application.CustomersService;
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class CustomersAdapter {
    private ViewOut v;
    private final CustomersService customersService;

    public CustomersAdapter(CustomersService customersService) {
        this.customersService = customersService;
    }

    public void createCustomer() {
        v = new ViewOut();
        // ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Cliente", 30);
        ViewOut.VInput nameInput = v.new VInput("Ingresa el Nombre del Cliente", 30);
        ViewOut.VInput ageInput = v.new VInput("Ingresa la Edad del Cliente", 3);
        ViewOut.VInput idDocumentInput = v.new VInput("Ingresa el Documento de Identidad del Cliente", 20);
        ViewOut.VInput idDocumentTypeInput = v.new VInput("Ingresa el Tipo de documento", 20);
        ViewOut.VInput passwordInput = v.new VInput("Ingresa la contraseña del Cliente", 20);
        ViewOut.VInput emailInput = v.new VInput("Ingresa el email del Cliente", 20);

        JButton addButton = new JButton("Agregar Nuevo Cliente");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // int id = idInput.getInt();
                    String name = nameInput.getText();
                    int age = ageInput.getInt();
                    String idDocument = idDocumentInput.getText();
                    int idDocumentType = idDocumentTypeInput.getInt();
                    String email = emailInput.getText();
                    String password = passwordInput.getText();
                    Customer customer = new Customer(name, age, idDocumentType, idDocument, password, email);
                    customersService.createCustomer(customer);
                    // JOptionPane.showMessageDialog(v.container, "Cliente agregado exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al agregar el cliente: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // v.container.add(idInput.getDiv());
        v.container.add(nameInput.getDiv());
        v.container.add(ageInput.getDiv());
        v.container.add(emailInput.getDiv());
        v.container.add(passwordInput.getDiv());
        v.container.add(idDocumentInput.getDiv());
        v.container.add(idDocumentTypeInput.getDiv());
        v.printBody(addButton, v.BackButton());
    }

    public void updateCustomer() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Cliente", 30);
        ViewOut.VInput nameInput = v.new VInput("Ingresa el Nombre del Cliente", 30);
        ViewOut.VInput ageInput = v.new VInput("Ingresa la Edad del Cliente", 3);
        ViewOut.VInput idDocumentInput = v.new VInput("Ingresa el Documento de Identidad del Cliente", 20);
        ViewOut.VInput idDocumentTypeInput = v.new VInput("Ingresa el Tipo de documento", 20);
        ViewOut.VInput passwordInput = v.new VInput("Ingresa la contraseña del Cliente", 20);
        ViewOut.VInput emailInput = v.new VInput("Ingresa el email del Cliente", 20);

        JButton updateButton = new JButton("Actualizar Cliente");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    String name = nameInput.getText();
                    int age = ageInput.getInt();
                    String idDocument = idDocumentInput.getText();
                    int idDocumentType = idDocumentTypeInput.getInt();
                    String email = emailInput.getText();
                    String password = passwordInput.getText();

                    Customer customer = new Customer(id, name, age, idDocumentType, idDocument, password, email);
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
        v.container.add(idDocumentTypeInput.getDiv());
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
        String[] columnNames = { "ID", "Nombre", "Edad", "Id Document", "Contraseña", "Correo Electronico", };
        Object[][] data = new Object[customers.size()][7];

        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            data[i][0] = customer.getId();
            data[i][1] = customer.getName();
            data[i][2] = customer.getAge();
            data[i][3] = customer.getId_document();
            data[i][4] = customer.getId_document_type();
            data[i][5] = customer.getPassword();
            data[i][6] = customer.getEmail();
        }

        v.container.add(v.new VTable(columnNames, data).getDiv());
        v.printBody(v.BackButton());
    }

    public void findByIdCustomer() {
        try {
            v = new ViewOut();
            ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Cliente a Buscar", 30);

            JButton searchButton = new JButton("Buscar Cliente");
            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        JPanel lastWindow = v.body;
                        v = new ViewOut();

                        int id = idInput.getInt();
                        Optional<Customer> customerOptional = customersService.getCustomerById(id);
                        if (customerOptional.isPresent()) {
                            Customer customer = customerOptional.get();
                            String[] columnNames = { "ID", "Nombre", "Edad", "ID Documento", "Contraseña",
                                    "Correo Electrónico" };
                            Object[][] data = new Object[1][7];
                            data[0][0] = customer.getId();
                            data[0][1] = customer.getName();
                            data[0][2] = customer.getAge();
                            data[0][3] = customer.getId_document();
                            data[0][4] = customer.getId_document_type();
                            data[0][5] = customer.getPassword();
                            data[0][6] = customer.getEmail();

                            v.container.add(v.new VTable(columnNames, data).getDiv());
                            v.printBody(v.BackButton("findByIdCustomer", lastWindow));
                        } else {
                            JOptionPane.showMessageDialog(null, "No se encontró el cliente con el ID especificado",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Ingrese un valor numérico para el ID del cliente", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(v.container, "Error al buscar el cliente: " + ex.getMessage(),
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            v.container.add(idInput.getDiv());
            v.printBody(searchButton, v.BackButton());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al iniciar la búsqueda de cliente: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
