package ru.akirakozov.sd.refactoring.servlet.query;

import ru.akirakozov.sd.refactoring.html.ResponseBuilder;
import ru.akirakozov.sd.refactoring.servlet.utils.ServletQueryExecutor;

import javax.annotation.Nonnull;

public class SumQueryHandler extends SqlQueryHandler {
    public SumQueryHandler(@Nonnull final String databaseUrl) {
        super("SELECT SUM(price) FROM PRODUCT", databaseUrl);
    }

    @Override
    public void handle(@Nonnull final ResponseBuilder builder) {
        ServletQueryExecutor.executeQuery(getDatabaseUrl(), getQuery(), resultSet -> {
            builder.addLine("Summary price: ");
            if (resultSet.next()) {
                builder.addLine(resultSet.getInt(1) + "");
            }
        });
        builder.buildHtml();
    }
}
