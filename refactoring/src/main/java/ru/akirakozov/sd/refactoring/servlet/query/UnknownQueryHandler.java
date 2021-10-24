package ru.akirakozov.sd.refactoring.servlet.query;

import ru.akirakozov.sd.refactoring.html.ResponseBuilder;

import javax.annotation.Nonnull;

public class UnknownQueryHandler implements QueryHandler {
    private final String query;

    public UnknownQueryHandler(@Nonnull final String query) {
        this.query = query;
    }

    @Override
    public void handle(@Nonnull final ResponseBuilder builder) {
        builder.addLine("Unknown command: " + query);
        builder.buildText();
    }
}
