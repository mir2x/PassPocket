package com.mir.passpocket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private static Connection connection;


    private int userId = 0;
    private String userName = null;
    private String userEmail = null;
    private boolean isAdmin = false;
    private byte admin = 0;


    private User() {}

    private final static User INSTANCE = new User();

    public static User getInstance() {
        return INSTANCE;
    }

    public static void addUser(String name, String email, String password, int i) {
        connection = DBConnector.Connect();
        String insertQuery = "INSERT INTO Users VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(insertQuery);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setInt(4, i);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getUserId() {
        return userId;
    }
    public String getUserName() {
        return userName;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public byte getUserAdmin() {
        return admin;
    }
    public boolean getAdminStatus() {
        return isAdmin;
    }

    public void setUser(String s1) throws SQLException {

        connection = DBConnector.Connect();
        String query = "select * from Users where Email = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, s1);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            userId = rs.getInt(1);
            userName = rs.getString(2);
            userEmail = rs.getString(3);
            admin = rs.getByte(5);
        }
        isAdmin = admin == 1;
    }

    public void logOut() {
        userId = 0;
        userName = null;
        userEmail = null;
        isAdmin = false;
    }
}
