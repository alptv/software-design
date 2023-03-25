package product.service;

public class ValidationUtils {
    public static void validateRequired(Object value, String message) throws ValidationException {
        if (value == null) {
            throw new ValidationException(message);
        }
    }
    public static void validateStringRequired(String value, String message) throws ValidationException {
        if (value == null || value.isBlank()) {
            throw new ValidationException(message);
        }
    }
}
