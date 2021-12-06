package task.manager.service;


import org.springframework.stereotype.Service;
import task.manager.model.TaskList;
import task.manager.model.User;

import java.util.List;

@Service
public interface UserService {
    List<TaskList> getTaskListForUser(final long userId);

    void createTaskList(final long userId, final TaskList taskList);

    boolean isLoginFree(final String login);

    User register(final User user);

    boolean isValidUser(final User user);

    User findUserByLogin(final String login);
}
