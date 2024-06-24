package com.campuslands.modules.tripcrews.infrastructure.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.campuslands.core.MySQL;
import com.campuslands.modules.tripbookingdetails.domain.models.Tripbookingdetails;
import com.campuslands.modules.tripbookingdetails.domain.repository.TripbookingdetailsRepository;

public class TripBookingDetailsMySQL extends MySQL implements TripbookingdetailsRepository {

    public TripBookingDetailsMySQL() {
        super();
    }

    @Override
    public void save(Tripbookingdetails tripBookingDetails) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO tripbookingdetails (description) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, tripBookingDetails.getIdcustomers());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Tripbookingdetails tripBookingDetails) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE tripbookingdetails SET description = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, tripBookingDetails.getIdcustomers());
                statement.setInt(2, tripBookingDetails.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Tripbookingdetails> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM tripbookingdetails WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Tripbookingdetails tripBookingDetails = new Tripbookingdetails(
                            resultSet.getInt("id"),
                            resultSet.getInt("idtripbooking"),
                            resultSet.getString("idcustomers"),
                            resultSet.getInt("idfares"));
                        return Optional.of(tripBookingDetails);
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
            String query = "DELETE FROM tripbookingdetails WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Tripbookingdetails> findAll() {
        List<Tripbookingdetails> tripBookingDetails = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM tripbookingdetails";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Tripbookingdetails details = new Tripbookingdetails(
                      resultSet.getInt("id"),
                            resultSet.getInt("idtripbooking"),
                            resultSet.getString("idcustomers"),
                            resultSet.getInt("idfares")
                    );
                    tripBookingDetails.add(details);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tripBookingDetails;
    }
}
