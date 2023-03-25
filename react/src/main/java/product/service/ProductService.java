package product.service;

import org.springframework.stereotype.Service;
import product.repository.ProductRepository;
import product.service.model.Currency;
import product.service.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static product.service.ValidationUtils.validateRequired;
import static product.service.ValidationUtils.validateStringRequired;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Flux<Product> findWithCurrency(Currency currency) {
        return productRepository.findAll()
                .map(product -> {
                    var newAmount = product.getAmount().exchange(currency);
                    return product.toBuilder().amount(newAmount).build();
                });
    }

    public Mono<Product> saveProduct(Product product) {
        try {
            validateRequired(product.getAmount(), "Amount required");
            validateStringRequired(product.getName(), "Empty name");
            validateStringRequired(product.getDescription(), "Empty description");
            return productRepository.findByName(product.getName())
                    .flatMap(u -> Mono.<Product>error(new ValidationException("Product with this name already exists")))
                    .switchIfEmpty(productRepository.insert(product));
        } catch (Exception e) {
            return Mono.error(e);
        }
    }
}
