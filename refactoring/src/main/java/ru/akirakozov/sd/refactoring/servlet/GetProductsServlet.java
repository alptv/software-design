package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.database.DatabaseQueryExecutor;
import ru.akirakozov.sd.refactoring.html.ResponseBuilder;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseBuilder builder = new ResponseBuilder(response);

        try (DatabaseQueryExecutor executor = new DatabaseQueryExecutor(dataBaseUrl)) {
            ResultSet resultSet = executor.executeQuery("SELECT * FROM PRODUCT");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                builder.addLineBreak(name + "\t" + price);
            }
            resultSet.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        builder.buildHtml();
    }
}
