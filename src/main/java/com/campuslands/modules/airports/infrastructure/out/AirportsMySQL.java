package com.campuslands.modules.airports.infrastructure.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.campuslands.core.MySQL;
import com.campuslands.modules.airports.domain.models.Airport;
import com.campuslands.modules.airports.domain.repository.AirportsRepository;

/**
 * AirportsMySQL
 */
public class AirportsMySQL  extends MySQL implements AirportsRepository{

    public AirportsMySQL() {
        super();
    }

    @Override
    public void save(Airport airports) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO airlines (name) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, airports.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Airport airports) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE airports SET name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, airports.getName());
                statement.setInt(2, airports.getId());
                statement.setInt(3, airports.getIdCity());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Airport> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM airports WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Airport airport = new Airport(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("city")
                        );
                        return Optional.of(airport);
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
            String query = "DELETE FROM airports WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Airport> findAll() {
        List<Airport> airport = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM airports";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Airport airp = new Airport(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("city")
                    );
                    airport.add(airp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airport;
    }

    
}