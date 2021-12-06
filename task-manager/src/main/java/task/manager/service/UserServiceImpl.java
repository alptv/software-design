package task.manager.service;

import org.springframework.stereotype.Service;
import task.manager.dao.TaskListDao;
import task.manager.dao.UserDao;
import task.manager.model.TaskList;
import task.manager.model.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final TaskListDao taskListDao;
    private final UserDao userDao;

    public UserServiceImpl(final TaskListDao taskListDao, final UserDao userDao) {
        this.taskListDao = taskListDao;
        this.userDao = userDao;
    }

    public List<TaskList> getTaskListForUser(long userId) {
        return taskListDao.findTaskListsByUserId(userId);
    }

    @Override
    public void createTaskList(final long userId, final TaskList taskList) {
        taskList.setUserId(userId);
        taskListDao.saveTaskList(taskList);
    }

    @Override
    public boolean isLoginFree(final String login) {
        return userDao.countByLogin(login) == 0;
    }

    @Override
    public User register(final User user) {
        return userDao.saveUser(user);
    }

    @Override
    public boolean isValidUser(final User user) {
        Optional<User> possibleUser = userDao.findByLogin(user.getLogin());
        return possibleUser.map(value -> value.getPassword().equals(user.getPassword())).orElse(false);
    }

    @Override
    public User findUserByLogin(final String login) {
        return userDao.findByLogin(login).orElse(null);
    }
}
