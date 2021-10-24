package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.database.DatabaseQueriesExecutor;
import ru.akirakozov.sd.refactoring.html.ResponseBuilder;
import ru.akirakozov.sd.refactoring.servlet.utils.ServletQueryExecutor;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends HttpServlet {
    private final String dataBaseUrl;

    public GetProductsServlet(@Nonnull final String dataBaseUrl) {
        this.dataBaseUrl = dataBaseUrl;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        ResponseBuilder builder = new ResponseBuilder(response);

        ServletQueryExecutor.executeQuery(dataBaseUrl, "SELECT * FROM PRODUCT", resultSet -> {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                builder.addLineBreak(name + "\t" + price);
            }
        });
        builder.buildHtml();
    }
}
