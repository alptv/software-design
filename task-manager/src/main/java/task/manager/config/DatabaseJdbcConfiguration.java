package task.manager.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import task.manager.dao.*;

import javax.sql.DataSource;

@Configuration
public class DatabaseJdbcConfiguration extends AbstractJdbcConfiguration {


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("user");
        dataSource.setPassword("password");
        return dataSource;
    }

    @Bean
    public TaskListDao tasksListsDao(DataSource dataSource) {
        TaskListJdbcDao taskListDao = new TaskListJdbcDao();
        taskListDao.setDataSource(dataSource);
        return taskListDao;
    }

    @Bean
    public TasksDao tasksDao(DataSource dataSource) {
        TasksJdbcDao taskDao = new TasksJdbcDao();
        taskDao.setDataSource(dataSource);
        return taskDao;

    }


    @Bean
    public UserDao usersDao(DataSource dataSource) {
        UserJdbcDao userDao = new UserJdbcDao();
        userDao.setDataSource(dataSource);
        return userDao;
    }
}
