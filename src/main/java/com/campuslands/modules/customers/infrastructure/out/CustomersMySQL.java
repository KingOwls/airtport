package com.campuslands.modules.customers.infrastructure.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;
import com.campuslands.core.MySQL;
import com.campuslands.modules.customers.domain.models.Customer;
import com.campuslands.modules.customers.domain.repository.CustomersRepository;

public class CustomersMySQL extends MySQL implements CustomersRepository {

    public CustomersMySQL() {
        super();
    }

    @Override
    public void save(Customer customers) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO customers (name) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, customers.getId());
                statement.setString(2, customers.getName());
                statement.setInt(3, customers.getAge());
                statement.setInt(4, customers.getIddocument());
                statement.setString(5, customers.getPassword());
                statement.setString(6, customers.getEmail());
                statement.executeUpdate();
                JOptionPane.showInputDialog(null, "Cliente creado exitosa mente", "INSERT", 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "ERROR", 0);
        }
    }

    @Override
    public void update(Customer customers) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE customers SET name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, customers.getId());
                statement.setString(2, customers.getName());
                statement.setInt(3, customers.getAge());
                statement.setInt(4, customers.getIddocument());
                statement.setString(5, customers.getPassword());
                statement.setString(6, customers.getEmail());
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente", "UPDATE", 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "ERROR", 0);
        }
    }

    @Override
    public Optional<Customer> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM customers WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Customer customers = new Customer(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("age"),
                                resultSet.getInt("iddocument"),
                                resultSet.getString("email"),
                                resultSet.getString("password"));
                        return Optional.of(customers);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showInputDialog(null, e, "Error", 0);
        }
        return Optional.empty();
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM customers WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Cliente borrado exitosamente", "DELETE", 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showInputDialog(null, e, "Error", 0);
        }
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM customers";
            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Customer customer = new Customer(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getInt("iddocument"),
                            resultSet.getString("email"),
                            resultSet.getString("password"));
                    customers.add(customer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showInputDialog(null, e, "Error", 0);
        }
        return customers;
    }
}
