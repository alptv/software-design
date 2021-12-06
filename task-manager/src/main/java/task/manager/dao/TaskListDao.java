package task.manager.dao;

import task.manager.model.TaskList;

import java.util.List;
import java.util.Optional;


public interface TaskListDao {

    Optional<TaskList> findTaskListById(final long taskListId);

    List<TaskList> findTaskListsByUserId(final long userId);

    void saveTaskList(final TaskList taskList);

    void deleteTaskListById(final long taskList);
}
