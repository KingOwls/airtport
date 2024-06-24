package com.campuslands.modules.gates.infrastructure.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.campuslands.core.MySQL;
import com.campuslands.modules.gates.domain.models.Gates;
import com.campuslands.modules.gates.domain.repository.GatesRepository;

public class GatesMySQL extends MySQL implements GatesRepository {

    public GatesMySQL() {
        super();
    }

    @Override
    public void save(Gates gate) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO gates (gate_number, airport_id) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, gate.getGetNumber());
                statement.setString(2, gate.getIdAirport());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Gates gate) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE gates SET gate_number = ?, airport_id = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, gate.getGetNumber());
                statement.setString(2, gate.getIdAirport());
                statement.setInt(3, gate.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Gates> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM gates WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Gates gate = new Gates(
                            resultSet.getInt("id"),
                            resultSet.getString("gate_number"),
                            resultSet.getString("airport_id")
                        );
                        return Optional.of(gate);
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
            String query = "DELETE FROM gates WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Gates> findAll() {
        List<Gates> gates = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM gates";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Gates gate = new Gates(
                        resultSet.getInt("id"),
                        resultSet.getString("gate_number"),
                        resultSet.getString("airport_id")
                    );
                    gates.add(gate);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gates;
    }
}

