package com.mir.passpocket;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Account {
    private static Connection connection;
    public static void addAccount(String name, String email, String url, String password, String category) throws SQLException {
        System.out.println(category);
        System.out.println(User.getInstance().getUserName());

        connection = DBConnector.Connect();
        String query = "insert into Accounts Values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, url);
        ps.setString(3, email);
        ps.setString(4, password);
        ps.setString(5, new SimpleDateFormat("yyyyMMdd HH:mm:ss aa").format(new Date()));
        ps.setString(6, category);
        ps.setInt(7, User.getInstance().getUserId());
        ps.setString(8, User.getInstance().getUserEmail());
        ps.setByte(9, User.getInstance().getUserAdmin());
        ps.executeUpdate();
    }

    public static ObservableList<AccountModel> allAccount() throws SQLException {

        ObservableList<AccountModel> accountList = FXCollections.observableArrayList();
        String query = "select Name, Email, Password, Url, Category, Modified from Accounts";
        connection = DBConnector.Connect();
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            accountList.add(new AccountModel(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6))
                            );
        }

        return accountList;
    }
}
