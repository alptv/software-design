package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.database.DatabaseQueryExecutor;

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

        try (DatabaseQueryExecutor executor = new DatabaseQueryExecutor(dataBaseUrl)) {
            ResultSet resultSet = executor.executeQuery("SELECT * FROM PRODUCT");
            response.getWriter().println("<html><body>");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                response.getWriter().println(name + "\t" + price + "</br>");
            }
            response.getWriter().println("</body></html>");
            resultSet.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
