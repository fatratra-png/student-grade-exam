package org.spring.studentgradeexam.repository;

import org.spring.studentgradeexam.db.DatabaseConnection;
import org.spring.studentgradeexam.model.Exam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExamRepository {

    public List<Exam> findAll() {
        String sql = "SELECT id, title, exam_date, coefficient FROM exams ORDER BY title";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            List<Exam> exams = new ArrayList<>();
            while (resultSet.next()) {
                exams.add(mapExam(resultSet));
            }
            return exams;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Exam> findById(String id) {
        String sql = "SELECT id, title, exam_date, coefficient FROM exams WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapExam(resultSet));
                }
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Exam mapExam(ResultSet resultSet) throws SQLException {
        return new Exam(
                resultSet.getString("id"),
                resultSet.getString("title"),
                resultSet.getObject("exam_date", Instant.class),
                resultSet.getInt("coefficient"),
                null
        );
    }
}
