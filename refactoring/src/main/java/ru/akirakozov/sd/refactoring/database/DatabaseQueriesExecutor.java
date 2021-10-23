package ru.akirakozov.sd.refactoring.database;

import javax.annotation.Nonnull;
import java.sql.*;

public class DatabaseQueriesExecutor extends DatabaseExecutor {
    private ResultSet resultSet;

    public DatabaseQueriesExecutor(@Nonnull final String dataBaseUrl, @Nonnull final String query) throws SQLException {
        super(dataBaseUrl, query);
    }

    public void execute() throws SQLException {
        resultSet = getStatement().executeQuery(getQuery());
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    @Override
    public void close() throws SQLException {
        super.close();
        resultSet.close();
    }
}
