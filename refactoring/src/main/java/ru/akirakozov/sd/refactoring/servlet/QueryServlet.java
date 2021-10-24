package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.database.DatabaseQueryExecutor;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;

/**
 * @author akirakozov
 */
public class QueryServlet extends HttpServlet {
    private final String dataBaseUrl;

    public QueryServlet(@Nonnull final String dataBaseUrl) {
        this.dataBaseUrl = dataBaseUrl;
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");

        if ("max".equals(command)) {
            try (DatabaseQueryExecutor executor = new DatabaseQueryExecutor(dataBaseUrl)) {
                ResultSet rs = executor.executeQuery("SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1");
                response.getWriter().println("<html><body>");
                response.getWriter().println("<h1>Product with max price: </h1>");

                while (rs.next()) {
                    String name = rs.getString("name");
                    int price = rs.getInt("price");
                    response.getWriter().println(name + "\t" + price + "</br>");
                }
                rs.close();
                response.getWriter().println("</body></html>");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ("min".equals(command)) {
            try (DatabaseQueryExecutor executor = new DatabaseQueryExecutor(dataBaseUrl)) {

                ResultSet rs = executor.executeQuery("SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1");
                response.getWriter().println("<html><body>");
                response.getWriter().println("<h1>Product with min price: </h1>");

                while (rs.next()) {
                    String name = rs.getString("name");
                    int price = rs.getInt("price");
                    response.getWriter().println(name + "\t" + price + "</br>");
                }
                rs.close();
                response.getWriter().println("</body></html>");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ("sum".equals(command)) {
            try (DatabaseQueryExecutor executor = new DatabaseQueryExecutor(dataBaseUrl)) {
                ResultSet rs = executor.executeQuery("SELECT SUM(price) FROM PRODUCT");
                response.getWriter().println("<html><body>");
                response.getWriter().println("Summary price: ");

                if (rs.next()) {
                    response.getWriter().println(rs.getInt(1));
                }
                rs.close();
                response.getWriter().println("</body></html>");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ("count".equals(command)) {
            try (DatabaseQueryExecutor executor = new DatabaseQueryExecutor(dataBaseUrl)) {
                ResultSet rs = executor.executeQuery("SELECT COUNT(*) FROM PRODUCT");
                response.getWriter().println("<html><body>");
                response.getWriter().println("Number of products: ");

                if (rs.next()) {
                    response.getWriter().println(rs.getInt(1));
                }
                response.getWriter().println("</body></html>");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            response.getWriter().println("Unknown command: " + command);
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
