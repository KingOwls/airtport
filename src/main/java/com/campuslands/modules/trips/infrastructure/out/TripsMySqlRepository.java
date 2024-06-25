package com.campuslands.modules.trips.infrastructure.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.campuslands.core.MySQL;
import com.campuslands.modules.trips.domain.models.Trips;
import com.campuslands.modules.trips.domain.repository.TripsRepository;

public class TripsMySqlRepository extends MySQL implements TripsRepository {

    public TripsMySqlRepository() {
        super();
    }

    @Override
    public void save(Trips trips) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO trips (trip_name) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, trips.getId());
                statement.setDate(2, trips.getDate());
                statement.setDouble(3, trips.getPrice());
                statement.setString(4, trips.getDeparture_airport());
                statement.setString(5, trips.getArrival_airport());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Trips trips) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE trips SET trip_name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, trips.getId());
                statement.setDate(2, trips.getDate());
                statement.setDouble(3, trips.getPrice());
                statement.setString(4, trips.getDeparture_airport());
                statement.setString(5, trips.getArrival_airport());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Trips> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM trips WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Trips trips = new Trips(
                            resultSet.getInt("id"),
                            resultSet.getDate("date"),
                            resultSet.getDouble("price"),
                            resultSet.getString("departure_airport"),
                            resultSet.getString("arrival_airport")
                        );
                        return Optional.of(trips);
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
            String query = "DELETE FROM trips WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Trips> findAll() {
        List<Trips> trips = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM trips";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Trips trip = new Trips(
                        resultSet.getInt("id"),
                        resultSet.getDate("date"),
                        resultSet.getDouble("price"),
                        resultSet.getString("departure_airport"),
                        resultSet.getString("arrival_airport")
                    );
                    trips.add(trip);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trips;
    }
}
