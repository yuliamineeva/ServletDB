package ru.innopolis.stc9.servlet1.db.connectionManager;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс с настройками соединения с базой данных
 */
public class ConnectionManagerJDBC implements IConnectionManager {
    private static IConnectionManager connectionManager;
    private final static Logger logger = Logger.getLogger(ConnectionManagerJDBC.class);

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
            logger.error("ClassNotFoundException", e);
        } catch (SQLException e) {
            logger.error("database connection error", e);
        }
        return connection;
    }
}
