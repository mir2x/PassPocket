package com.mir.passpocket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static Connection connection;

    public static Connection Connect() {
        try {
            String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=PassPocket;user=sa;password=p@ssword89";
            connection = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
