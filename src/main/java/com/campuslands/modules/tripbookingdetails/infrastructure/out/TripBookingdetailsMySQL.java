
package com.campuslands.modules.tripbookingdetails.infrastructure.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.campuslands.core.MySQL;
import com.campuslands.modules.tripbooking.domain.models.TripBooking;
import com.campuslands.modules.tripbooking.domain.repository.TripBookingRepository;

public class TripBookingdetailsMySQL extends MySQL implements TripBookingRepository {

    public TripBookingdetailsMySQL() {
        super();
    }

    @Override
    public void save(TripBooking tripBooking) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO tripbooking (booking_date) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setDate(1, tripBooking.getDate());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(TripBooking tripBooking) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE tripbooking SET booking_date = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setDate(1, tripBooking.getDate());
                statement.setInt(2, tripBooking.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<TripBooking> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM tripbooking WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        TripBooking tripBooking = new TripBooking(
                            resultSet.getInt("id"),
                            resultSet.getDate("date"),
                            resultSet.getInt("idtrips")


                        );
                        return Optional.of(tripBooking);
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
            String query = "DELETE FROM tripbooking WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TripBooking> findAll() {
        List<TripBooking> tripBookings = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM tripbooking";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    TripBooking booking = new TripBooking(
                        resultSet.getInt("id"),
                            resultSet.getDate("date"),
                            resultSet.getInt("idtrips")
                    );
                    tripBookings.add(booking);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tripBookings;
    }
}
