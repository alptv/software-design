package product.controller;

import org.springframework.web.reactive.result.view.Rendering.Builder;
import org.springframework.web.server.WebSession;
import product.service.ValidationException;

public class ErrorUtils {
    public static final String ERROR_KEY = "error";

    public static void addError(WebSession webSession, Throwable error) {
        var message = (error instanceof ValidationException) ? error.getMessage() : "Something went wrong...";
        webSession.getAttributes().put(ERROR_KEY, message);
    }

    public static Builder<?> withErrors(Builder<?> builder, WebSession webSession) {
        var error = getError(webSession);
        if (error != null) {
            return builder.modelAttribute(ERROR_KEY, error);
        }
        return builder;
    }

    public static String getError(WebSession webSession) {
        var error = webSession.getAttributes().get(ERROR_KEY);
        webSession.getAttributes().remove(ERROR_KEY);
        return (String) error;
    }
}
