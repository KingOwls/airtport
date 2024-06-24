package com.campuslands.modules.revemployee.infrastructure.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.campuslands.core.MySQL;
import com.campuslands.modules.revemployee.domain.models.Revemployee;
import com.campuslands.modules.revemployee.domain.repository.RevEmployeeRepository;

public class RevEmployeeMySqlRepository extends MySQL implements RevEmployeeRepository {

    public RevEmployeeMySqlRepository() {
        super();
    }

    @Override
    public void save(Revemployee revEmployee) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO revemployee (name) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, revEmployee.getIdEmployee());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Revemployee revEmployee) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE revemployee SET name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, revEmployee.getIdEmployee());
                statement.setInt(2, revEmployee.getIdRevision());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Revemployee> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM revemployee WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Revemployee revEmployee = new Revemployee(
                                resultSet.getInt("id"),
                                resultSet.getInt("idRevision"));
                        return Optional.of(revEmployee);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM revemployee WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Revemployee> findAll() {
        List<Revemployee> revEmployees = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM revemployee";
            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Revemployee revEmployee = new Revemployee(
                            resultSet.getInt("id"),
                            resultSet.getInt("idRevision"));
                    revEmployees.add(revEmployee);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revEmployees;
    }
}
