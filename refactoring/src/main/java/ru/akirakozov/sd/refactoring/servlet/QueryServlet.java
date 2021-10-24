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
public class QueryServlet extends HttpServlet {
    private final String dataBaseUrl;
    public QueryServlet(@Nonnull final String dataBaseUrl) {
        this.dataBaseUrl = dataBaseUrl;
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String command = request.getParameter("command");
        ResponseBuilder builder = new ResponseBuilder(response);
        if ("max".equals(command)) {
            String query = "SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1";
            ServletQueryExecutor.executeQuery(dataBaseUrl, query, resultSet -> {
                builder.addH1("Product with max price: ");
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int price = resultSet.getInt("price");
                    builder.addLineBreak(name + "\t" + price);
                }
            });
        } else if ("min".equals(command)) {
            String query = "SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1";
            ServletQueryExecutor.executeQuery(dataBaseUrl, query, resultSet -> {
                builder.addH1("Product with min price: ");
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int price = resultSet.getInt("price");
                    builder.addLineBreak(name + "\t" + price);
                }
            });
        } else if ("sum".equals(command)) {
            String query = "SELECT SUM(price) FROM PRODUCT";
            ServletQueryExecutor.executeQuery(dataBaseUrl, query, resultSet -> {
                builder.addLine("Summary price: ");
                if (resultSet.next()) {
                    builder.addLine(resultSet.getInt(1) + "");
                }
            });
        } else if ("count".equals(command)) {
            String query = "SELECT COUNT(*) FROM PRODUCT";
            ServletQueryExecutor.executeQuery(dataBaseUrl, query, resultSet -> {
                builder.addLine("Number of products: ");

                if (resultSet.next()) {
                    builder.addLine(resultSet.getInt(1) + "");
                }
            });
        } else {
            builder.addLine("Unknown command: " + command);
            builder.buildText();
            return;
        }
        builder.buildHtml();
    }

}
