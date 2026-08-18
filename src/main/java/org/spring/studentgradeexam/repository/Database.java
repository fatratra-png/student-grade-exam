package org.spring.studentgradeexam.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Database {

    private static final String URL = "jdbc:postgresql://localhost:5432/student_grade_exam";
    private static final String USER = "postgres";
    private static final String PASSWORD = "";

    private Database() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
