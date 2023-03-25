package product.controller.form;

import lombok.Getter;
import lombok.Setter;
import product.service.model.Currency;

@Getter
@Setter
public class RegisterForm {
    private String login;
    private String password;
    private Currency currency;
}
