package product.service;

import org.springframework.stereotype.Service;
import product.repository.UserRepository;
import product.service.model.User;
import reactor.core.publisher.Mono;

import static product.service.ValidationUtils.validateRequired;
import static product.service.ValidationUtils.validateStringRequired;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<User> createUser(User user) {
        try {
            validateStringRequired(user.getLogin(), "Empty login");
            validateStringRequired(user.getPassword(), "Empty password");
            validateRequired(user.getCurrency(), "Empty currency");
            return userRepository.findByLogin(user.getLogin())
                    .flatMap(u -> Mono.<User>error(new ValidationException("User with this login already exists")))
                    .switchIfEmpty(userRepository.insert(user));
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

    public Mono<User> findUserWithMatchingCredentials(String login, String password) {
        return userRepository.findByLogin(login)
                .filter(user -> user.getPassword().equals(password))
                .switchIfEmpty(Mono.error(new ValidationException("Wrong credentials")));
    }
}
