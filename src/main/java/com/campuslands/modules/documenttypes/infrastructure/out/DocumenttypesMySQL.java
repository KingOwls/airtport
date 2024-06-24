package com.campuslands.modules.documenttypes.infrastructure.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.campuslands.core.MySQL;
import com.campuslands.modules.documenttypes.domain.models.DocumentType;
import com.campuslands.modules.documenttypes.domain.repository.DocumentTypesRepository;

public class DocumenttypesMySQL extends MySQL implements DocumentTypesRepository {

    public DocumenttypesMySQL() {
        super();
    }

    @Override
    public void save(DocumentType documentType) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO documenttypes (name) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, documentType.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(DocumentType documentType) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE documenttypes SET name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, documentType.getName());
                statement.setInt(2, documentType.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<DocumentType> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM documenttypes WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        DocumentType documentTypes = new DocumentType(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                        );
                        return Optional.of(documentTypes);
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
            String query = "DELETE FROM documenttypes WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DocumentType> findAll() {
        List<DocumentType> documentTypes = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM documenttypes";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    DocumentType documentType = new DocumentType(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                    );
                    documentTypes.add(documentType);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return documentTypes;
    }
}
