package com.campuslands.modules.planes.infrastructure.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.campuslands.core.MySQL;
import com.campuslands.modules.planes.domain.models.Planes;
import com.campuslands.modules.planes.domain.repository.PlanesRepository;

public class PlanesMySqlRepository extends MySQL implements PlanesRepository {

    public PlanesMySqlRepository() {
        super();
    }

    @Override
    public void save(Planes planes) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO planes (name) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, planes.getPlates());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Planes planes) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE planes SET name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, planes.getPlates());
                statement.setInt(2, planes.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Planes> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM planes WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Planes planes = new Planes(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("plates"),
                            resultSet.getDate("capacity"),
                            resultSet.getInt("fabrication_date"),
                            resultSet.getInt("id_status")
                        );
                        return Optional.of(planes);
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
            String query = "DELETE FROM planes WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Planes> findAll() {
        List<Planes> planes = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM planes";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Planes plane = new Planes(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("plates"),
                        resultSet.getDate("capacity"),
                        resultSet.getInt("fabrication_date"),
                        resultSet.getInt("id_status")
                    );
                    planes.add(plane);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planes;
    }
}
