package com.campuslands.modules.cities.infrastructure.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.campuslands.core.MySQL;
import com.campuslands.modules.cities.domain.models.Cities;
import com.campuslands.modules.cities.domain.repository.CitiesRepository;

public class CitiesMySQL extends MySQL implements CitiesRepository {

    public CitiesMySQL() {
        super();
    }

    @Override
    public void save(Cities cities) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO cities (name) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, cities.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Cities cities) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE cities SET name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, cities.getName());
                statement.setInt(2, cities.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Cities> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM cities WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Cities cities = new Cities(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("id_country")
                        );
                        return Optional.of(cities);
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
            String query = "DELETE FROM cities WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Cities> findAll() {
        List<Cities> cities = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM cities";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Cities city = new Cities(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("id_country")
                        
                    );
                    cities.add(city);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }
}
