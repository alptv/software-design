package ru.akirakozov.sd.refactoring.html;


import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseBuilder {
    private final HttpServletResponse response;
    private final StringBuilder content = new StringBuilder();

    public ResponseBuilder(@Nonnull final HttpServletResponse response) {
        this.response = response;
    }

    public void addLine(@Nonnull final String line) {
        content.append(line).append("\n");
    }

    public void addH1(@Nonnull final String content) {
        this.content.append("<h1>").append(content).append("</h1>").append("\n");
    }

    public void addLineBreak(@Nonnull final String content) {
        this.content.append(content).append("</br>").append("\n");
    }

    public void buildHtml() {
        build("<html><body>\n" + content.toString() + "</body></html>\n");
    }

    public void buildText() {
        build(content.toString());
    }

    private void build(@Nonnull String content) {
        try {
            response.getWriter().print(content);
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
