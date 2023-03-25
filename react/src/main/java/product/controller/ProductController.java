package product.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.result.view.Rendering;
import org.springframework.web.server.WebSession;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;
import product.controller.form.LoginForm;
import product.controller.form.ProductForm;
import product.controller.form.RegisterForm;
import product.service.AuthService;
import product.service.ProductService;
import product.service.UserService;
import product.service.model.Amount;
import product.service.model.Currency;
import product.service.model.Product;
import product.service.model.User;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.result.view.Rendering.redirectTo;
import static org.springframework.web.reactive.result.view.Rendering.view;
import static product.controller.ErrorUtils.addError;
import static product.controller.ErrorUtils.withErrors;
import static product.service.model.Currency.USD;
import static reactor.core.publisher.Mono.just;

@Controller
public class ProductController implements ErrorController {
    private final AuthService authService;
    private final UserService userService;
    private final ProductService productService;

    public ProductController(final AuthService authService, final UserService userService, final ProductService productService) {
        this.authService = authService;
        this.userService = userService;
        this.productService = productService;
    }


    @GetMapping("/index")
    public Mono<Rendering> index(WebSession webSession) {
        if (!authService.isAuthenticated(webSession)) {
            return just(redirectTo("/login").build());
        }
        return just(withErrors(view("index"), webSession)
                .modelAttribute("user", authService.getUser(webSession))
                .build()
        );
    }

    @GetMapping("/product")
    public Mono<Rendering> addProductForm(WebSession webSession) {
        if (!authService.isAuthenticated(webSession)) {
            return just(redirectTo("/login").build());
        }
        return just(withErrors(view("product"), webSession).build());
    }


    @PostMapping("/product")
    public Mono<Rendering> addProduct(ProductForm productForm, WebSession webSession) {
        if (!authService.isAuthenticated(webSession)) {
            return just(redirectTo("/login").build());
        }
        var amount = Amount.builder()
                .amount(productForm.getAmountUSD())
                .currency(USD)
                .build();
        var product = Product
                .builder()
                .name(productForm.getName())
                .description(productForm.getDescription())
                .amount(amount)
                .build();
        return productService.saveProduct(product)
                .flatMap(productSaved -> just(redirectTo("/index").build()))
                .onErrorResume(e -> {
                    addError(webSession, e);
                    return just(redirectTo("/product").build());
                });
    }


    @GetMapping("/products")
    public Mono<Rendering> products(WebSession webSession) {
        if (!authService.isAuthenticated(webSession)) {
            return just(redirectTo("/login").build());
        }
        var user = authService.getUser(webSession);
        var products = productService.findWithCurrency(user.getCurrency());
        var productsTemplateData = new ReactiveDataDriverContextVariable(products);
        return just(withErrors(view("products"), webSession)
                .modelAttribute("products", productsTemplateData)
                .modelAttribute("currency")
                .build()
        );
    }


    @GetMapping("/register")
    public Rendering register(WebSession webSession) {
        return authGet(webSession)
                .modelAttribute("type", "register")
                .modelAttribute("currencies",Currency.values())
                .build();
    }

    @PostMapping("/register")
    public Mono<Rendering> register(RegisterForm form, WebSession webSession) {
        var newUser = User
                .builder()
                .password(form.getPassword())
                .login(form.getLogin())
                .currency(form.getCurrency())
                .build();
        var user = userService.createUser(newUser);
        return auth("/register", webSession, user);
    }

    @GetMapping("/login")
    public Rendering login(WebSession webSession) {
        return authGet(webSession).modelAttribute("type", "login").build();
    }

    @PostMapping("/login")
    public Mono<Rendering> login(LoginForm form, WebSession webSession) {
        var user = userService.findUserWithMatchingCredentials(form.getLogin(), form.getPassword());
        return auth("/login", webSession, user);
    }

    @GetMapping("/logout")
    public Mono<Rendering> logout(WebSession webSession) {
        authService.logout(webSession);
        return just(redirectTo("/login").build());
    }

    private Mono<Rendering> auth(String template, WebSession webSession, Mono<User> monoUser) {
        return monoUser.flatMap(user -> {
                    authService.login(user, webSession);
                    return just(redirectTo("/index").build());
                })
                .onErrorResume(e -> {
                    addError(webSession, e);
                    return just(redirectTo(template).build());
                });
    }

    private Rendering.Builder<?> authGet(WebSession webSession) {
        if (authService.isAuthenticated(webSession)) {
            return redirectTo("/index");
        }
        return withErrors(view("auth"), webSession);
    }
}
