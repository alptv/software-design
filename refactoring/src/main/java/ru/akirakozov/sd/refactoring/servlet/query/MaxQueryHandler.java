package ru.akirakozov.sd.refactoring.servlet.query;

import ru.akirakozov.sd.refactoring.html.ResponseBuilder;
import ru.akirakozov.sd.refactoring.servlet.utils.ServletQueryExecutor;

import javax.annotation.Nonnull;

public class MaxQueryHandler extends SqlQueryHandler {
    public MaxQueryHandler(@Nonnull final String databaseUrl) {
        super("SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1", databaseUrl);
    }

    @Override
    public void handle(@Nonnull final ResponseBuilder builder) {
        ServletQueryExecutor.executeQuery(getDatabaseUrl(), getQuery(), resultSet -> {
            builder.addH1("Product with max price: ");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                builder.addLineBreak(name + "\t" + price);
            }
        });
        builder.buildHtml();
    }
}
