package ru.akirakozov.sd.refactoring.database;

import javax.annotation.Nonnull;
import java.sql.SQLException;

public class DatabaseUpdatesExecutor extends DatabaseExecutor {

    public DatabaseUpdatesExecutor(@Nonnull final String dataBaseUrl, @Nonnull final String query) throws SQLException {
        super(dataBaseUrl, query);
    }

    public void execute() throws SQLException {
        getStatement().executeUpdate(getQuery());
    }

}
