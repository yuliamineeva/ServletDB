package ru.innopolis.stc9.servlet1.db.connectionManager;

import java.sql.Connection;

public interface IConnectionManager {
    public Connection getConnection();
}
