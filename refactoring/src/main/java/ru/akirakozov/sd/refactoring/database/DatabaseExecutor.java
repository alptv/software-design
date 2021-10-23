package ru.akirakozov.sd.refactoring.database;

import javax.annotation.Nonnull;
import java.sql.*;

public abstract class DatabaseExecutor implements AutoCloseable {
    private final Statement statement;
    private final Connection connection;
    private final String query;

    public DatabaseExecutor(@Nonnull final String dataBaseUrl, @Nonnull final String query) throws SQLException {
        this.connection = DriverManager.getConnection(dataBaseUrl);
        this.statement = connection.createStatement();
        this.query = query;
    }

    protected Statement getStatement() {
        return statement;
    }

    protected Connection getConnection() {
        return connection;
    }

    protected String getQuery() {
        return query;
    }

    public abstract void execute() throws SQLException;

    public void close() throws SQLException {
        statement.close();
        connection.close();
    }
}
