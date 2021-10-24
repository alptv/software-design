package ru.akirakozov.sd.refactoring.servlet.utils;

import java.sql.SQLException;

@FunctionalInterface
public interface SQLConsumer<T> {
    void accept(T argument) throws SQLException;
}
