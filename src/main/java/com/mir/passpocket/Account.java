package com.mir.passpocket;

import com.mir.passpocket.Model.AccountModel;
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

    private static Connection connection = DBConnector.Connect();

    private Account() {}

    private static Account INSTANCE = null;

    public static Account getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Account();
        }
        return INSTANCE;
    }

    public AccountModel getAccount() {
        return account;
    }

    public void setAccount(AccountModel a) {
        account = a;
    }

    public static void addAccount(String name, String email, String url, String password, String category) throws SQLException {
        String encryptedPassword = AES256.encrypt(password);
        String query = "insert into Accounts Values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, url);
        ps.setString(3, email);
        ps.setString(4, encryptedPassword);
        ps.setString(5, new SimpleDateFormat("yyyyMMdd HH:mm:ss aa").format(new Date()));
        ps.setString(6, category);
        ps.setInt(7, User.getInstance().getUserId());
        ps.setString(8, User.getInstance().getUserEmail());
        ps.setByte(9, User.getInstance().getUserAdmin());
        ps.executeUpdate();
    }

    public static void addToFavorites(int accountId, String userEmail) throws SQLException {
        String query = "insert into Favorites values(?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, accountId);
        ps.setString(2, userEmail);
        ps.executeUpdate();
    }

    public static void update(String column, String text) throws SQLException {
        String query = "UPDATE Accounts SET " + column + " = ? " + "WHERE Id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, text);
        ps.setInt(2, Account.getInstance().getAccount().getId());
        ps.executeUpdate();
    }

    public static void deleteAccount() throws SQLException {
        String query = "delete from Favorites where AccountId = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, Account.getInstance().getAccount().getId());
        ps.executeUpdate();
        connection.close();
        connection = DBConnector.Connect();
        query = "delete from Accounts where Id = ?";
        ps = connection.prepareStatement(query);
        ps.setInt(1, Account.getInstance().getAccount().getId());
        ps.executeUpdate();
        connection.close();
    }

    public static ObservableList<AccountModel> search(String search) throws SQLException {
        ObservableList<AccountModel> accountList = FXCollections.observableArrayList();
        String query = "select Id, Name, Email, Password, Url, Category, Modified from Accounts where Name like ? or Url like ? or Email like ? and UserEmail = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, "%" + search + "%");
        ps.setString(2, "%" + search + "%");
        ps.setString(3, "%" + search + "%");
        ps.setString(4, User.getInstance().getUserEmail());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            accountList.add(new AccountModel(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    AES256.decrypt(rs.getString(4)),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7))
            );
        }
        return accountList;
    }

    public static ObservableList<AccountModel> allAccount() throws SQLException {
        ObservableList<AccountModel> accountList = FXCollections.observableArrayList();
        String query = "select Id, Name, Email, Password, Url, Category, Modified from Accounts where UserEmail = ? ORDER BY Name";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, User.getInstance().getUserEmail());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            accountList.add(new AccountModel(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            AES256.decrypt(rs.getString(4)),
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
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, User.getInstance().getUserEmail());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            accountList.add(new AccountModel(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    AES256.decrypt(rs.getString(4)),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7))
            );
        }

        return accountList;

    }
}
