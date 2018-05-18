package ru.innopolis.stc9.servlet1.db.connectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerJDBC implements IConnectionManager {
    private static IConnectionManager connectionManager;

    private ConnectionManagerJDBC() {
    }

    public static IConnectionManager getInstance() {
        if (connectionManager == null) {
            connectionManager = new ConnectionManagerJDBC();
        }
        return connectionManager;
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/EducationalPerformance",
                    "postgres",
                    "root");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
