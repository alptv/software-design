package ru.akirakozov.sd.refactoring.servlet.query;

import javax.annotation.Nonnull;

public class QueryHandlerFactory {

    public QueryHandler createQueryHandlerFromName(@Nonnull final String name, @Nonnull final String databaseUrl) {
        switch (name) {
            case "max":
                return new MaxQueryHandler(databaseUrl);
            case "min":
                return new MinQueryHandler(databaseUrl);
            case "sum":
                return new SumQueryHandler(databaseUrl);
            case "count":
                return new CountQueryHandler(databaseUrl);
            default:
                return new UnknownQueryHandler(name);
        }
    }

}
