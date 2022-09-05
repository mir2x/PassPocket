package com.mir.passpocket;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Account {

    private AccountModel account;

    private Account() {}

    private static Account INSTANCE = null;

    public static Account getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Account();
        }
        return INSTANCE;
    }

    public static void addToFavorites(int accountId, String userEmail) throws SQLException {
        connection = DBConnector.Connect();
        String query = "insert into Favorites values(?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, accountId);
        ps.setString(2, userEmail);
        ps.executeUpdate();
    }



    public AccountModel getAccount() {
        return account;
    }

    public void setAccount(AccountModel a) {
        account = a;
    }

    private static Connection connection;
    public static void addAccount(String name, String email, String url, String password, String category) throws SQLException {

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
        String query = "select Id, Name, Email, Password, Url, Category, Modified from Accounts where UserEmail = ? ORDER BY Name";
        connection = DBConnector.Connect();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, User.getInstance().getUserEmail());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            accountList.add(new AccountModel(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7))
                            );
        }

        return accountList;
    }
    public static ObservableList<AccountModel> favoriteAccount() throws SQLException {
        ObservableList<AccountModel> accountList = FXCollections.observableArrayList();
        String query = "select a.Id, a.Name, a.Email, a.Password, a.Url, a.Category, a.Modified\n" +
                "from Accounts a\n" +
                "inner join\n" +
                "Favorites f\n" +
                "on a.Id = f.AccountId\n" +
                "where a.UserEmail = ?";
        connection = DBConnector.Connect();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, User.getInstance().getUserEmail());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            accountList.add(new AccountModel(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7))
            );
        }

        return accountList;

    }

    public static void update(String column, TextField input) throws SQLException {
        connection = DBConnector.Connect();
        String query = "UPDATE Accounts SET " + column + " = ? " + "WHERE Id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, input.getText());
        ps.setInt(2, Account.getInstance().getAccount().getId());
        ps.executeUpdate();
    }

    public static void update(String column, PasswordField input) throws SQLException {
        connection = DBConnector.Connect();
        String query = "UPDATE Accounts SET " + column + " = ? " + "WHERE Id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, input.getText());
        ps.setInt(2, Account.getInstance().getAccount().getId());
        ps.executeUpdate();
    }

    public static void update(String column, ComboBox<String> input) throws SQLException {
        connection = DBConnector.Connect();
        String query = "UPDATE Accounts SET " + column + " = ? " + "WHERE Id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, input.getValue());
        ps.setInt(2, Account.getInstance().getAccount().getId());
        ps.executeUpdate();
    }


//    public static AccountModel firstAccount() throws SQLException {
//        AccountModel firstAccount = null;
//        String query = "select DISTINCT Name, Email, Password, Url, Category, Modified from Accounts where UserEmail = ? ORDER BY Name";
//        connection = DBConnector.Connect();
//        PreparedStatement ps = connection.prepareStatement(query);
//        ps.setString(1, User.getInstance().getUserEmail());
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()) {
//                    firstAccount = new AccountModel(rs.getString(1),
//                    rs.getString(2),
//                    rs.getString(3),
//                    rs.getString(4),
//                    rs.getString(5),
//                    rs.getString(6));
//        }
//       return firstAccount;
//    }
}
