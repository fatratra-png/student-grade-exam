package org.spring.studentgradeexam.repository;

import org.spring.studentgradeexam.db.DatabaseConnection;
import org.spring.studentgradeexam.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepository {

    public List<Student> findAll() {
        String sql = "SELECT id, first_name, last_name FROM students ORDER BY last_name, first_name";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                students.add(mapStudent(resultSet));
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Student> findById(String id) {
        String sql = "SELECT id, first_name, last_name FROM students WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapStudent(resultSet));
                }
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean save(Student student) {
        String sql = "INSERT INTO students (id, first_name, last_name) VALUES (?, ?, ?) ON CONFLICT (id) DO NOTHING";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, student.id());
            statement.setString(2, student.firstName());
            statement.setString(3, student.lastName());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Student mapStudent(ResultSet resultSet) throws SQLException {
        return new Student(
                resultSet.getString("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                null
        );
    }
}
