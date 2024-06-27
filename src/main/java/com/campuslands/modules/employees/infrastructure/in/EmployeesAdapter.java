package com.campuslands.modules.employees.infrastructure.in;

import com.campuslands.modules.employees.domain.models.Employee;
import com.campuslands.modules.employees.application.EmployeesService;
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class EmployeesAdapter {
    private ViewOut v;
    private final EmployeesService employeesService;

    public EmployeesAdapter(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    public void createEmployee() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el id del Empleado", 30);
        ViewOut.VInput nameInput = v.new VInput("Ingresa el Nombre del Empleado", 30);
        ViewOut.VInput idRolInput = v.new VInput("Ingresa el ID del Rol del Empleado", 10);
        ViewOut.VInput emailInput = v.new VInput("Ingresa el ID del Rol del Empleado", 10);
        ViewOut.VInput passwordInput = v.new VInput("Ingresa el ID del Rol del Empleado", 10);
        ViewOut.VDate ingressDateInput = v.new VDate("Ingresa la Fecha de Ingreso del Empleado (YYYY-MM-DD)", ("Date"));
        ViewOut.VInput idAirlineInput = v.new VInput("Ingresa el ID de la Aerolínea del Empleado", 10);
        ViewOut.VInput idAirportInput = v.new VInput("Ingresa el ID del Aeropuerto del Empleado", 30);

        JButton addButton = new JButton("Agregar Nuevo Empleado");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    String name = nameInput.getText();
                    int idRol = idRolInput.getInt();
                    String email = emailInput.getText();
                    String password = passwordInput.getText();
                    Date ingressDate = ingressDateInput.getValue();
                    int idAirline = idAirlineInput.getInt();
                    int idAirport = idAirportInput.getInt();

                    Employee employee = new Employee(id, name, email, ingressDate, idAirline, idAirport, password,
                            idRol);
                    employeesService.createEmployee(employee);
                    // JOptionPane.showMessageDialog(v.container, "Empleado agregado
                    // exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al agregar el empleado: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.container.add(nameInput.getDiv());
        v.container.add(emailInput.getDiv());
        v.container.add(ingressDateInput.getDiv());
        v.container.add(idAirlineInput.getDiv());
        v.container.add(idAirportInput.getDiv());
        v.container.add(passwordInput.getDiv());
        v.container.add(idRolInput.getDiv());
        v.printBody(addButton, v.BackButton());
    }

    public void updateEmployee() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Empleado", 30);
        ViewOut.VInput nameInput = v.new VInput("Ingresa el Nombre del Empleado", 30);
        ViewOut.VInput emailInput = v.new VInput("Ingresa el ID del Rol del Empleado", 10);
        ViewOut.VInput passwordInput = v.new VInput("Ingresa el ID del Rol del Empleado", 10);
        ViewOut.VInput idRolInput = v.new VInput("Ingresa el ID del Rol del Empleado", 10);
        ViewOut.VInput ingressDateInput = v.new VInput("Ingresa la Fecha de Ingreso del Empleado (YYYY-MM-DD)", 20);
        ViewOut.VInput idAirlineInput = v.new VInput("Ingresa el ID de la Aerolínea del Empleado", 10);
        ViewOut.VInput idAirportInput = v.new VInput("Ingresa el ID del Aeropuerto del Empleado", 30);

        JButton updateButton = new JButton("Actualizar Empleado");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    String name = nameInput.getText();
                    int idRol = idRolInput.getInt();
                    String email = emailInput.getText();
                    String password = passwordInput.getText();
                    Date ingressDate = Date.valueOf(ingressDateInput.getText());
                    int idAirline = idAirlineInput.getInt();
                    int idAirport = idAirportInput.getInt();

                    Employee employee = new Employee(id, name, email, ingressDate, idAirline, idAirport, password,
                            idRol);
                    employeesService.updateEmployee(employee);
                    // JOptionPane.showMessageDialog(v.container, "Empleado actualizado
                    // exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al actualizar el empleado: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.container.add(nameInput.getDiv());
        v.container.add(emailInput.getDiv());
        v.container.add(ingressDateInput.getDiv());
        v.container.add(idAirlineInput.getDiv());
        v.container.add(idAirportInput.getDiv());
        v.container.add(passwordInput.getDiv());
        v.container.add(idRolInput.getDiv());
        v.printBody(updateButton, v.BackButton());
    }

    public void deleteEmployee() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Empleado", 30);

        JButton deleteButton = new JButton("Eliminar Empleado");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    employeesService.deleteEmployee(id);
                    // JOptionPane.showMessageDialog(v.container, "Empleado eliminado
                    // exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al eliminar el empleado: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.printBody(deleteButton, v.BackButton());
    }

    public void findAllEmployees() {
        v = new ViewOut();
        List<Employee> employees = employeesService.getAllEmployees();
        String[] columnNames = { "ID", "Nombre", "Email", "Fecha Ingreso", "Id Aerolinea", "Id Aeropuerto",
                "contraseña",
                " Id Rol" };
        Object[][] data = new Object[employees.size()][8];

        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            data[i][0] = employee.getId();
            data[i][1] = employee.getName();
            data[i][2] = employee.getEmail();
            data[i][3] = employee.getIngressdate();
            data[i][4] = employee.getIdairline();
            data[i][5] = employee.getIdairport();
            data[i][6] = employee.getPassword();
            data[i][7] = employee.getIdrol();
        }

        v.container.add(v.new VTable(columnNames, data).getDiv());
        v.printBody(v.BackButton());
    }

    public void findByIdEmployee() {
        try {
            v = new ViewOut();
            ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Empleado a Buscar", 30);

            JButton searchButton = new JButton("Buscar Empleado");
            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        JPanel lastWindow = v.body; // Store the previous window panel
                        v = new ViewOut(); // Create a new instance of ViewOut
                        int id = idInput.getInt();
                        Optional<Employee> employeeOptional = employeesService.getEmployeeById(id);
                        if (employeeOptional.isPresent()) {
                            Employee employee = employeeOptional.get();
                            String[] columnNames = { "ID", "Nombre", "Email", "Fecha Ingreso", "ID Aerolínea",
                                    "ID Aeropuerto", "Contraseña", "ID Rol" };
                            Object[][] data = new Object[1][8];
                            data[0][0] = employee.getId();
                            data[0][1] = employee.getName();
                            data[0][2] = employee.getEmail();
                            data[0][3] = employee.getIngressdate();
                            data[0][4] = employee.getIdairline();
                            data[0][5] = employee.getIdairport();
                            data[0][6] = employee.getPassword();
                            data[0][7] = employee.getIdrol();

                            v.container.add(v.new VTable(columnNames, data).getDiv());
                            v.printBody(v.BackButton("findByIdEmployee", lastWindow));
                        } else {
                            JOptionPane.showMessageDialog(null, "No se encontró el empleado con el ID especificado",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Ingrese un valor numérico para el ID del empleado",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(v.container, "Error al buscar el empleado: " + ex.getMessage(),
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            v.container.add(idInput.getDiv());
            v.printBody(searchButton, v.BackButton());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al iniciar la búsqueda de empleado: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
