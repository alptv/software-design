package product.service.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Map;

import static java.math.BigDecimal.ONE;
import static java.math.RoundingMode.HALF_UP;
import static product.service.model.Currency.*;

@Document
@Data
@Builder(toBuilder = true)
public class Amount {
    private static final Map<Currency, BigDecimal> conversions = Map.of(
            RUB, BigDecimal.valueOf(77.28),
            USD, ONE,
            EUR, BigDecimal.valueOf(0.93)
    );

    private final BigDecimal amount;
    private final Currency currency;

    public Amount exchange(Currency currency) {
        var newAmount = getAmount()
                .divide(conversions.get(getCurrency()), HALF_UP)
                .multiply(conversions.get(currency));
        return new Amount(newAmount, currency);
    }
}
