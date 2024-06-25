package com.campuslands.modules.flightconnections.infrastructure.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.campuslands.core.MySQL;
import com.campuslands.modules.flightconnections.domain.models.FlightConnection;
import com.campuslands.modules.flightconnections.domain.repository.FlightConnectionsRepository;

public class FlightConnectionsMySqlRespository extends MySQL implements FlightConnectionsRepository {

    public FlightConnectionsMySqlRespository() {
        super();
    }

    @Override
    public void save(FlightConnection flightConnections) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO flight_connections (departure, arrival) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, flightConnections.getId());
                statement.setString(2, flightConnections.getConnection_number());
                statement.setInt(3, flightConnections.getId_trip());
                statement.setInt(4, flightConnections.getId_plane());
                statement.setString(5, flightConnections.getId_airport());
                statement.setString(6, flightConnections.getType_flight());
                statement.setString(7, flightConnections.getLast_Scale());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(FlightConnection flightConnections) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE flight_connections SET departure = ?, arrival = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, flightConnections.getId());
                statement.setString(2, flightConnections.getConnection_number());
                statement.setInt(3, flightConnections.getId_trip());
                statement.setInt(4, flightConnections.getId_plane());
                statement.setString(5, flightConnections.getId_airport());
                statement.setString(6, flightConnections.getType_flight());
                statement.setString(7, flightConnections.getLast_Scale());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<FlightConnection> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM flight_connections WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        FlightConnection flightConnections = new FlightConnection(
                                resultSet.getInt("id"),
                                resultSet.getString("connection_number"),
                                resultSet.getInt("id_trip"),
                                resultSet.getInt("id_plane"),
                                resultSet.getString("id_airport"),
                                resultSet.getString("type_flight"),
                                resultSet.getString("Last_Scale"));
                        return Optional.of(flightConnections);
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
            String query = "DELETE FROM flight_connections WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<FlightConnection> findAll() {
        List<FlightConnection> flightConnections = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM flight_connections";
            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    FlightConnection flightConnection = new FlightConnection(
                            resultSet.getInt("id"),
                            resultSet.getString("connection_number"),
                            resultSet.getInt("id_trip"),
                            resultSet.getInt("id_plane"),
                            resultSet.getString("id_airport"),
                            resultSet.getString("type_flight"),
                            resultSet.getString("Last_Scale")

                    );
                    flightConnections.add(flightConnection);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flightConnections;
    }
}
