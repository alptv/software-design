package ru.akirakozov.sd.refactoring.servlet;

import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class GetProductsServletTest extends ServletTest {
    private final GetProductsServlet getProductServlet = new GetProductsServlet(ProductDBEnvironment.DATABASE_URL);

    @Test
    public void getFromEmptyDatabase() throws IOException {
        getProductServlet.doGet(request, response);

        assertThat(htmlResponse.toString()).isEqualTo(
                "<html><body>\n" +
                "</body></html>\n"
        );
        verify(response).setContentType("text/html");
        verify(response).setStatus(HttpServletResponse.SC_OK);
    }


    @Test
    public void getFromNonEmptyDatabase() throws IOException, SQLException {
        productDBEnvironment.addProductInTable("name1", 101);
        productDBEnvironment.addProductInTable("name2", 102);
        productDBEnvironment.addProductInTable("name3", 103);

        getProductServlet.doGet(request, response);

        assertThat(htmlResponse.toString()).isEqualTo(
                "<html><body>\n" +
                        "name1\t101</br>\n" +
                        "name2\t102</br>\n" +
                        "name3\t103</br>\n" +
                "</body></html>\n");

        verify(response).setContentType("text/html");
        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    public void nonExistentProductTable() throws SQLException {
        productDBEnvironment.dropProductTable();
        assertThatThrownBy(() -> getProductServlet.doGet(request, response))
                .isInstanceOf(RuntimeException.class);
    }
}