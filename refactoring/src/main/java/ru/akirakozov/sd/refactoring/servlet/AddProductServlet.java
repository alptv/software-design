package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.html.ResponseBuilder;
import ru.akirakozov.sd.refactoring.servlet.utils.ServletQueryExecutor;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author akirakozov
 */
public class AddProductServlet extends HttpServlet {
    private final String dataBaseUrl;

    public AddProductServlet(@Nonnull String dataBaseUrl) {
        this.dataBaseUrl = dataBaseUrl;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        long price = Long.parseLong(request.getParameter("price"));
        String update = "INSERT INTO PRODUCT " +
                "(NAME, PRICE) VALUES (\"" + name + "\"," + price + ")";

        ServletQueryExecutor.executeUpdate(dataBaseUrl, update);

        ResponseBuilder builder = new ResponseBuilder(response);
        builder.addLine("OK");
        builder.buildText();
    }
}
