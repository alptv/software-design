package ru.akirakozov.sd.refactoring.servlet.utils;

import ru.akirakozov.sd.refactoring.database.DatabaseQueriesExecutor;

import javax.annotation.Nonnull;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServletQueryExecutor {

    public static void executeQuery(@Nonnull final String url, @Nonnull final String query, @Nonnull final SQLConsumer<ResultSet> consumer) {
        try (DatabaseQueriesExecutor databaseQueryExecutor = new DatabaseQueriesExecutor(url)) {
            ResultSet resultSet = databaseQueryExecutor.executeQuery(query);
            consumer.accept(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void executeUpdate(@Nonnull final String url, @Nonnull final String update) {
        try (DatabaseQueriesExecutor databaseQueryExecutor = new DatabaseQueriesExecutor(url)) {
            databaseQueryExecutor.executeUpdate(update);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
