package ru.akirakozov.sd.refactoring.servlet.query;

import javax.annotation.Nonnull;

public abstract class SqlQueryHandler implements QueryHandler {
    private final String query;
    private final String databaseUrl;


    public SqlQueryHandler(@Nonnull final String query, @Nonnull final String databaseUrl) {
        this.query = query;
        this.databaseUrl = databaseUrl;
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public String getQuery() {
        return query;
    }
}
