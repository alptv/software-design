package product.service.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder(toBuilder = true)
public class Product {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String description;
    private Amount amount;
}

