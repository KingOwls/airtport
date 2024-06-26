package com.campuslands.modules.revisions.infrastructure.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.campuslands.core.MySQL;
import com.campuslands.modules.revisions.domain.models.Revisions;
import com.campuslands.modules.revisions.domain.repository.RevisionsRepository;

public class RevisionsMySqlRepository extends MySQL implements RevisionsRepository {

    public RevisionsMySqlRepository() {
        super();
    }

    @Override
    public void save(Revisions revisions) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO revisions (revision_date) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, revisions.getId());
                statement.setDate(2, revisions.getRevision_date());
                statement.setInt(3, revisions.getId_plane());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Revisions revisions) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE revisions SET revision_date = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, revisions.getId());
                statement.setDate(2, revisions.getRevision_date());
                statement.setInt(3, revisions.getId_plane());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Revisions> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM revisions WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Revisions revisions = new Revisions(
                                resultSet.getInt("id"),
                                resultSet.getDate("revision_date"),
                                resultSet.getInt("id_plane"));
                        return Optional.of(revisions);
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
            String query = "DELETE FROM revisions WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Revisions> findAll() {
        List<Revisions> revisions = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM revisions";
            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Revisions revision = new Revisions(
                            resultSet.getInt("id"),
                            resultSet.getDate("revision_date"),
                            resultSet.getInt("id_plane"));
                    revisions.add(revision);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revisions;
    }
}
