package com.campuslands.modules.auth.infrastructure.out;

import com.campuslands.core.MySQL;
import com.campuslands.modules.auth.domain.repository.AuthRepository;

import com.campuslands.modules.auth.domain.models.Auth;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Optional;

import javax.swing.JOptionPane;

public class AuthMySQL extends MySQL implements AuthRepository {

    public AuthMySQL() {
        super();
    }

    @Override
    public Optional<Auth> login(String email, String pas) {
        Optional<Auth> user = findEmployee(email, pas);
        if (user.isEmpty()) {
            user = findClient(email, pas);
            return user;
        } else {
            return user;
        }
    }

    public Optional<Auth> findEmployee(String email, String pas) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT employees.id, email, password, tr.name AS rol " +
                    "FROM employees " +
                    "INNER JOIN tripulationroles AS tr ON employees.idrol = tr.id " +
                    "WHERE email = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, email);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        if (resultSet.getString("password").equals(pas)) {
                            Auth auth = Auth.getInstance();
                            auth.setUid(resultSet.getString("id"));
                            auth.setEmail(resultSet.getString("email"));
                            auth.setPassword(resultSet.getString("password"));
                            auth.setRol(resultSet.getString("rol"));
                            return Optional.of(auth);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "ERROR", 0);
        }
        return Optional.empty();
    }

    public Optional<Auth> findClient(String email, String pas) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, email, password FROM customers"
                    + " WHERE email = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, email);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        if (resultSet.getString("password").equals(pas)) {
                            Auth auth = Auth.getInstance();
                            auth.setUid(resultSet.getString("id"));
                            auth.setEmail(resultSet.getString("email"));
                            auth.setPassword(resultSet.getString("password"));
                            auth.setRol("cliente");
                            return Optional.of(auth);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "ERROR", 0);
        }
        return Optional.empty();
    }
}
