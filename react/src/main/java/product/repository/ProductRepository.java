package product.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import product.service.model.Product;
import reactor.core.publisher.Mono;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
    Mono<Product> findByName(String name);
}
