package com.campuslands.modules.flightfares.infrastructure.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.campuslands.core.MySQL;
import com.campuslands.modules.flightfares.domain.models.Flightfares;
import com.campuslands.modules.flightfares.domain.repository.FlightfaresRepository;

public class FlightFaresMySQL extends MySQL implements FlightfaresRepository {

    public FlightFaresMySQL() {
        super();
    }

    @Override
    public void save(Flightfares flightFares) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO flightfares (price) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, flightFares.getId());
                statement.setString(2, flightFares.getDescription());
                statement.setString(3, flightFares.getDetails());
                statement.setInt(4, flightFares.getValue());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Flightfares flightFares) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE flightfares SET price = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, flightFares.getId());
                statement.setString(2, flightFares.getDescription());
                statement.setString(3, flightFares.getDetails());
                statement.setInt(4, flightFares.getValue());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Flightfares> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM flightfares WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Flightfares flightFares = new Flightfares(
                            resultSet.getInt("id"),
                            resultSet.getString("description"),
                            resultSet.getString("details"),
                            resultSet.getInt("value")
                        );
                        return Optional.of(flightFares);
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
            String query = "DELETE FROM flightfares WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Flightfares> findAll() {
        List<Flightfares> flightFares = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM flightfares";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Flightfares fare = new Flightfares(
                        resultSet.getInt("id"),
                        resultSet.getString("description"),
                        resultSet.getString("details"),
                        resultSet.getInt("value")
                    );
                    flightFares.add(fare);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flightFares;
    }
}
