package com.mir.passpocket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class Validator {
    private static Connection connection;
    public static boolean isEmpty(String s1) {
        return s1.isEmpty();
    }

    public static boolean isEmpty(String s1, String s2) {
        return s1.isEmpty() && s2.isEmpty();
    }

    public static boolean isEmpty(String s1, String s2, String s3, String s4) {
        return s1.isEmpty() && s2.isEmpty() && s3.isEmpty() && s4.isEmpty();
    }

    public static boolean doEmailExist(String s1) throws SQLException {
        connection = DBConnector.Connect();
        String query = "select * from Users where Email = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, s1);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public static boolean doPasswordExist(String s1, String s2) throws SQLException {
        connection = DBConnector.Connect();
        String query = "select * from Users where Email = ? and Password = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, s1);
        ps.setString(2, s2);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public static boolean doNameExist(String s1) throws SQLException {
        connection = DBConnector.Connect();
        String query = "select * from Users where Name = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, s1);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public static boolean isNameTooLong(String s1) {
        return s1.length() > 15;
    }

    public static boolean isNameTooSmall(String s1) {
        return s1.length() < 3;
    }

    public static boolean isEmailValid(String s1) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
        return Pattern.compile(emailRegex).matcher(s1).matches();
    }

    public static boolean isPasswordValid(String s1) {
        return s1.length() >= 6;
    }

    public static boolean doPasswordMatch(String s1, String s2) {
        return s1.equals(s2);
    }
}
