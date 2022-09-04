package com.mir.passpocket;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Category {

    private static Connection connection;

    public static ObservableList<String> allCategories() throws SQLException {
        ObservableList<String> categoryList = FXCollections.observableArrayList();
        String query = "select Name from Categories";
        connection = DBConnector.Connect();
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            categoryList.add(rs.getString(1));
        }

        return categoryList;
    }
}
