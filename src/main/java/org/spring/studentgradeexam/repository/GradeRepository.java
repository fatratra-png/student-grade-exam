package org.spring.studentgradeexam.repository;

import org.spring.studentgradeexam.db.DatabaseConnection;
import org.spring.studentgradeexam.model.Grade;
import org.spring.studentgradeexam.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GradeRepository {

    public List<Grade> findByExamId(String examId) {
        String sql = """
                SELECT g.id, g.score, s.id AS student_id, s.first_name, s.last_name
                FROM grades g
                JOIN students s ON s.id = g.student_id
                WHERE g.exam_id = ?
                ORDER BY s.last_name, s.first_name
                """;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, examId);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Grade> grades = new ArrayList<>();
                while (resultSet.next()) {
                    grades.add(mapGradeWithStudent(resultSet));
                }
                return grades;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Grade> findByStudentId(String studentId) {
        String sql = "SELECT id, score FROM grades WHERE student_id = ? ORDER BY id";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, studentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Grade> grades = new ArrayList<>();
                while (resultSet.next()) {
                    grades.add(new Grade(
                            resultSet.getString("id"),
                            resultSet.getBigDecimal("score"),
                            null
                    ));
                }
                return grades;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean save(Grade grade, String studentId, String examId) {
        String sql = "INSERT INTO grades (id, score, student_id, exam_id) VALUES (?, ?, ?, ?) ON CONFLICT (id) DO NOTHING";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, grade.id());
            statement.setBigDecimal(2, grade.score());
            statement.setString(3, studentId);
            statement.setString(4, examId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Grade mapGradeWithStudent(ResultSet resultSet) throws SQLException {
        Student student = new Student(
                resultSet.getString("student_id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                null
        );
        return new Grade(
                resultSet.getString("id"),
                resultSet.getBigDecimal("score"),
                student
        );
    }
}
