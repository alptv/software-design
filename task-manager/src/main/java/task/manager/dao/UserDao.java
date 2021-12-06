package task.manager.dao;

import task.manager.model.User;

import java.util.Optional;


public interface UserDao {
    long countByLogin(final String login);

    User saveUser(final User user);

    Optional<User> findByLogin(final String login);

}
