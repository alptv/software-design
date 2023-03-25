package product.service.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public class User {
    @Id
    private String id;

    @Indexed(unique = true)
    private String login;

    private String password;

    private Currency currency;
}
