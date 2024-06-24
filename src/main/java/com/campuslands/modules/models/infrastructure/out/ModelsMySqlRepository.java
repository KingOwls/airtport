package com.campuslands.modules.models.infrastructure.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.campuslands.core.MySQL;
import com.campuslands.modules.models.domain.models.Models;
import com.campuslands.modules.models.domain.repository.ModelsRepository;

public class ModelsMySqlRepository extends MySQL implements ModelsRepository {

    public ModelsMySqlRepository() {
        super();
    }

    @Override
    public void save(Models models) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO models (name) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, models.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Models models) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE models SET name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, models.getName());
                statement.setInt(2, models.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Models> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM models WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Models models = new Models(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("manuFactureId")
                        );
                        return Optional.of(models);
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
            String query = "DELETE FROM models WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Models> findAll() {
        List<Models> models = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM models";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Models model = new Models(
                        resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("manuFactureId")
                    );
                    models.add(model);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return models;
    }
}
