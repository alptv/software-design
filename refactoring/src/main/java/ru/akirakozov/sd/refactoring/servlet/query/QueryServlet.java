package ru.akirakozov.sd.refactoring.servlet.query;

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
    private final QueryHandlerFactory queryHandlerFactory;

    public QueryServlet(@Nonnull final String dataBaseUrl) {
        this.dataBaseUrl = dataBaseUrl;
        this.queryHandlerFactory = new QueryHandlerFactory();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String command = request.getParameter("command");
        QueryHandler handler = queryHandlerFactory.createQueryHandlerFromName(command, dataBaseUrl);
        ResponseBuilder builder = new ResponseBuilder(response);
        handler.handle(builder);
    }

}
