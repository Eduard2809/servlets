package com.test.repository;
import com.test.model.Student;

import java.sql.*;
import java.util.ArrayList;


public class StudentRepositoryJdbc implements StudentRepository {

    private static final String DB_URL = "jdbc:mysql://localhost/test";
    private static final String USER = "root";
    private static final String PASS = null;

    public StudentRepositoryJdbc() {
        try {
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Student getByUsername(String username)  {
        Student student = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps1 = conn.prepareStatement("SELECT username FROM student WHERE " +
                     "username = ?")) {
            ps1.setString(1, username);
            ResultSet rs = ps1.executeQuery();
            while (rs.next()) {
                student = new Student();
                student.setUsername(rs.getString("username"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public void save(String name, int age, String username, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement("INSERT INTO student " +
                     "(name,age,username,password)VALUES (?,?,?,?)")) {
                ps.setString(1, name);
                ps.setInt(2, age);
                ps.setString(3, username);
                ps.setString(4, password);
                ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Student login(String username, String password) {
        Student student = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement("SELECT username,password from student " +
                     "WHERE username = ? AND password = ?")) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                student = new Student();
                student.setUsername(rs.getString("username"));
                student.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public void delete(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement("DELETE from student where id = ?")) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void select(ArrayList<Student> students) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * from student")) {

            while (rs.next()) {
                students.add(new Student(rs.getInt("id"),
                        rs.getString("name"), rs.getInt("age")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void search(Student student, int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement("SELECT * from student where id = ?")
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeAge(int age, int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement("UPDATE student SET age = ? where id = ?")
        ) {
            ps.setInt(1, age);
            ps.setInt(2, id);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

