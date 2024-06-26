package com.campuslands.modules.planes.infrastructure.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;

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
            String query = "INSERT INTO planes (id, plateNumber, capacity, fabrication_date, id_status, id_model) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, planes.getId());
                statement.setString(2, planes.getPlateNumber());
                statement.setInt(3, planes.getCapacity());
                statement.setDate(4, new java.sql.Date(planes.getFabrication_date().getTime()));
                statement.setInt(5, planes.getId_status());
                statement.setInt(6, planes.getId_model());
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Avión agregado exitosamente.", "INSERT", 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "ERROR", 0);
        }
    }

    @Override
    public void update(Planes planes) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE planes SET " +
                    "    plateNumber = ?," +
                    "    capacity = ?," +
                    "    fabrication_date = ?," +
                    "    id_status = ?, " +
                    "    id_model = ? " +
                    "WHERE " +
                    "    id = ?;";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, planes.getPlateNumber());
                statement.setInt(2, planes.getCapacity());
                statement.setDate(3, new java.sql.Date(planes.getFabrication_date().getTime()));
                statement.setInt(4, planes.getId_status());
                statement.setInt(5, planes.getId_model());
                statement.setInt(6, planes.getId());
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Avión Actualizado exitosamente.", "UPDATE", 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "ERROR", 0);
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
                                resultSet.getInt("id_status"));
                        return Optional.of(planes);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "ERROR", 0);
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
                JOptionPane.showMessageDialog(null, "Avión Borrado exitosamente.", "DELETE", 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "ERROR", 0);
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
                            resultSet.getString("plateNumber"),
                            resultSet.getInt("capacity"),
                            resultSet.getDate("fabrication_date"),
                            resultSet.getInt("id_status"),
                            resultSet.getInt("id_model"));
                    planes.add(plane);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "ERROR", 0);

        }
        return planes;
    }
}
