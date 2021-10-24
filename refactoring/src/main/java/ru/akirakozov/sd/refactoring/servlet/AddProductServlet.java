package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.database.DatabaseQueryExecutor;
import ru.akirakozov.sd.refactoring.html.ResponseBuilder;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author akirakozov
 */
public class AddProductServlet extends HttpServlet {
    private final String dataBaseUrl;

    public AddProductServlet(@Nonnull String dataBaseUrl) {
        this.dataBaseUrl = dataBaseUrl;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        long price = Long.parseLong(request.getParameter("price"));

        try (DatabaseQueryExecutor executor = new DatabaseQueryExecutor(dataBaseUrl)) {
            String update = "INSERT INTO PRODUCT " +
                    "(NAME, PRICE) VALUES (\"" + name + "\"," + price + ")";
            executor.executeUpdate(update);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResponseBuilder builder = new ResponseBuilder(response);
        builder.addLine("OK");
        builder.buildText();
    }
}
