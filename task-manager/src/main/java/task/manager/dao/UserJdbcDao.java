package task.manager.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import task.manager.model.User;

import java.util.Optional;


public class UserJdbcDao extends JdbcDaoSupport implements UserDao {
    @Override
    public long countByLogin(final String login) {
        String sql = "select count(*) from Users where Login = ?";
        return getJdbcTemplate().queryForObject(sql, Long.class, login);
    }

    @Override
    public User saveUser(final User user) {
        String sql = "insert into Users (Login, Password) values (?, ?)";
        getJdbcTemplate().update(sql, user.getLogin(), user.getPassword());
        return findByLogin(user.getLogin()).get();
    }

    @Override
    public Optional<User> findByLogin(final String login) {
        String sql = "select Id, Login, Password from Users where Login = ?";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(User.class), login).stream().findFirst();
    }
}
