package ru.akirakozov.sd.refactoring.servlet.query;

import ru.akirakozov.sd.refactoring.html.ResponseBuilder;

import javax.annotation.Nonnull;

public interface QueryHandler {
    void handle(@Nonnull final ResponseBuilder builder);
}
