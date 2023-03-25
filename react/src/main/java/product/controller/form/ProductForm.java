package product.controller.form;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductForm {
    private String name;
    private String description;
    private BigDecimal amountUSD;
}
