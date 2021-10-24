package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.database.DatabaseQueryExecutor;
import ru.akirakozov.sd.refactoring.html.ResponseBuilder;
import ru.akirakozov.sd.refactoring.html.components.Body;
import ru.akirakozov.sd.refactoring.html.components.TagHtmlComponent;

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
        ResponseBuilder builder = new ResponseBuilder(response);
        if ("max".equals(command)) {
            try (DatabaseQueryExecutor executor = new DatabaseQueryExecutor(dataBaseUrl)) {
                ResultSet rs = executor.executeQuery("SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1");
                builder.addH1("Product with max price: ");
                while (rs.next()) {
                    String name = rs.getString("name");
                    int price = rs.getInt("price");
                    builder.addLineBreak(name + "\t" + price);
                }
                rs.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ("min".equals(command)) {
            try (DatabaseQueryExecutor executor = new DatabaseQueryExecutor(dataBaseUrl)) {
                ResultSet rs = executor.executeQuery("SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1");
                builder.addH1("Product with min price: ");
                while (rs.next()) {
                    String name = rs.getString("name");
                    int price = rs.getInt("price");
                    builder.addLineBreak(name + "\t" + price);
                }
                rs.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ("sum".equals(command)) {
            try (DatabaseQueryExecutor executor = new DatabaseQueryExecutor(dataBaseUrl)) {
                ResultSet rs = executor.executeQuery("SELECT SUM(price) FROM PRODUCT");
                builder.addLine("Summary price: ");

                if (rs.next()) {
                    builder.addLine(rs.getInt(1) + "");
                }
                rs.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ("count".equals(command)) {
            try (DatabaseQueryExecutor executor = new DatabaseQueryExecutor(dataBaseUrl)) {
                ResultSet rs = executor.executeQuery("SELECT COUNT(*) FROM PRODUCT");
                builder.addLine("Number of products: ");

                if (rs.next()) {
                    builder.addLine(rs.getInt(1) + "");
                }
                rs.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            builder.addLine("Unknown command: " + command);
            builder.buildText();
            return;
        }
        builder.buildHtml();

    }

}
